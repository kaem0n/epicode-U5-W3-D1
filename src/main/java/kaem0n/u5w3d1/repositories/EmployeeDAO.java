package kaem0n.u5w3d1.repositories;

import kaem0n.u5w3d1.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Long> {
    boolean existsByEmail(String email);

    Optional<Employee> findByUsername(String username);
}
