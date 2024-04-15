package kaem0n.u5w3d1.controllers;

import kaem0n.u5w3d1.entities.Employee;
import kaem0n.u5w3d1.exceptions.BadRequestException;
import kaem0n.u5w3d1.payloads.EmployeeDTO;
import kaem0n.u5w3d1.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService es;

    @GetMapping
    private Page<Employee> getAllEmployees(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size,
                                           @RequestParam(defaultValue = "id") String sort) {
        return es.findAll(page, size, sort);
    }

    @GetMapping("/{id}")
    private Employee getEmployeeById(@PathVariable long id) {
        return es.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Employee saveNewEmployee(@RequestBody @Validated EmployeeDTO payload, BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        else return es.save(payload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteEmployee(@PathVariable long id) {
        es.delete(id);
    }

    @PutMapping("/{id}")
    private Employee updateEmployee(@PathVariable long id, @RequestBody @Validated EmployeeDTO payload, BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        else return es.update(id, payload);
    }

    @PatchMapping("/{id}/avatar")
    private Employee changeAvatar(@PathVariable long id, @RequestParam("avatar") MultipartFile img) throws IOException {
        return es.changeAvatar(id, img);
    }
}
