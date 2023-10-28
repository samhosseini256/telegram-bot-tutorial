package com.example.service.manageCommonds;

import com.example.service.OtherTelegramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.ArrayList;

@RestController
public class CommondsController {

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

    @RequestMapping("/set-command")
    public void setCommands(){
        otherTelegramService.setCommand();
    }


}