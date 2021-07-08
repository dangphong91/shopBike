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
@Table(name = "sizes")
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idSize;

    private String nameSize;

    public Size(String nameSize) {
        this.nameSize = nameSize;
    }
}
