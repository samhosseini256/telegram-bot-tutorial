package com.example.service;

import com.example.Configs.BotInitialize;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Log4j
@Component
public class TelegramBotService extends TelegramLongPollingBot {

    @Autowired
    private BotInitialize botInitialize;

    @Override
    public String getBotUsername() {
        return botInitialize.getBotUsername();
    }

    @Override
    public String getBotToken() {
        return botInitialize.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message newMessage = update.getMessage();

        SendMessage responseMessage = new SendMessage();
        responseMessage.setChatId(newMessage.getChatId().toString());
        responseMessage.setText(newMessage.getText());

        sendAnswerMessage(responseMessage);

    }

    public void sendAnswerMessage(SendMessage message) {
        if (message != null) {
            try {
                execute(message);
            } catch (TelegramApiException e) {
                log.error(e);
            }
        }
    }

}
