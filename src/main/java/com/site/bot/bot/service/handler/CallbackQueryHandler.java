package com.site.bot.bot.service.handler;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import com.site.bot.bot.data.QueryData;
import com.site.bot.bot.service.manager.ForecastManager;
import com.site.bot.bot.service.manager.MainManager;
import com.site.bot.bot.service.telegram.Bot;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CallbackQueryHandler {

    MainManager mainManager;
    ForecastManager forecastManager;
    
    public BotApiMethod<?> answer(CallbackQuery query, Bot bot){
       String[] data = query.getData().split("_"); 
       QueryData queryData;
       queryData = QueryData.valueOf(data[0]);

       switch(queryData){
        case main  -> {return mainManager.answerQuery(query, data, bot);}
       // case fc    -> {return forecastManager.answerQuery(query, data, bot);}
        case empty -> {return AnswerCallbackQuery.builder()
                                .callbackQueryId(query.getId())
                                .text("Я мусорная кнопка.")
                                .build();
        } 
       }
       throw new UnsupportedOperationException();
    }
}
