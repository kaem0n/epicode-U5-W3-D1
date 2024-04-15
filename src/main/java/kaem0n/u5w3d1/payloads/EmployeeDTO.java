package kaem0n.u5w3d1.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record EmployeeDTO(@NotEmpty(message = "Username field must not be empty.")
                          @Size(min = 3, max = 20, message = "Username field length must be between 3 and 20 characters.")
                          String username,
                          @NotEmpty(message = "Name field must not be empty.")
                          @Size(min = 3, max = 20, message = "Name field length must be between 3 and 20 characters.")
                          String name,
                          @NotEmpty(message = "Surname field must not be empty.")
                          @Size(min = 3, max = 20, message = "Surname field length must be between 3 and 20 characters.")
                          String surname,
                          @NotEmpty(message = "Email field must not be empty.")
                          @Email(message = "Invalid email format.")
                          String email,
                          @NotEmpty(message = "Password field must not be empty.")
                          @Size(min = 8, message = "Password must be at least 8 characters long.")
                          String password) {
}
