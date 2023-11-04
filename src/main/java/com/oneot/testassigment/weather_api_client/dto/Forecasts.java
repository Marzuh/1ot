package com.oneot.testassigment.weather_api_client.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;

import java.util.List;

@Getter
@JacksonXmlRootElement(localName = "forecasts")
public class Forecasts {

    @JacksonXmlElementWrapper(localName = "forecasts", useWrapping = false)
    @JacksonXmlProperty(localName = "forecast")
    private List<Forecast> forecastList;
}
