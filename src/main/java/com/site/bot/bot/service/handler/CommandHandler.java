package com.site.bot.bot.service.handler;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import com.site.bot.bot.data.CommandData;
import com.site.bot.bot.service.manager.ForecastManager;
import com.site.bot.bot.service.manager.MainManager;
import com.site.bot.bot.service.telegram.Bot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommandHandler {
    
    MainManager mainManager;
    ForecastManager forecastManager;
    
    public BotApiMethod<?> answer(Message message, Bot bot){
       
        String command = message.getText().substring(1);
        CommandData commandData;

        try{
            commandData = CommandData.valueOf(command);
        } catch(Exception e){
            log.warn("Unsupported command was received: " + command);
            return unknownCommand(message.getChatId(), command);

        }
        switch(commandData){
            //case start -> {return mainManager.answerCommand(message, bot);}
            case help -> {return showListOfCommands(message.getChatId());}
            case forecast -> {return forecastManager.answerCommand(message, bot);}
        }
        throw new UnsupportedOperationException();
    } 

    private BotApiMethod<?> showListOfCommands(Long chatId){
        return SendMessage.builder()
                           .chatId(chatId)
                           .text("""
                                   
                                📌 /start - начало взаимодействия с ботом
                                📌 /help - перечень комманд
                                📌 /forecast - главная страница прогноза
                                   """)
                                   .build();
    }

    private BotApiMethod<?> unknownCommand(Long chatId, String command){
        return SendMessage.builder()
                           .text("Я не знаю такой комманды {" + command+"} \n\n Ознакомьтесь с перечнем комманд при помощи комманды: \n /help" )
                           .chatId(chatId)
                           .build();
    }


}
