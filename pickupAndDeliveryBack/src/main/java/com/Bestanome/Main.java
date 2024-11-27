package com.Bestanome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Bestanome.Tools.XMLParsing;

@RestController
@SpringBootApplication
public class Main {

    @RequestMapping("/")
    String home() {
        return XMLParsing.parseXMLFile("../fichiersXMLPickupDelivery/myDeliverRequest.xml").toString(4);
        //return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}