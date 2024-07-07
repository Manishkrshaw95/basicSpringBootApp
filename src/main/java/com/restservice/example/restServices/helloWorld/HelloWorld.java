package com.restservice.example.restServices.helloWorld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {


    @GetMapping(path = "/hello-world")
    public String hellowWorld(){
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean hellowWorldBean(){
        return new HelloWorldBean("Hello Manish");
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean HelloWorlsVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Wolcome to the new world of SpringBoot %s", name));
    }

}
