package com.musku.paymentservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Value("${stripe.apikey}")
    String stripeKey;
    @GetMapping("/hello")
    public String hello()
    {
        return "hello "+ stripeKey ;
    }
}
