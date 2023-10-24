package com.example.service;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class HelloCommand extends TelegramLongPollingCommandBot {


    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public void processNonCommandUpdate(Update update) {

    }
}