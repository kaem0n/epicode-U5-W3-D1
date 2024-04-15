package kaem0n.u5w3d1.controllers;

import kaem0n.u5w3d1.entities.Employee;
import kaem0n.u5w3d1.exceptions.BadRequestException;
import kaem0n.u5w3d1.payloads.EmployeeDTO;
import kaem0n.u5w3d1.payloads.LoginDTO;
import kaem0n.u5w3d1.payloads.LoginResponseDTO;
import kaem0n.u5w3d1.services.AuthService;
import kaem0n.u5w3d1.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService as;
    @Autowired
    private EmployeeService es;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO payload) {
        return as.login(payload);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    private Employee saveNewEmployee(@RequestBody @Validated EmployeeDTO payload, BindingResult validation) {
        if (validation.hasErrors()) throw new BadRequestException(validation.getAllErrors());
        else return es.save(payload);
    }
}
