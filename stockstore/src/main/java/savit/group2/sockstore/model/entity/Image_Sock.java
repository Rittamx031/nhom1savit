package savit.group2.sockstore.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Image_Sock")
public class Image_Sock {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    private String code;

    private String path;

    @ManyToOne
    @JoinColumn(name = "id_sock")
    private Sock sock;

    private Boolean status;
}
