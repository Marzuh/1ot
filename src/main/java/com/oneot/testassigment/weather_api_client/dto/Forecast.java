package com.oneot.testassigment.weather_api_client.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;

@Getter
@JacksonXmlRootElement(localName = "forecast")
public class Forecast {

    @JacksonXmlProperty(isAttribute = true)
    private String date;

    @JacksonXmlProperty(localName = "day")
    private DayForecast day;

    @JacksonXmlProperty(localName = "night")
    private NightForecast night;
}
