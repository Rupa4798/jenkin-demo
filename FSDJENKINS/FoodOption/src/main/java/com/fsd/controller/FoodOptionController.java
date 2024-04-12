package com.fsd.controller;

import com.fsd.entity.Event;
import com.fsd.entity.FoodOption;
import com.fsd.feign.EventClient;
import com.fsd.service.FoodOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foodOptions")
public class FoodOptionController {

    @Autowired
    private FoodOptionService foodOptionService;
    
    @Autowired
    private EventClient eClient;
    
     public FoodOptionController() {
    	 
    	 
    	 
     }
    
    @PostMapping
    public ResponseEntity<FoodOption> createFoodOption(@RequestBody FoodOption foodOption) {
        FoodOption createdFoodOption = foodOptionService.createFoodOption(foodOption);
        return new ResponseEntity<>(createdFoodOption, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FoodOption>> getAllFoodOptions() {
        List<FoodOption> foodOptions = foodOptionService.getAllFoodOptions();
        return new ResponseEntity<>(foodOptions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodOption> getFoodOptionById(@PathVariable Long id) {
        FoodOption foodOption = foodOptionService.getFoodOptionById(id);
        if (foodOption != null) {
            return new ResponseEntity<>(foodOption, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodOption> updateFoodOption(@PathVariable Long id, @RequestBody FoodOption foodOption) {
        FoodOption updatedFoodOption = foodOptionService.updateFoodOption(id, foodOption);
        if (updatedFoodOption != null) {
            return new ResponseEntity<>(updatedFoodOption, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFoodOption(@PathVariable Long id) {
        foodOptionService.deleteFoodOption(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    
    @GetMapping("/event/{eventId}")
    public List<FoodOption> getAllFoodOptionsByEventId(@PathVariable Long eventId) {
        return foodOptionService.getAllFoodOptionsByEventId(eventId);
        
    }
    
    @GetMapping("allevents")
    public Event getAllEvents(@PathVariable Long eventId) {
    	
    	return eClient.getEventById(eventId);
    	
    }
    
    
}

