package com.oneot.testassigment.forecast_job;

import com.oneot.testassigment.domain.forecast.ForecastService;
import com.oneot.testassigment.weather_api_client.WeatherApiClient;
import com.oneot.testassigment.weather_api_client.dto.Forecasts;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class ForecastJobExecutor {

    private final ForecastService forecastService;

    private final WeatherApiClient weatherApiClient;



    @Scheduled(cron = "${forecast-job-scheduler.cron-expression}", zone = "UTC")
    @SchedulerLock(name = "fetchApiForecast")
    public void fetchForecasts() {
        Forecasts forecasts = weatherApiClient.fetchAndUpdateForecasts();
        forecastService.saveFetchedForecasts(forecasts);
    }
}
