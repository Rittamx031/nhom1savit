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

    private String coupon_code;

    private String name;

    private String description;

    private Date valid_from;

    private Date valid_until;

    private Double cash_discount;

    private Float percent_discount;

    private String discount_type;

    private Boolean status;
}
