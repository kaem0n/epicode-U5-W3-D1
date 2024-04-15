package kaem0n.u5w3d1.services;

import kaem0n.u5w3d1.entities.Device;
import kaem0n.u5w3d1.entities.Employee;
import kaem0n.u5w3d1.exceptions.BadRequestException;
import kaem0n.u5w3d1.exceptions.NotFoundException;
import kaem0n.u5w3d1.payloads.DeviceDTO;
import kaem0n.u5w3d1.repositories.DeviceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DeviceService {
    @Autowired
    private DeviceDAO dd;
    @Autowired
    private EmployeeService es;

    public Page<Device> findAll(int page, int size, String sort) {
        if (size > 50) size = 50;
        Pageable p = PageRequest.of(page, size, Sort.by(sort));
        return dd.findAll(p);
    }

    public Device findById(long id) {
        return dd.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Device save(DeviceDTO payload) {
        Device newDevice = new Device(payload.type());
        newDevice.setStatus("Available");
        return dd.save(newDevice);
    }

    public void delete(long id) {
        Device found = this.findById(id);
        dd.delete(found);
    }

    public Device update(long id, DeviceDTO payload) {
        Device found = this.findById(id);
        found.setType(payload.type());
        dd.save(found);
        return found;
    }

    public Device assign(long deviceId, long employeeId) {
        Device device = this.findById(deviceId);
        Employee employee = es.findById(employeeId);
        if (Objects.equals(device.getStatus(), "Dismissed")) throw new BadRequestException("Device ID '" + deviceId + "' is dismissed.");
        else if (Objects.equals(device.getStatus(), "Maintenance")) throw new BadRequestException("Device ID '" + deviceId + "' is being maintained.");
        else if (device.getEmployee() == employee) throw new BadRequestException("Device ID '" + deviceId +
                "' is already assigned to employee ID '" + employeeId + "'.");
        else {
            device.setStatus("Assigned");
            device.setEmployee(employee);
            return dd.save(device);
        }
    }

    public Device makeAvailable(long id) {
        Device found = this.findById(id);
        if (Objects.equals(found.getStatus(), "Dismissed")) throw new BadRequestException("Device ID '" + id + "' is dismissed.");
        else {
            found.setStatus("Available");
            found.setEmployee(null);
            return dd.save(found);
        }
    }

    public Device dismiss(long id) {
        Device found = this.findById(id);
        if (Objects.equals(found.getStatus(), "Dismissed")) throw new BadRequestException("Device ID '" + id + "' is already dismissed.");
        else {
            found.setStatus("Dismissed");
            found.setEmployee(null);
            return dd.save(found);
        }
    }

    public Device startMaintenance(long id) {
        Device found = this.findById(id);
        if (Objects.equals(found.getStatus(), "Dismissed")) throw new BadRequestException("Device ID '" + id + "' is dismissed.");
        else if (Objects.equals(found.getStatus(), "Maintenance")) throw new BadRequestException("Device ID '" + id + "' is already being maintained.");
        else {
            found.setStatus("Maintenance");
            found.setEmployee(null);
            return dd.save(found);
        }
    }
}
