package savit.group2.sockstore.model.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class SockBlogId implements Serializable {
    private UUID id_blog;

    private UUID id_sock;
}
