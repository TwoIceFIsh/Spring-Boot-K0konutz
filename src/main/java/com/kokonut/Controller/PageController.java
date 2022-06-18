package com.kokonut.Controller;

import com.kokonut.Service.mailingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
@Slf4j
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

    @GetMapping("/h2-console")
    public String to_h2() {
        log.info("h2");
        return "/";
    }

    @GetMapping("/soch")
    public String goSoch() {
        return "soch/login";
    }

    @GetMapping("/mail")
    public String goMail(Model model) throws IOException {
        model.addAttribute("mail_count", mailingservice.get_list("./mail_list.txt").size());
        model.addAttribute("articles", mailingservice.getArticleList());
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


    @GetMapping("/dac")
    public String go_dac() {
        return "dac/make";
    }
}

