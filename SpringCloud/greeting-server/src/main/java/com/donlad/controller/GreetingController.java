package com.donlad.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author donald
 * @date 2021/06/12
 */
@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @GetMapping("/sayHello/{name}")
    public String sayHello(@PathVariable("name") String name) {

        return "hello, " + name;
    }
}
