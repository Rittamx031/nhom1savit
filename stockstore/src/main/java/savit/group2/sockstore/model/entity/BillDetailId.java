package savit.group2.sockstore.model.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class BillDetailId implements Serializable {
    private UUID id_bill;

    private UUID id_sock_detail;

}
