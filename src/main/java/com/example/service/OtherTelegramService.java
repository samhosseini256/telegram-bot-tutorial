package com.example.service;

import lombok.*;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.GetMe;
import org.telegram.telegrambots.meta.api.methods.commands.GetMyCommands;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendContact;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OtherTelegramService extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;

    @Bean
    public Contact myAcontAsContact(){

        Contact myContact = new Contact();
        myContact.setFirstName("Aziz");
        myContact.setLastName("Hosseini");
        //todo change phone number
        myContact.setPhoneNumber("+00000000000");
        myContact.setUserId(59566445l);

        return myContact;
    }

    @Bean
    public TelegramBotsApi telegramBotsApi() throws TelegramApiException {
        return new TelegramBotsApi(DefaultBotSession.class);
    }


    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public void onUpdateReceived(Update update) {
    }

    public void sendTextMessage(String text, long chatId) {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(chatId);
        sendMessage.enableMarkdown(true);


        if (sendMessage != null) {
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                log.error(e);
            }
        }
    }
    public void sendContactMessage(Contact contact, long chatId){

        SendContact sendContact = new SendContact();
        sendContact.setFirstName(contact.getFirstName());
        sendContact.setLastName(contact.getLastName());
        sendContact.setPhoneNumber(contact.getPhoneNumber());
        sendContact.setChatId(chatId);

        if (sendContact != null){
            try {
                execute(sendContact);
            } catch (TelegramApiException e){
                log.error(e);
            }
        }
    }

    public void difetentMessages(Update update){

        if (update.hasMessage()){
            sendTextMessage(update.getMessage().getText(), update.getMessage().getChatId());
            System.out.println("GetMessageChatId: " + update.getMessage().getChatId());
        }

//        update.get



        if (update.hasEditedMessage()){
            sendTextMessage(update.getEditedMessage().getText(),update.getEditedMessage().getChatId());
            System.out.println("GetEdidetMessageChatId: " + update.getEditedMessage().getChatId());

            if (update.getMessage() != null){
                System.out.println("GetMessageChatId: " + update.getMessage().getChatId());
            }

//            System.out.println((update.getMessage() != null)? update.getMessage().getChatId(): "Null" );
        }

    }



    @SneakyThrows
    public void sendMessage (AbsSender absSender, String chatId){

        Message message = new Message();
//        message.sett


        SendMessage answer = new SendMessage();
        answer.setChatId(chatId);
        answer.setText("Test");

        absSender.execute(answer);

    }

    @SneakyThrows
    public User getMe2() {
        return execute(new GetMe());
    }

    @SneakyThrows
    public ArrayList<BotCommand> getMyCommands (){
        return execute(new GetMyCommands());
    }

    @SneakyThrows
    public void setCommand (){

        BotCommand resume2 = new BotCommand("resume2", "Resume2");
        BotCommand resume3 = new BotCommand("resume3", "Resume3");

        List<BotCommand> botCommands = Arrays.asList(resume2,resume3);


        SetMyCommands setMyCommands = new SetMyCommands();

        setMyCommands.setCommands(botCommands);


        execute(setMyCommands);
    }



}
