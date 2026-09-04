package com.codehidder.user_demo.health;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/check-health")
    public String checkHealth(){
        return "Hello I am GOOD!";
    }
}
