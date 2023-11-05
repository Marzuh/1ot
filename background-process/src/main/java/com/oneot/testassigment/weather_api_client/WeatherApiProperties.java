package com.oneot.testassigment.weather_api_client;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Getter
@RequiredArgsConstructor
@Validated
@ConfigurationProperties("weather-api")
public class WeatherApiProperties {

    @NotNull
    @URL
    private final String apiUri;

}
