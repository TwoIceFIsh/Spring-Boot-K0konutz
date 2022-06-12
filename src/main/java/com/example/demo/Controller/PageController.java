package com.example.demo.Controller;

import com.example.demo.Service.mailingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class PageController {

    @Autowired
    mailingService mailingservice;

    @GetMapping("/")
    public String welcome() {
        return "main";
    }

    @GetMapping("/calc")
    public String goCalc() {
        return "calc/calc";
    }

    @GetMapping("/soch")
    public String goSoch() {
        return "soch/login";
    }

    @GetMapping("/mail")
    public String goMail(Model model) throws IOException {
        model.addAttribute("mail_count", mailingservice.get_num());
        return "mailing/main";
    }

    @GetMapping("/kokonutz/map")
    public String goMap() {
        return "kokonutz/map";
    }

    @GetMapping("/test")
    public String mm() {
        return "dac/main";
    }

    @GetMapping("/kokonutz")
    public String goKokonutz() {
        return "kokonutz/main";
    }
}

