package com.oneot.testassigment.weather_api_client.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@JacksonXmlRootElement(localName = "forecasts")
public class XmlForecasts {

    @JacksonXmlElementWrapper(localName = "forecasts", useWrapping = false)
    @JacksonXmlProperty(localName = "forecast")
    private List<XmlForecast> forecastList;
}
