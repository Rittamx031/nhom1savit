package savit.group2.sockstore.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    private String name;

    private String email;
    private String password;

    private LocalDate birthday;

    private String phone;

    private String address;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

}
