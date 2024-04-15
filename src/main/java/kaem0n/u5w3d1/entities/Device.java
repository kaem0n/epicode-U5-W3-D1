package kaem0n.u5w3d1.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "devices")
public class Device {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_id")
    private long id;
    private String type;
    private String status;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Device(String type) {
        this.type = type;
        this.status = "Available";
        this.employee = null;
    }
}
