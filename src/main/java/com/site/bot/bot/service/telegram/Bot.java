package com.site.bot.bot.service.telegram;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.site.bot.bot.service.UpdateDispatcher;

import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Bot extends TelegramWebhookBot{
    TelegramProperties telegramProperties;
    UpdateDispatcher updateDispatcher;

    public Bot(TelegramProperties telegramProperties, UpdateDispatcher updateDispatcher){
        super(telegramProperties.getToken());
        this.telegramProperties = telegramProperties;
        this.updateDispatcher = updateDispatcher;
    }

    @Override
    public String getBotPath(){
        return telegramProperties.getUrl();
    }

    @Override
    public String getBotUsername(){
        return telegramProperties.getName();
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update){
        return updateDispatcher.distribute(update, this);
    }
    
}
