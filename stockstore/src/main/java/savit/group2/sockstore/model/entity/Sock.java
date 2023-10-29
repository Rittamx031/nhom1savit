package savit.group2.sockstore.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Sock")
public class Sock {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    private String code;

    private String name;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "id_producer")
    private Producer producer;

    @ManyToOne
    @JoinColumn(name = "id_meterial")
    private Material material;

    private String path;

    private Boolean outstanding;

    private Boolean status;

}
