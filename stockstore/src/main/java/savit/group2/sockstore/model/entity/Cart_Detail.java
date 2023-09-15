package savit.group2.sockstore.model.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Cart_Detail")
public class Cart_Detail {
    @EmbeddedId
    private CartDetailId id;

    @ManyToOne
    @JoinColumn(name = "id_cart")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "id_sock_detail")
    private Sock_Detail sock_detail;

    private Integer quantity;

    private String note;

    private String status;
}