package com.site.bot.bot.service.manager;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import com.site.bot.bot.service.telegram.Bot;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MainManager {
    public BotApiMethod<?> answerQuery(CallbackQuery query, String[] data, Bot bot){
        return EditMessageText.builder()
        .text(
            """
                    Я - бот погоды 
                    Я создан в демонстрационных целях (*¯︶¯*)
                    """
        )
        .chatId(query.getMessage().getChatId())
        .messageId(query.getMessage().getMessageId())
        .build();
    }

}
