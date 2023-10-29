package com.example.service;

import com.example.service.manageCommonds.ResumeCommand;
import jakarta.annotation.PostConstruct;
import lombok.*;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.extensions.bots.commandbot.CommandBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.GetMe;
import org.telegram.telegrambots.meta.api.methods.commands.DeleteMyCommands;
import org.telegram.telegrambots.meta.api.methods.commands.GetMyCommands;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMemberCount;
import org.telegram.telegrambots.meta.api.methods.send.SendContact;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramBot;
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
    public Contact myAcontAsContact() {

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

//        User user = new User();
//        user.

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

    public void sendContactMessage(Contact contact, long chatId) {

        SendContact sendContact = new SendContact();
        sendContact.setFirstName(contact.getFirstName());
        sendContact.setLastName(contact.getLastName());
        sendContact.setPhoneNumber(contact.getPhoneNumber());
        sendContact.setChatId(chatId);

        if (sendContact != null) {
            try {
                execute(sendContact);
            } catch (TelegramApiException e) {
                log.error(e);
            }
        }
    }

    public void difetentMessages(Update update) {

        if (update.hasMessage()) {
            sendTextMessage(update.getMessage().getText(), update.getMessage().getChatId());
            System.out.println("GetMessageChatId: " + update.getMessage().getChatId());
        }

//        update.get


        if (update.hasEditedMessage()) {
            sendTextMessage(update.getEditedMessage().getText(), update.getEditedMessage().getChatId());
            System.out.println("GetEdidetMessageChatId: " + update.getEditedMessage().getChatId());

            if (update.getMessage() != null) {
                System.out.println("GetMessageChatId: " + update.getMessage().getChatId());
            }

//            System.out.println((update.getMessage() != null)? update.getMessage().getChatId(): "Null" );
        }

    }


    @SneakyThrows(value = {TelegramApiException.class})
    public void sendMessage(AbsSender absSender, String chatId) {

        Message message = new Message();
//        message.sett


        SendMessage answer = new SendMessage();
        answer.setChatId(chatId);
        answer.setText("Test");

        absSender.execute(answer);

    }

    @SneakyThrows(value = {TelegramApiException.class})
    public User getMe2() {
        return execute(new GetMe());
    }

    @SneakyThrows(value = {TelegramApiException.class})
    public ArrayList<BotCommand> getMyCommands() {
        return execute(new GetMyCommands());
    }

    @SneakyThrows(value = {TelegramApiException.class})
    public void setMyCommand(BotCommand botCommand) {

        List<BotCommand> botCommands = getMyCommands();
        botCommands.add(botCommand);

        SetMyCommands setMyCommands = new SetMyCommands();
        setMyCommands.setCommands(botCommands);

        execute(setMyCommands);
    }


    @SneakyThrows(value = {TelegramApiException.class})
    public void deleteMyCommands() {
        DeleteMyCommands deleteMyCommands = new DeleteMyCommands();
        execute(deleteMyCommands);
    }

    public void addHelpCommand(){
        String messageText = "The BultIn bot supports the following commands: \ncommands : description\n";

        for (BotCommand botCommand: getMyCommands()){
            messageText.contains(botCommand.getCommand() + " : "+botCommand.getDescription());
        }

        BotCommand helpCommand = new BotCommand("help", "Help Command");




        setMyCommand(helpCommand);

    }


    public void addCommand() {


    }

////    @PostConstruct
//    @SneakyThrows
//    public List<User> allUsersStartRobot(){
//
//        return null;
//    }



}
