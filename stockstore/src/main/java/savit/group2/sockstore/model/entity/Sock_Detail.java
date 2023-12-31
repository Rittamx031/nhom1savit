package savit.group2.sockstore.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;
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

    private String description;

    @ManyToOne
    @JoinColumn(name = "id_discount")
    private Discount discount;

    @ManyToOne
    @JoinColumn(name = "id_sock")
    private Sock sock;

    @ManyToOne
    @JoinColumn(name = "id_color")
    private Color color;

    private String path;

    private Boolean status;


}
