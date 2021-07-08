package com.shopbike.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUser;

    private String nameUser;
    private String email;
    private String password;
    private String phone;
    private String address;

    public User(String nameUser, String email, String password, String phone, String address) {
        this.nameUser = nameUser;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }
}
