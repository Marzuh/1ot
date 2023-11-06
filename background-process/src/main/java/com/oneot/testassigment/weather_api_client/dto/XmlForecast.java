package com.oneot.testassigment.weather_api_client.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@JacksonXmlRootElement(localName = "forecast")
public class XmlForecast {

    @JacksonXmlProperty(isAttribute = true)
    private String date;

    @JacksonXmlProperty(localName = "day")
    private XmlDayForecast day;

    @JacksonXmlProperty(localName = "night")
    private XmlNightForecast night;
}
