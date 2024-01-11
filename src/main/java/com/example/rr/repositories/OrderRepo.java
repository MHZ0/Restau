package com.example.rr.repositories;

import com.example.rr.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order,Long> {

}
