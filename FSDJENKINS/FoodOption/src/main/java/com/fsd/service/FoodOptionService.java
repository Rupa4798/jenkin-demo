package com.fsd.service;

import com.fsd.entity.FoodOption;
import com.fsd.feign.EventClient;
import com.fsd.repository.FoodOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodOptionService {

    @Autowired
    private FoodOptionRepository foodOptionRepository;
    
    @Autowired
    EventClient eClient;

    public FoodOption createFoodOption(FoodOption foodOption) {
        return foodOptionRepository.save(foodOption);
    }

    public List<FoodOption> getAllFoodOptions() {
        return foodOptionRepository.findAll();
    }

    public FoodOption getFoodOptionById(Long id) {
        return foodOptionRepository.findById(id).orElse(null);
    }

    public FoodOption updateFoodOption(Long id, FoodOption foodOption) {
        if (foodOptionRepository.existsById(id)) {
            foodOption.setId(id);
            return foodOptionRepository.save(foodOption);
        }
        return null;
    }

    public void deleteFoodOption(Long id) {
        foodOptionRepository.deleteById(id);
    }
    
    
    public List<FoodOption> getAllFoodOptionsByEventId(Long eventId) {
       
    	 System.out.println(eClient.getEventById(eventId));
    	return foodOptionRepository.findByEventId(eventId);
        
       
        
    }
    
    
    
    
    
    
    
    
}
