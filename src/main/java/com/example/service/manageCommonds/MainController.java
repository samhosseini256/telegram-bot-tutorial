package com.example.service.manageCommonds;

import com.example.dao.UserDomain;
import com.example.service.TelegramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    private TelegramService telegramService;

    @RequestMapping("/getMe")
    public User getMe() {
        return telegramService.getMe2();
    }

    @RequestMapping("/get-my-commands")
    public ArrayList<BotCommand> getMyCommands() {
        return telegramService.getMyCommands();
    }


    @RequestMapping("/delete-my-commands")
    public void deleteMyCommand(){
        telegramService.deleteMyCommands();
    }


    @RequestMapping("/send-message-to-all-users")
    public void addCommand(@RequestBody String message){
        telegramService.sendMessageToAllUsers(message);
    }

    @RequestMapping("/get-all-users")
    public List<UserDomain> getAllUsers(){
        return telegramService.getAllUsers();
    }

    @RequestMapping("/first-test-methode")
    public void sendMessageToAllUsers(){
        telegramService.firstTestMethod();
    }

}