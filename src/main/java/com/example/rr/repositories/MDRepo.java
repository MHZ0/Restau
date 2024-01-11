package com.example.rr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.rr.models.MealsAndDrinks;

import java.util.List;
import java.util.Optional;

public interface MDRepo extends JpaRepository<MealsAndDrinks,Long> {

    Optional<MealsAndDrinks> findByName(String name);

}
