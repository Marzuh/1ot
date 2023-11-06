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
@JacksonXmlRootElement(localName = "day")
public class XmlDayForecast extends XmlBaseForecast {

    @JacksonXmlElementWrapper(localName = "day", useWrapping = false)
    @JacksonXmlProperty(localName = "place")
    private List<XmlPlace> places;

}
