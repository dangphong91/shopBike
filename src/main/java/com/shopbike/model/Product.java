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
    private Double priceProduct;
    private String description;

    public Product(Type type, Size size, String nameProduct, Double priceProduct, String description) {
        this.type = type;
        this.size = size;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.description = description;
    }
}