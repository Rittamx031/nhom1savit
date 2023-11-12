package savit.group2.sockstore.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "address")
    private String address;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "wardcode")
    private String wardcode;
    @Column(name = "fulladdress")
    private String fulladdress;

    @Column(name = "districtcode")
    private int districtcode;

    @Column(name = "updated_at")
    private LocalDateTime updateAt;
}
