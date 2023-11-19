package edu.poly.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity @Table(name = "Orders")
public class Order implements Serializable {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
     String username;
    String address;
    Boolean available;
    Boolean confirm;
    Double price;
    @Temporal(TemporalType.DATE)
    @Column(name = "Createdate")
    Date createDate = new Date();
    @ManyToOne
    @JoinColumn(name="Account_id")
    Account account;
    @OneToMany(mappedBy = "order")
    List<OrderDetail> orderDetails;
}
