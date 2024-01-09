package com.example.rr.controllers;

import jakarta.validation.Valid;
import com.example.rr.models.Order;
import com.example.rr.repositories.MDRepo;
import com.example.rr.repositories.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class OrderController {

    private final MDRepo mdRepo;
    private final OrderRepo order;

    @GetMapping(value="/orders")
    public List<Order> getAll(){
        return order.findAll();
    }

    @GetMapping(value="/orderbyid/{id}")
    public Order getOrderByID(@PathVariable("id") Long id){

        return order.findById(id).orElseThrow();
    }

    @PostMapping(value="/addorder")
    public Order addOrder(@Valid @RequestBody Order order){
        this.order.save(order);

        Optional<Order> ordertoc= this.order.findById(order.getOrderId());
        Order createdorder = ordertoc.get();
        this.order.save(createdorder);
        createdorder.setBill(order.calculateTotalBill());

        return this.order.save(createdorder);
    }

    @PutMapping(value="/updateorder/{id}")
    public Order updateOrder(@PathVariable("id") Long id,@Valid @RequestBody Order updatedOrder){
        Optional<Order> order = this.order.findById(id);
        if(order.isPresent()){
            Order existingOrder = order.get();
            existingOrder.setTableNumber(updatedOrder.getTableNumber());
            existingOrder.setMealsAndDrinks(updatedOrder.getMealsAndDrinks());
            this.order.save(existingOrder);
            existingOrder.setBill(existingOrder.calculateTotalBill());
            return this.order.save(existingOrder);
        }else{
            throw new RuntimeException("No order record exist for given id");
        }
    }


    @DeleteMapping(value="/deleteorderbyid/{id}")
    public Order deleteOrder(@PathVariable("id") Long id){
        Optional<Order> order = this.order.findById(id);
        if(order.isPresent()){
            this.order.delete(order.get());
            return order.get();
        }else{
            throw new RuntimeException("No order record exist for given id");
        }
    }

    @PutMapping("/updateTotalBillbyid/{orderId}")
    public float updateTotalBill(@PathVariable Long orderId) {
        Optional<Order> optionalOrder = order.findById(orderId);

        if (optionalOrder.isPresent()) {
            Order orderRepo = optionalOrder.get();
            orderRepo.setBill(orderRepo.calculateTotalBill()); // Assuming you have an 'updateTotalBill' method in your Order entity
            order.save(orderRepo); // Save the changes to the database
            return orderRepo.getBill();
        } else {
            throw new RuntimeException("No order record exist for given id");
        }
    }

    //    @PutMapping("/{orderId}/updateTotalBill") For calculating the total bill IN THE FRONT BY NAME String list method
//    public ResponseEntity<Float> updateTotalBill(@PathVariable Long orderId) {
//        Optional<Order> optionalOrder = order.findById(orderId);
//
//        if (optionalOrder.isPresent()) {
//            Order orderToUpdate = optionalOrder.get();
//            orderToUpdate.setBill(orderToUpdate.getBill()); // Assuming you have an 'updateTotalBill' method in your Order entity
//            order.save(orderToUpdate); // Save the changes to the database
//            return ResponseEntity.ok(orderToUpdate.getBill());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

//    @GetMapping("/{orderId}/totalBill")
//    public ResponseEntity<Float> getTotalBill(@PathVariable Long orderId) {
//        Optional<Order> optionalOrder = order.findById(orderId);
//        if (optionalOrder.isPresent()) {
//            Order orderRepo = optionalOrder.get();
//            float totalBill = orderRepo.calculateTotalBill();
//            return ResponseEntity.ok(totalBill);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

//    @GetMapping ("/placeorder/{tableid}")
//    public void placeOrder(@PathVariable("tableid") int tableid) {
//
//    }

    // add meal or a drink(mdrepo) name one by one to the order(Order) table
    // with calculating the price each time adding or deleting a meal or a drink for the bill

//    public void addOrder(Long MDid){
//        for (int i = 0; i < tableOrder.size(); i++) {
//            if (tableOrder.get(i) == null){
//                tableOrder.set(i, (MDRepo) getMDByID(MDid));
//                break;
//            }else{
//                tableOrder.set((tableOrder.size()+1), (MDRepo) getMDByID(MDid));
//                break;
//            }
//        }
//    }
//
//    public float billToPay(){
//        for (int i = 0; i < tableOrder.size(); i++) {
//            bill += tableOrder.get(i).getPrice();
//        }
//        return bill ;
//    }


}
