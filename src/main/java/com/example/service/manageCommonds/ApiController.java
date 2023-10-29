package com.example.service.manageCommonds;

import com.example.service.OtherTelegramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.ArrayList;

@RestController
public class ApiController {

    @Autowired
    private OtherTelegramService otherTelegramService;

    @RequestMapping("/getMe")
    public User getMe() {
        return otherTelegramService.getMe2();
    }

    @RequestMapping("/get-my-commands")
    public ArrayList<BotCommand> getMyCommands() {
        return otherTelegramService.getMyCommands();
    }


    @RequestMapping("/delete-my-commands")
    public void deleteMyCommand(){
        otherTelegramService.deleteMyCommands();
    }

    @RequestMapping("/add-help-command")
    public void addHelpCommand(){
        otherTelegramService.addHelpCommand();
    }

    @RequestMapping("/add-command")
    public void addCommand(){
        otherTelegramService.addCommand();
    }


}