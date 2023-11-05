package com.oneot.testassigment.weather_api_client;

import com.oneot.testassigment.weather_api_client.dto.Forecasts;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
@EnableConfigurationProperties({ WeatherApiProperties.class })
public class WeatherApiClient {

    private final WeatherApiProperties weatherApiProperties;

    private final RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(WeatherApiClient.class);

    @Retryable(retryFor = {HttpClientErrorException.class},
            maxAttempts = 5,
            backoff = @Backoff(delay = 1000))
    public Forecasts fetchAndUpdateForecasts() throws HttpClientErrorException {
        logger.debug("Start fetching new forecasts");
        ResponseEntity<Forecasts> response = restTemplate.getForEntity(weatherApiProperties.getApiUri(), Forecasts.class);
        logger.debug("Weather API response = {}", response);
        return response.getBody();
    }

    @Recover
    public void recover(HttpClientErrorException e) {
        logger.warn("New forecast did not fetched with exception: ", e);
    }

}
