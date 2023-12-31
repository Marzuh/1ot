package com.oneot.testassigment.weather_api_client.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class XmlBaseForecast {

    @JacksonXmlProperty(localName = "phenomenon")
    private String phenomenon;

    @JacksonXmlProperty(localName = "tempmin")
    private Integer tempMin;

    @JacksonXmlProperty(localName = "tempmax")
    private Integer tempMax;

    @JacksonXmlProperty(localName = "text")
    private String text;

    @JacksonXmlProperty(localName = "peipsi")
    private String peipsi;
}
