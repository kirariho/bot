package com.site.bot.bot.service.manager;

import java.util.List;

import org.jvnet.hk2.annotations.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.site.bot.bot.dto.WeatherResponse;
import com.site.bot.bot.service.factory.KeyboardFactory;
import com.site.bot.bot.service.telegram.Bot;

import static com.site.bot.bot.data.QueryData.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ForecastManager {

  KeyboardFactory keyboardFactory;

  public BotApiMethod<?> answerCommand(Message message, Bot bot){
    return SendMessage.builder()
                      .text(
                        """
                            Введите название города на английском языке,
                            после чего выберите вариант прогноза ⬇️⬇️⬇️
                            """
                      )
                      .chatId(message.getChatId())
                      .replyMarkup(
                              keyboardFactory.getInlineKeyboard(
                                List.of("Ввести город"),
                                List.of(1),
                                List.of(fc_c_.name())
                              )
                      )
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
    //WeatherResponse weatherResponse = "h".equals(type) ? weatherService.getHoursByCityName(cityName) : weatherService.getDaysByCityName(cityName);
    StringBuilder strBuildr = new StringBuilder();
    
    
    return SendMessage.builder()
              .text(strBuildr.toString())
              .chatId(query.getMessage().getChatId())
              .replyMarkup(
                    keyboardFactory.getInlineKeyboard(
                            List.of("На главную"),
                            List.of(1),
                            List.of(main.name())
                    )
              )
              .build();  
    }

    private InlineKeyboardMarkup cityEnteredKeyboard (String current){
        
      return keyboardFactory.getInlineKeyboard(
              List.of(
                !current.isEmpty() ? current: "Поле ввода",
                "Q", "W", "E", "R", "T", "Y", "U", "I",
                "O", "P", "A", "S", "D", "F", "G", "H",
                "J", "K", "L", "Z", "X", "C", "V", "B",
                'N', "M", "-","/uD83D/uDEAB", "/uD83D/uDEAB", "/uD83D/uDEAB", "/uD83D/uDEAB", "/uD83D/uDEAB",
                "Ввод", "Стереть"
              ),
              List.of(1, 8, 8, 8, 8, 2),
              List.of(
                empty.name(),
                fc_c_.name() + current + "Q", fc_c_.name() + current + "W",
                fc_c_.name() + current + "E", fc_c_.name() + current + "R",
                fc_c_.name() + current + "T", fc_c_.name() + current + "Y",
                fc_c_.name() + current + "U", fc_c_.name() + current + "I",
                fc_c_.name() + current + "O", fc_c_.name() + current + "P",
                fc_c_.name() + current + "A", fc_c_.name() + current + "S",
                fc_c_.name() + current + "D", fc_c_.name() + current + "F",
                fc_c_.name() + current + "G", fc_c_.name() + current + "H",
                fc_c_.name() + current + "J", fc_c_.name() + current + "K",
                fc_c_.name() + current + "L", fc_c_.name() + current + "Z",
                fc_c_.name() + current + "X", fc_c_.name() + current + "C",
                fc_c_.name() + current + "V", fc_c_.name() + current + "B",
                fc_c_.name() + current + "N", fc_c_.name() + current + "M",
                fc_c_.name() + current + "-", empty.name(), empty.name(), empty.name(), 
                empty.name(), empty.name(), fc_c_.name() + current,
                current.length() <= 1 ? fc_c_.name() : fc_c_.name() + current.substring(0, current.length() - 1)

              )
      );
    }

}
