package savit.group2.sockstore.model.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class Discount_SockDetail_Id implements Serializable {

    private UUID id_sock_detail;

    private UUID id_discount;

}
