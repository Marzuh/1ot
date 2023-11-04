package com.oneot.testassigment.forecast_job;

import com.oneot.testassigment.forecast.Forecast;
import com.oneot.testassigment.forecast.ForecastRepository;
import com.oneot.testassigment.forecast.TimeOfDay;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class ForecastJobExecutor {

    private final ForecastRepository forecastRepository;

    @Scheduled(cron = "${forecast-job-scheduler.cron-expression}", zone = "UTC")
    @SchedulerLock(name = "fetchApiForecast")
    public void fetchForecasts() {
        Forecast forecast = new Forecast().setDate(LocalDate.now()).setTimeOfDay(TimeOfDay.DAY);
        forecast.setCreatedAt(Instant.now());
        forecast.setCreatedBy("Alex");
        forecast.setModifiedAt(Instant.now());
        forecast.setModifiedBy("Alex");

        System.out.println("scheduled job, saving forecast " + forecast.getCreatedAt());
        forecastRepository.save(forecast);
    }
}
