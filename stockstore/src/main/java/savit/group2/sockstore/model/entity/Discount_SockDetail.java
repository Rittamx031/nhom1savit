package savit.group2.sockstore.model.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Discount_SockDetail")
public class Discount_SockDetail {
    @EmbeddedId
    private Discount_SockDetail_Id id;

    @ManyToOne
    @MapsId("id_sock_detail")
    @JoinColumn(name = "id_sock_detail")
    private Sock_Detail sock_detail;

    @ManyToOne
    @MapsId("id_discount")
    @JoinColumn(name = "id_discount")
    private Discount discount;

    private Double unit_base_price;

    private Double money_return;


}
