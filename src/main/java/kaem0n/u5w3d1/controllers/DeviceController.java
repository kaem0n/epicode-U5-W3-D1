package kaem0n.u5w3d1.controllers;

import kaem0n.u5w3d1.entities.Device;
import kaem0n.u5w3d1.exceptions.BadRequestException;
import kaem0n.u5w3d1.payloads.DeviceDTO;
import kaem0n.u5w3d1.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    private DeviceService ds;

    @GetMapping
    private Page<Device> getAllDevices(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String sort){
        return ds.findAll(page, size, sort);
    }

    @GetMapping("/{id}")
    private Device getDeviceById(@PathVariable long id) {
        return ds.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Device saveNewDevice(@RequestBody @Validated DeviceDTO payload, BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        else return ds.save(payload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteDevice(@PathVariable long id) {
        ds.delete(id);
    }

    @PutMapping("/{id}")
    private Device updateDevice(@PathVariable long id, @RequestBody @Validated DeviceDTO payload, BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        else return ds.update(id, payload);
    }

    @PatchMapping("/{deviceId}/assign")
    private Device assignDevice(@PathVariable long deviceId, @RequestParam("employeeId") long employeeId) {
        return ds.assign(deviceId, employeeId);
    }

    @PatchMapping("/{id}/available")
    private Device makeDeviceAvailable(@PathVariable long id) {
        return ds.makeAvailable(id);
    }

    @PatchMapping("/{id}/dismiss")
    private Device dismissDevice(@PathVariable long id) {
        return ds.dismiss(id);
    }

    @PatchMapping("/{id}/maintenance")
    private Device startDeviceMaintenance(@PathVariable long id) {
        return ds.startMaintenance(id);
    }
}
