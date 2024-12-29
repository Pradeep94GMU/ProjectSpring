package com.pradeep.ExternalAPi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class WeatherService {
    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherResponse getWeather(String city) {
        String url = String.format("%s?q=%s&appid=%s&units=metric", apiUrl, city, apiKey);

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        // Parse response to create WeatherResponse
        WeatherResponse weather = new WeatherResponse();
        weather.setCity(city);
        Map<String, Object> main = (Map<String, Object>) response.get("main");
        weather.setTemperature((Double) main.get("temp"));
        weather.setDescription((String) ((Map<String, Object>) ((List<?>) response.get("weather")).get(0)).get("description"));

        return weather;
    }
}
