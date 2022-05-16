package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {


    @GetMapping("/")
    public String welcome() {
        return "main";
    }

    @GetMapping("/calc")
    public String goCalc() {
        return "calc";
    }

    @GetMapping("/soch")
    public String goSoch() {
        return "soch/login";
    }

    @GetMapping("/mail")
    public String goMail() {
        return "mailing/main";
    }
}

