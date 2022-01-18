package com.reststudy.restfulwebservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldRestController {

    // GET
    // Hello-World(endpoint)
    @GetMapping("/hello-world")
    public String HelloWorld(){
        return "Hello World";
    }
}
