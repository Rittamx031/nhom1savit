package savit.group2.sockstore.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Discount_SockDetail")
public class Discount_SockDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_sock_detail")
    private Sock_Detail sock_detail;

    @ManyToOne
    @JoinColumn(name = "id_discount")
    private Discount discount;

    private Double money_return;

    private boolean status;

    private Double unit_base_price;


}
