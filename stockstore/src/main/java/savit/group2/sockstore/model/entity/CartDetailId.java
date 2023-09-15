package savit.group2.sockstore.model.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class CartDetailId implements Serializable {
    private UUID id_cart;

    private UUID id_sock_detail;
}
