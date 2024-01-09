package com.example.rr;

import com.example.rr.models.MealsAndDrinks;
import com.example.rr.models.Order;
import com.example.rr.repositories.MDRepo;
import com.example.rr.repositories.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class RrApplication {

    public static void main(String[] args) {

//        SpringApplication.run(RrApplication.class, args
       ApplicationContext cx = SpringApplication.run(RrApplication.class, args);
        OrderRepo orderRepo = cx.getBean(OrderRepo.class);
//        orderRepo.save(new Order(null,1,"Coca Cola + Pizza","Ready",15));
        MDRepo mdRepo = cx.getBean(MDRepo.class);
        mdRepo.save(new MealsAndDrinks(1L,"Pizza ","Large Pizza Margarita","/images/pm.jpg",15));
        mdRepo.save(new MealsAndDrinks(2L,"Pizza 4S","Large Italian 4 Season Pizza","/images/p4.jpg",20));
        mdRepo.save(new MealsAndDrinks(3L,"Pasta","Italian Pasta","/images/pasta.jpg",10));
        mdRepo.save(new MealsAndDrinks(4L,"Salade","Green salade with dark bred and olive","/images/salade.jpg",5));
        mdRepo.save(new MealsAndDrinks(5L,"Bacon","Bacon beef meat with olive oil","/images/bc.jpg",10));
        mdRepo.save(new MealsAndDrinks(6L,"Fried Chicken","Fried Chicken With crispy fried potato","/images/fc.jpg",15));
        mdRepo.save(new MealsAndDrinks(7L,"Chicken wings","Fried Chicken Wings","/images/cw.jpg",10));
        mdRepo.save(new MealsAndDrinks(8L,"Chicken Nuggets","10 Chicken nuggets pieces","/images/cn.jpg",12));
        mdRepo.save(new MealsAndDrinks(9L,"Chicken Burger","Chicken Burger with fries","/images/cb.jpg",10));
        mdRepo.save(new MealsAndDrinks(10L,"Beef Burger","Beef Burger with fries","/images/bb.jpg",10));
        mdRepo.save(new MealsAndDrinks(11L,"Cheese Burger","Cheese Burger with fries (meat of your choice)","/images/chb.jpg",13));
        mdRepo.save(new MealsAndDrinks(12L,"Coca Cola","Coca Cola Soda can 0.3L","/images/cs.jpg",1.5f));
        mdRepo.save(new MealsAndDrinks(13L,"Fanta","Fanta Soda can 0.3L","/images/fs.jpg",1.5f));
        mdRepo.save(new MealsAndDrinks(14L,"Sprite","Sprite Soda can 0.3L","/images/ss.jpg",1.5f));

    }

}
