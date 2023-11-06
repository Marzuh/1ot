package com.oneot.testassigment.weather_api_client;

import com.oneot.testassigment.config.TestRestTemplateConfiguration;
import com.oneot.testassigment.utils.TestUtil;
import com.oneot.testassigment.weather_api_client.dto.Forecasts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ExtendWith(SpringExtension.class)
@RestClientTest(WeatherApiClient.class)
@Import(TestRestTemplateConfiguration.class)
public class WeatherApiClientTest {

    @Autowired
    private WeatherApiClient weatherApiClient;

    @Autowired
    private MockRestServiceServer mockServer;

    @Autowired
    private RestTemplate restTemplate;

    String XML_DATA = TestUtil.loadResource("weatherApiResponse.xml");
    String URL = "http://fake.api";

    public WeatherApiClientTest() throws Exception {
    }


    @BeforeEach
    void init() {
        this.mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    @DisplayName("Success forecast fetching. Check data structure.")
    void fetchForecastsSucceedTest() throws Exception {
        // Given
        String xmlData = TestUtil.loadResource("weatherApiResponse.xml");
        String url = "http://fake.api";
        mockServer.expect(requestTo(URL))
                .andRespond(withSuccess(XML_DATA, MediaType.APPLICATION_XML));

        // When
        Forecasts response = weatherApiClient.fetchForecasts();

        // Then
        assertThat(response.getForecastList().size()).isEqualTo(4);
        assertThat(response.getForecastList()).allSatisfy(forecast -> {
            assertThat(forecast.getDate()).isIn(List.of("2023-11-07", "2023-11-08", "2023-11-09", "2023-11-10"));
        });

        assertThat(response.getForecastList()).anySatisfy(forecast -> {
            assertThat(forecast.getDate()).isEqualTo("2023-11-07");
            assertThat(forecast.getDay()).satisfies(dayForecast -> {
                assertThat(dayForecast.getPhenomenon()).isEqualTo("Moderate shower");
                assertThat(dayForecast.getTempMin()).isEqualTo(6);
                assertThat(dayForecast.getTempMax()).isEqualTo(10);
                assertThat(dayForecast.getText()).isEqualTo("Cloudy with some clear spells. Rain in many places." +
                        " In the forenoon locally fog. Southwest, west wind 3-9, in gusts up to 12 m/s. Air temperature 6..10°C.");
                assertThat(dayForecast.getPeipsi()).isEqualTo("Mainly southwest wind 3-8 m/s. Wave height up " +
                        "to 0.6 m. At times rain. Visibility moderate. Air temperature 5..7°C.");
                assertThat(dayForecast.getPlaces().size()).isEqualTo(6);
                assertThat(dayForecast.getPlaces()).allSatisfy(place -> {
                    assertThat(place.getName()).isIn(List.of("Harku", "Jõhvi", "Tartu", "Pärnu", "Kuressaare", "Türi"));
                    assertThat(place.getPhenomenon()).isIn(List.of("Moderate rain", "Moderate shower", "Light rain"));
                    assertThat(place.getTempMin()).isNull();
                    assertThat(place.getTempMax()).isBetween(8, 10);
                });
            });
            assertThat(forecast.getNight()).satisfies(nightForecast -> {
                assertThat(nightForecast.getPhenomenon()).isEqualTo("Moderate rain");
                assertThat(nightForecast.getTempMin()).isEqualTo(4);
                assertThat(nightForecast.getTempMax()).isEqualTo(9);
                assertThat(nightForecast.getText()).isEqualTo("Cloudy with some clear spells. Rain and fog in many places. " +
                        "Mainly westerly wind 3-9, on coast in gusts up to 12 m/s. Air temperature 4..9°C.");
                assertThat(nightForecast.getPeipsi()).isEqualTo("Southwest, west wind 3-8 m/s. Wave height up to 0.6 m. " +
                        "At times rain, possible fog. Visibility moderate or poor. Air temperature 4..7°C.");
                assertThat(nightForecast.getPlaces().size()).isEqualTo(6);
                assertThat(nightForecast.getPlaces()).allSatisfy(place -> {
                    assertThat(place.getName()).isIn(List.of("Harku", "Jõhvi", "Tartu", "Pärnu", "Kuressaare", "Türi"));
                    assertThat(place.getPhenomenon()).isIn(List.of("Moderate rain", "Moderate shower", "Light rain", "Fog"));
                    assertThat(place.getTempMax()).isNull();
                    assertThat(place.getTempMin()).isBetween(6, 8);
                });
            });
        });
    }

    @Test
    @DisplayName("Success fetch on third try.")
    void fetchForecastsRetryTwoThenSucceedTest() {
        // When
        mockServer.expect(ExpectedCount.times(2), requestTo(URL))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));
        mockServer.expect(requestTo(URL))
                .andRespond(withSuccess(XML_DATA, MediaType.APPLICATION_XML));

        Forecasts response = weatherApiClient.fetchForecasts();

        // Then
        assertThat(response.getForecastList().size()).isEqualTo(4);
        assertThat(response.getForecastList()).allSatisfy(forecast -> {
            assertThat(forecast.getDate()).isIn(List.of("2023-11-07", "2023-11-08", "2023-11-09", "2023-11-10"));
        });
    }
}