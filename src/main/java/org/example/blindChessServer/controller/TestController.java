package org.example.blindChessServer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @GetMapping("/c1")
    public String func(){
        return "GOOD";
    }
}
