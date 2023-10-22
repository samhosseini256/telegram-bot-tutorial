package com.example.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface BotService {

    void sendAnswerMessage(SendMessage message);

}