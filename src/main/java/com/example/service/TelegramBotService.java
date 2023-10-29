package com.example.service;

import com.example.service.manageCommonds.ResumeCommand;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendDice;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Dice;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.List;

@Log4j
@Component
public class TelegramBotService extends TelegramLongPollingBot {

    @Autowired
    private OtherTelegramService otherTelegramService;

    @Autowired
    private ResumeCommand resumeCommand;

    @Autowired
    private Contact myAcontAsContact;

    @Value("${bot.username}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }


    private List<User> allUsersStartTheBot = new ArrayList<>();

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {

        User user = update.getMessage().getFrom();
        if (!allUsersStartTheBot.contains(user)) {
            allUsersStartTheBot.add(user);
        }

//        otherTelegramService.sendTextMessage(String.valueOf(allUsersStartTheBot.size()),update.getMessage().getChatId());

//        //Echo
//        otherTelegramService.sendTextMessage(update.getMessage().getText(), update.getMessage().getChatId());

        Dice dice = new Dice();
        SendDice sendDice = new SendDice();
        sendDice.setChatId(update.getMessage().getChatId());
        execute(sendDice);




    }



    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(this);
        }
        catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }

}