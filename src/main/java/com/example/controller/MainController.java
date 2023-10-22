package com.example.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Message;

@RestController
public class MainController {

    @PostMapping("/test-method")
    public void testMethode(Message message){

    }

}
