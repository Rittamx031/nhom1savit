package savit.group2.sockstore.model.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "SockDetail_Blog")
public class SockDetail_Blog {
    @EmbeddedId
    private SockDetail_BlogID id;

    @ManyToOne
    @JoinColumn(name = "id_blog")
    private Blog blog;

    @ManyToOne
    @JoinColumn(name = "id_sock_detail")
    private Sock_Detail sock_detail;

}
