package com.fsd.service;

import com.fsd.entity.Event;
import com.fsd.entity.Registration;
import com.fsd.entity.User;
import com.fsd.feign.EventClient;
import com.fsd.feign.UserClient;
import com.fsd.repository.RegistrationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegistrationService {
@Autowired
    private  RegistrationRepository registrationRepository;
    @Autowired
    private  UserClient userClient;
    
    @Autowired
    private EventClient eClient;

    
//    @Autowired
//    public RegistrationService(RegistrationRepository registrationRepository) {
//        this.registrationRepository = registrationRepository;
//        //this.userClient = userClient;
//    }

    @Transactional
    public Registration createRegistration(Long eventId, Long userId, int numberOfAdults, int numberOfChildren) {

        // Retrieve the user object using Feign client
        User user = userClient.getUserById(userId);
        Event event=eClient.getEventById(eventId);
        
        
        System.out.println(user);

        // Check if the user exists
        if (user == null) {
            // Handle the case where the user is not found
            throw new RuntimeException("User not found with ID: " + userId);
        }
        
        if (event == null) {
            // Handle the case where the user is not found
            throw new RuntimeException("Event not found with ID: " + eventId);
        }

        // Create a new registration instance
        Registration registration = new Registration();
        registration.setNumberOfAdults(numberOfAdults);
        registration.setNumberOfChildren(numberOfChildren);
        registration.setUserId(userId);
        registration.setEventId(eventId);
         // Set the retrieved user object on the registration

        // Save the registration
        return registrationRepository.save(registration);
    }

    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    public Registration getRegistrationById(Long id) {
        return registrationRepository.findById(id).orElse(null);
    }

    public List<Registration> getRegistrationsByUserId(Long userId) {
        return registrationRepository.findByUserId(userId);
    }

    public int getRegistrationCountByUserId(Long userId) {
        List<Registration> registrations = registrationRepository.findByUserId(userId);
        return registrations.size();
    }

    public int countRegistrationsByEventId(Long eventId) {
        return registrationRepository.countRegistrationsByEventId(eventId);
    }
}
