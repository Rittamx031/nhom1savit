package savit.group2.sockstore.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    private Date date_create;

    @ManyToOne
    @JoinColumn(name = "id_employee")
    private Employee employee;

    private Date date_payment;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    private String note;

    @ManyToOne
    @JoinColumn(name = "id_pay_method")
    private Pay_Method pay_method;

    private String status;
}
