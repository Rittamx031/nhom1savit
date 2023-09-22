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
@Table(name = "Sock_Detail")
public class Sock_Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;


    private Integer quantity;

    private Double unit_base_price;

    @ManyToOne
    @JoinColumn(name = "id_producer")
    private Producer producer;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;


    @ManyToOne
    @JoinColumn(name = "id_sock")
    private Sock sock;

    @ManyToOne
    @JoinColumn(name = "id_pattern")
    private Pattern pattern;

    @ManyToOne
    @JoinColumn(name = "id_meterial")
    private Material material;

    @ManyToOne
    @JoinColumn(name = "id_size")
    private Size size;

    @ManyToOne
    @JoinColumn(name = "id_color")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "id_image")
    private Image_Sock_Detail image_sock_detail;

    private Boolean status;
}
