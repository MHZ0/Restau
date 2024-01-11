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
@Table (name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long orderId;

    @Column(name = "tableNumber")
    int tableNumber;

        @ManyToMany(cascade ={} )
    @JoinTable(
            name = "order_meals_and_drinks",
            joinColumns = @JoinColumn(name = "orderId"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private List<MealsAndDrinks> mealsAndDrinks;

    @Column(name = "bill")
    float bill;

    public float calculateTotalBill() {
        float totalBill = 0;
        if (mealsAndDrinks != null) {
            for (MealsAndDrinks item : mealsAndDrinks) {
                totalBill += item.getPrice();
            }
        }
        return totalBill;
    }


//    @ElementCollection
//    @CollectionTable(name = "order_meals_and_drinks", joinColumns = @JoinColumn(name = "orderId"))
//    @Column(name = "meals_or_drinks_name")
//    private List<String> mealsOrDrinksName;

//    @Column(name = "mealsAndDrinks")
//    String mealsOrDrinksName;



    //    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "order_meals_and_drinks",
//            joinColumns = @JoinColumn(name = "orderId"),
//            inverseJoinColumns = @JoinColumn(name = "id")
//    )
//    private List<MealsAndDrinks> mealsAndDrinks; // change to list of meals and drinks in postman to an existing ones



//    public class md{
//        Long id;
//        String name;
//        String description;
//        double price;
//    }


}
