package com.oneot.testassigment.weather_api_client.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@JacksonXmlRootElement(localName = "place")
public class XmlPlace {

    @JacksonXmlProperty(localName = "name")
    private String name;

    @JacksonXmlProperty(localName = "phenomenon")
    private String phenomenon;

    @JacksonXmlProperty(localName = "tempmin")
    private Integer tempMin;

    @JacksonXmlProperty(localName = "tempmax")
    private Integer tempMax;

}
