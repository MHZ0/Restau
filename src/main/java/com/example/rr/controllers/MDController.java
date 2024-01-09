package com.example.rr.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.rr.models.MealsAndDrinks;
import com.example.rr.repositories.MDRepo;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MDController {
    private final MDRepo mdRepo;

    @GetMapping(value = "/menu")
    public List<MealsAndDrinks> getAll(){

        return mdRepo.findAll();
    }

    @GetMapping(value = "/mdbyid/{id}")
    public  MealsAndDrinks getMDByID(@PathVariable("id") Long id){

        return mdRepo.findById(id).orElseThrow();
    }

    @GetMapping(value = "/mdbyname/{name}")
    public  MealsAndDrinks getMDByName(@PathVariable("name") String name){

        return mdRepo.findByName(name).get();
    }

    @PostMapping(value = "/addtomenu")
    public MealsAndDrinks addMD(@Valid @RequestBody MealsAndDrinks mealsAndDrinks){

        return mdRepo.save(mealsAndDrinks);
    }

    @DeleteMapping(value = "/deletemdbyid/{id}")
    public MealsAndDrinks deleteMD(@PathVariable("id") Long id){

        Optional<MealsAndDrinks> md = mdRepo.findById(id);

        if(md.isPresent())
        {
            mdRepo.delete(md.get());
            return  md.get();

        }
        else
        {
            throw new RuntimeException("No meal or record record exist for given id");
        }
    }

    @PutMapping(value = "/updatemdbyid/{id}")
    public MealsAndDrinks editMD(@PathVariable("id") Long id,@Valid @RequestBody MealsAndDrinks updatedmd){

            Optional<MealsAndDrinks> md = mdRepo.findById(id);

            if(md.isPresent())
            {
                MealsAndDrinks existingMD = md.get();
                existingMD.setName(updatedmd.getName());
                existingMD.setDescription(updatedmd.getDescription());
                existingMD.setImagePath(updatedmd.getImagePath());
                existingMD.setPrice(updatedmd.getPrice());
                return  (mdRepo.save(existingMD));
            }else{
                throw new RuntimeException("No employee record exist for given id");
            }
    }

}
