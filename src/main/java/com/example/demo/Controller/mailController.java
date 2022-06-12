package com.example.demo.Controller;

import com.example.demo.DTO.mailing.mailingDTO;
import com.example.demo.Service.mailingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
public class mailController {

    @Autowired
    private mailingService mailing;


    @PostMapping("/email_address")
    public ResponseEntity requestBodyJsonA(@RequestBody mailingDTO mailingdto) {
        return ResponseEntity.status(HttpStatus.OK).body(mailing.mail_insert(mailingdto));
    }


}
