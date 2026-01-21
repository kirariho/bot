package com.site.bot.bot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherRequest {
    private String icon;
    private String code;
    private String description;
}
