package com.kokonut.Controller;


import com.kokonut.DTO.mailing.mailingDTO;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@ToString
@RestController
@Slf4j
public class apiController {

    @PostMapping("/emailCheck")
    public ResponseEntity mailContll(@RequestBody mailingDTO address) throws IOException {
        Document doc = Jsoup.connect("http://androidweekly.net/").get();
        log.info(String.valueOf(doc.body()));
        return ResponseEntity.status(HttpStatus.OK).body('a');

    }
}
