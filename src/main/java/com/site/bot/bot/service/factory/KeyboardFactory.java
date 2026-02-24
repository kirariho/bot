package com.site.bot.bot.service.factory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

@Component
public class KeyboardFactory {

    public InlineKeyboardMarkup getInlineKeyboard(
            List<Object> text,
            List<Integer> configuration,
            List<String> data
    ) {
        if(text.size() != data.size() || text.size() != configuration
                .stream()
                .reduce(0, Integer::sum)
            ) {return null;}

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        int index = 0;
        for (Integer rowNumber : configuration){
            List<InlineKeyboardButton> row = new ArrayList<>();
            for(int i = 0; i < rowNumber; i++){
                InlineKeyboardButton button = new InlineKeyboardButton();
                button.setText((String)text.get(index));
                button.setCallbackData(data.get(index));
                row.add(button);
                index ++;
            }
            keyboard.add(row);
        }
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    public ReplyKeyboardMarkup getReplyKeyboard(
        List<String> text,
        List<Integer> configuration
    ){
        if (text.size() != configuration
                .stream()
                .reduce(0, Integer:: sum)) {return null;}
    
        List<KeyboardRow> keyboard = new ArrayList<>();
        int index = 0;
        for (Integer rowNumber : configuration){
            KeyboardRow row = new KeyboardRow();
            for (int i = 0; i < rowNumber; i++){
                KeyboardButton button = new KeyboardButton();
                button.setText(text.get(index));
                row.add(button);
                index ++;
            }
            keyboard.add(row);
        }
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

}
