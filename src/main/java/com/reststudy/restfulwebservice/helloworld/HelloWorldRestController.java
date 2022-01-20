package com.reststudy.restfulwebservice.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldRestController {

    // GET
    // Hello-World(endpoint)
    @GetMapping("/hello-world")
    public String HelloWorld(){
        return "Hello World";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean HelloWorldBean(){
        return new HelloWorldBean("Hello World");
    }

    @GetMapping("/hello-world-bean/path-variable/{name}")
    public HelloWorldBean HelloWorldBean(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World, %s",name));
    }

}
