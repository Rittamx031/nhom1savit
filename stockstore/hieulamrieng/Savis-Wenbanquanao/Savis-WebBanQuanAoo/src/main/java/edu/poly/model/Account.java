package edu.poly.model;


import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
@Entity @Table(name= "Accounts")

public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank(message = "Username not blank")
    String username;
    @NotBlank(message = "Password not blank")
    String password;
    String fullname;
    @NotEmpty
    @Email
    String email;
    String address;
    Integer phone;
    String photo;
    @NotNull
    boolean activated;
    @NotNull
    boolean admin;
    @OneToMany(mappedBy = "account")
    List<Order> orders;
}
