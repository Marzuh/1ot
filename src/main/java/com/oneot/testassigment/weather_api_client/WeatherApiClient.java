package com.oneot.testassigment.weather_api_client;

import com.oneot.testassigment.weather_api_client.dto.Forecasts;
import lombok.AllArgsConstructor;
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


    @Retryable(retryFor = {HttpClientErrorException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000))
    public Forecasts fetchAndUpdateForecasts() throws HttpClientErrorException {
        ResponseEntity<Forecasts> response = restTemplate.getForEntity(weatherApiProperties.getApiUri(), Forecasts.class);
        return response.getBody();
    }

    @Recover
    public void recover(HttpClientErrorException e) {
        //TODO: add logger
        //TODO: Add tests
        System.out.println("weather fetch throw exception " + e);
    }

}
