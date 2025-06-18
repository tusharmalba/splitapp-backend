package com.splitapp.splitapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Welcome to SplitApp API! Use endpoints like /expenses, /balances, /settlements etc.";
    }
}
