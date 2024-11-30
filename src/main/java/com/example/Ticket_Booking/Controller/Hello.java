package com.example.Ticket_Booking.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

@RequestMapping("/v1")
//    @GetMapping("/hello")
    public String greet(){
        return "Welcome to start Ticket Booking appliction";
    }
}
