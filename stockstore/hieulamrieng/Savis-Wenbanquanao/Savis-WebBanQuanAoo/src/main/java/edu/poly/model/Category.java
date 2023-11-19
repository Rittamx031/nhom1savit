package edu.poly.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;


@Data
@Entity
@Table(name="Categories")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank(message = "Name not empty")
    String name;
    @NotNull
    Boolean status;
    @OneToMany(mappedBy = "category")
    List<Product> products;
}
