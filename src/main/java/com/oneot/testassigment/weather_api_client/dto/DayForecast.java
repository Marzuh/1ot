package com.oneot.testassigment.weather_api_client.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;

import java.util.List;

@Getter
@JacksonXmlRootElement(localName = "day")
public class DayForecast extends BaseForecast {

    @JacksonXmlElementWrapper(localName = "day", useWrapping = false)
    @JacksonXmlProperty(localName = "place")
    private List<Place> places;

}
