package savit.group2.sockstore.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Set;
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

    @ManyToOne
    @JoinColumn(name = "id_size")
    private Size size;

    private Boolean outstanding;

    private Boolean status;

    @OneToMany(mappedBy = "sock")
    @JsonIgnore
    private Set<Sock_Detail> sockDetail;

}
