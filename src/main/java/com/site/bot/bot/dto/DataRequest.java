package com.site.bot.bot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataRequest {
    private int windSpeed;
    private int temperature;
    private int preciptationProbability;
    private WeatherRequest weather;
}
