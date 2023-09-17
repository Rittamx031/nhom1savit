package savit.group2.sockstore.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Bill_Detail")
public class Bill_Detail {
    @EmbeddedId
    private BillDetailId id;

    @ManyToOne
    @JoinColumn(name = "id_bill")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "id_sock_detail")
    private Sock_Detail sock_detail;

    private Integer quantity;

    private BigDecimal price;

    private String node;

    private String status;
}
