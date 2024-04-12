package com.fsd.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.fsd.entity.User;

import feign.Headers;

@FeignClient(name = "USERSERVICE")
public interface UserClient {

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable("id") Long id);
}
