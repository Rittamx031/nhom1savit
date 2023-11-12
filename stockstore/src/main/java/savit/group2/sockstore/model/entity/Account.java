package savit.group2.sockstore.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private Boolean status;
    @Column(name = "actived")
    private Boolean actived;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;
    @Column(name = "updated_at")
    private LocalDateTime updateAt;
}
