package savit.group2.sockstore.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Customer_Address")
public class Customer_Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    private String address;

    private String city;

    private String country;

    private String wardcode;

    private int districtcode;
    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;
}
