package com.fsd.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fsd.entity.Event;

import feign.Headers;
@FeignClient(name = "EventService", url = "http://localhost:7070")

public interface EventClient {
	
	

    @Headers("Content-Type: application/json") // Correct format for the Headers annotation
    @GetMapping("/events/{id}")
    Event getEventById(@PathVariable("id") Long id);

		

}
