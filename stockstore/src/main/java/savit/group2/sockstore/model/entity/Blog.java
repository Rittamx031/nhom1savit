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
@Table(name = "Blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    private String title;

    private String path;

    private Date date_create;

    private Date date_update;

    private String content;

    private Boolean status;
}
