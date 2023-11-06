package com.oneot.testassigment.utils;

import com.oneot.testassigment.weather_api_client.dto.XmlDayForecast;
import com.oneot.testassigment.weather_api_client.dto.XmlForecast;
import com.oneot.testassigment.weather_api_client.dto.XmlForecasts;
import com.oneot.testassigment.weather_api_client.dto.XmlNightForecast;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

public class TestUtil {
    public static String loadResource(String resourcePath) throws Exception {
        File file = ResourceUtils.getFile("classpath:" + resourcePath);
        return new String(Files.readAllBytes(file.toPath()));
    }

    public static XmlForecasts createTestForecasts() {
        return new XmlForecasts().setForecastList(
                List.of(
                        new XmlForecast().setDate("2023-11-07")
                                .setDay(createTestDay())
                                .setNight(createTestNight())
                )
        );
    }

    public static XmlDayForecast createTestDay() {
        return (XmlDayForecast) new XmlDayForecast()
                .setTempMin(1)
                .setTempMax(9)
                .setText("Test day text")
                .setPhenomenon("Test day phenomenon")
                .setPeipsi("Test day peipsi");
    }

    public static XmlNightForecast createTestNight() {
        return (XmlNightForecast) new XmlNightForecast()
                .setTempMin(2)
                .setTempMax(8)
                .setText("Test night text")
                .setPhenomenon("Test night phenomenon")
                .setPeipsi("Test night peipsi");
    }
}
