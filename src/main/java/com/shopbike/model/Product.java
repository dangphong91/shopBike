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
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProduct;

    @ManyToOne
    @JoinColumn(name = "typeId")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "sizeId")
    private Size size;

    private String nameProduct;
    private double priceProduct;
    private String description;
    private String image;

    public Product(String nameProduct, Type type, Size size, double priceProduct, String description, String image) {
        this.nameProduct = nameProduct;
        this.type = type;
        this.size = size;
        this.priceProduct = priceProduct;
        this.description = description;
        this.image = image;
    }
}