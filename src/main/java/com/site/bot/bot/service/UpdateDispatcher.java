package com.site.bot.bot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.site.bot.bot.service.handler.CallbackQueryHandler;
import com.site.bot.bot.service.handler.CommandHandler;
import com.site.bot.bot.service.handler.MessageHandler;
import com.site.bot.bot.service.telegram.Bot;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level =  AccessLevel.PRIVATE, makeFinal = true)
public class UpdateDispatcher {
    
    CallbackQueryHandler queryHandler;
    CommandHandler commandHandler;
    MessageHandler messageHandler;

    public BotApiMethod<?> distribute(Update update, Bot bot){
       
        if(update.hasCallbackQuery()){
            return queryHandler.answer(update.getCallbackQuery(), bot);
        }

        if(update.hasMessage()){
            Message message = update.getMessage();
            if(message.hasText()){
                String text = message.getText();
                if(text.charAt(0) == '/'){
                    return commandHandler.answer(update.getMessage(), bot);
                }

                return messageHandler.answer(update.getMessage(), bot);
            }
        }
         return null;
    }
}
