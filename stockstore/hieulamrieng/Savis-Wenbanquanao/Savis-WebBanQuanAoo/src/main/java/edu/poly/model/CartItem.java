package edu.poly.model;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class CartItem {
    private long id;
    private String name;
    private String image;
    private int quantity;
    private double price;
    private int discount;

}
