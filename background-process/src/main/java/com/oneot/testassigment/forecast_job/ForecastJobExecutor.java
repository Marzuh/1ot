package com.oneot.testassigment.forecast_job;

import com.oneot.testassigment.domain.forecast.ForecastService;
import com.oneot.testassigment.weather_api_client.WeatherApiClient;
import com.oneot.testassigment.weather_api_client.dto.XmlForecasts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
@EnableScheduling
public class ForecastJobExecutor {

    private final ForecastService forecastService;

    private final WeatherApiClient weatherApiClient;

    @Scheduled(cron = "${forecast-job-scheduler.cron-expression}", zone = "UTC")
    @SchedulerLock(name = "fetchApiForecast")
    public void fetchForecasts() {
        log.info("Scheduled job with name = \"fetchApiForecast\" started.");
        XmlForecasts forecasts = weatherApiClient.fetchForecasts();
        forecastService.saveFetchedForecasts(forecasts);
    }
}
