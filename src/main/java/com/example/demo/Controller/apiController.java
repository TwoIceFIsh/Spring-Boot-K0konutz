package com.example.demo.Controller;


import com.example.demo.DTO.mailing.mailingDTO;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@ToString
@RestController
@Slf4j
public class apiController {

    @PostMapping("/emailCheck")
    public ResponseEntity mailContll(@RequestBody mailingDTO address) {

        return ResponseEntity.status(HttpStatus.OK).body(address);

    }
}
