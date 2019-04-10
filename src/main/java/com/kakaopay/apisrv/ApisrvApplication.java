package com.kakaopay.apisrv;

import com.kakaopay.apisrv.service.ParseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ApisrvApplication {
    @Autowired
    private ParseDataService parseDataService;

    public static void main(String[] args) {
        SpringApplication.run(ApisrvApplication.class, args);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void setupData() {
//        parseDataService.loadAttractionCsvData("data2.csv");
//    }
}
