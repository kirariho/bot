package com.site.bot.bot.service.manager;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.site.bot.bot.dto.WeatherResponse;
import com.site.bot.bot.service.telegram.Bot;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ForecastManager {

  public BotApiMethod<?> answerCommand(Message message, Bot bot){
    return SendMessage.builder()
                      .text(
                        """
                            Введите название города на английском языке,
                            после чего выберите вариант прогноза ⬇️⬇️⬇️
                            """
                      )
                      .chatId(message.getChatId())
                      .build();
  }

    private BotApiMethod<?> sendResult(CallbackQuery query, String type, String sityName, Bot bot){
      try{
        bot.execute(
          SendMessage.builder()
                  .chatId(query.getMessage().getChatId())
                  .text("Выполняется запрос...")
                  .build()
        );
        bot.execute(
            DeleteMessage.builder()
                  .messageId(query.getMessage().getMessageId())
                  .chatId(query.getMessage().getChatId())
                  .build()
        );
    } catch(TelegramApiException e){
      log.error(e.getMessage());
    }
      StringBuilder strBuildr = new StringBuilder();
      return SendMessage.builder()
              .text(strBuildr.toString())
              .chatId(query.getMessage().getChatId())
              .build();  
    }

}
