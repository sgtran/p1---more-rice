package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AtharvaGameController {

    @GetMapping("/2048")
    public String test() {
        return "2048";
    }
}
 
