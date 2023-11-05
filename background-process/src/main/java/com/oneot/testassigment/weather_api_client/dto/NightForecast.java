package com.oneot.testassigment.weather_api_client.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;

import java.util.List;

@Getter
@JacksonXmlRootElement(localName = "night")
public class NightForecast extends BaseForecast {

    @JacksonXmlElementWrapper(localName = "night", useWrapping = false)
    @JacksonXmlProperty(localName = "place")
    private List<Place> places;

}
