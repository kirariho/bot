package com.site.bot.bot.service.handler;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import com.site.bot.bot.service.telegram.Bot;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE, makeFinal = true)
public class MessageHandler {

    public BotApiMethod<?> answer(Message message, Bot bot){

        return SendMessage.builder()
                          .text("Моя хозяйка еще не научила меня человеческим словам, простите!")
                          .chatId(message.getChatId())
                          .build();
        
    }
    
}
