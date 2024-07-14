package com.example.todolu.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("ola")
public class HelloWorldController {

    @GetMapping
    public String ola() {
        return "Olá mundo";
    }

}
