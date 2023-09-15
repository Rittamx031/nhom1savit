package savit.group2.sockstore.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Discount")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "id_sock")
    private Sock sock;

    private String coupon_code;

    private BigDecimal discount_value;

    private String discount_unit;

    private BigDecimal mininum_order_value;

    private BigDecimal maximun_discount_value;

    private Date valid_form;

    private Date valid_unit;

    private Date created_at;

    private Date updated_at;

    private String created_by;

    private String updated_by;

    private  Boolean status;
}
