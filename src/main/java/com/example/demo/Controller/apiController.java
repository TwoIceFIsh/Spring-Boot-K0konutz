package com.example.demo.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class apiController {

    @GetMapping("/mailing/{mailAddress}")
    public void mailContll(@PathVariable String mailAddress) {

    }
}
