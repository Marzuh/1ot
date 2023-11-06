package com.oneot.testassigment.weather_api_client;

import com.oneot.testassigment.weather_api_client.dto.Forecasts;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@AllArgsConstructor
@EnableConfigurationProperties({ WeatherApiProperties.class })
public class WeatherApiClient {

    private final WeatherApiProperties weatherApiProperties;

    private final RestTemplate restTemplate;

    @Retryable(retryFor = {HttpClientErrorException.class},
            maxAttempts = 5,
            backoff = @Backoff(delay = 1000))
    public Forecasts fetchAndUpdateForecasts() throws HttpClientErrorException {
        log.debug("Start fetching new forecasts");
        ResponseEntity<Forecasts> response = restTemplate.getForEntity(weatherApiProperties.getApiUri(), Forecasts.class);
        log.debug("Weather API response = {}", response);
        return response.getBody();
    }

    @Recover
    public void recover(HttpClientErrorException e) {
        log.warn("New forecast did not fetched with exception: ", e);
    }

}
