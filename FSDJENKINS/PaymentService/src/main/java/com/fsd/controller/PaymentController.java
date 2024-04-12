package com.fsd.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/makePayment")
    public ResponseEntity<String> makePayment(@RequestParam Long registrationId, @RequestParam String paymentMethod) {
        String paymentResult = paymentService.makePayment(registrationId, paymentMethod);
        return ResponseEntity.status(HttpStatus.OK).body(paymentResult);
    }
}
