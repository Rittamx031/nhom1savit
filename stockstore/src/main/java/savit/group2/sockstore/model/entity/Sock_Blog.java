package savit.group2.sockstore.model.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Sock_Blog")
public class Sock_Blog {

    @EmbeddedId
    private SockBlogId id;

    @ManyToOne
    @MapsId("id_blog")
    @JoinColumn(name = "id_blog")
    private Blog blog;

    @ManyToOne
    @MapsId("id_sock")
    @JoinColumn(name = "id_sock")
    private Sock sock;

    private Boolean status;
}
