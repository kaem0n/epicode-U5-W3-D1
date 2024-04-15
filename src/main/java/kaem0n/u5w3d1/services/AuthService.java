package kaem0n.u5w3d1.services;

import kaem0n.u5w3d1.entities.Employee;
import kaem0n.u5w3d1.exceptions.UnauthorizedException;
import kaem0n.u5w3d1.payloads.LoginDTO;
import kaem0n.u5w3d1.payloads.LoginResponseDTO;
import kaem0n.u5w3d1.security.JWTTokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {
    @Autowired
    private EmployeeService es;
    @Autowired
    private JWTTokenHandler th;

    public LoginResponseDTO login(LoginDTO payload) {
        Employee found = this.es.findByUsername(payload.username());
        if (Objects.equals(found.getPassword(), payload.password())) return new LoginResponseDTO(th.createToken(found));
        else throw new UnauthorizedException("Invalid credentials, try again.");
    }
}
