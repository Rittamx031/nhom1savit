package edu.poly.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="Products")
public class Product implements Serializable {
 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
 @NotBlank(message = "Name product not empty")
    String name;
   String image;
 @NotNull
    Integer quantity;
    @NotNull
    Double price;
    @NotBlank(message = "About not empty")
    String about;
    @NotNull
    Integer discount;
    @Temporal(TemporalType.DATE)
    @Column(name = "Createdate")
    Date createDate = new Date();
    @NotNull
    Boolean available;
    @ManyToOne
    @JoinColumn(name = "Categoryid")
    Category category;
    @OneToMany(mappedBy = "product")
    List<OrderDetail> orderDetails;


}
