package com.example.rr.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "meals_and_drinks")
public class MealsAndDrinks {

    @Id
    Long id;
//   @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @Column(name = "imagepath")
    String imagePath;

    @Column(name = "price")
    float price;



}
