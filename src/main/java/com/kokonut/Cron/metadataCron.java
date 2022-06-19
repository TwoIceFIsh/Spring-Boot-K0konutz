package com.kokonut.Cron;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class metadataCron {

    @Scheduled(fixedDelay = 900000)
    public void updateArticle() throws IOException {

        Document doc = Jsoup.connect("https://en.wikipedia.org/").get();
        log.info("Cron Up");
        Elements newsHeadlines = doc.select("table > tbody > tr > td");
        for (Element headline : newsHeadlines) {
            log.info("%s\n\t%s",
                    headline.attr("title"), headline.absUrl("href"));
        }
    }

}
