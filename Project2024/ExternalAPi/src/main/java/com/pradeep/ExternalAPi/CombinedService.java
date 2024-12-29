package com.pradeep.ExternalAPi;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CombinedService {
    private final WeatherService weatherService;
    private final NewsService newsService;

    public CombinedService(WeatherService weatherService, NewsService newsService) {
        this.weatherService = weatherService;
        this.newsService = newsService;
    }

    public SimplifiedResponse getSimplifiedData(String city, String country, String category) {
        WeatherResponse weatherResponse = weatherService.getWeather(city);
        NewsResponse newsResponse = newsService.getTopNews(country, category);

        // Transform weather data
        SimplifiedResponse.Weather weather = new SimplifiedResponse.Weather();
        weather.setCity(weatherResponse.getCity());
        weather.setTemperature(weatherResponse.getTemperature());
        weather.setDescription(weatherResponse.getDescription());

        // Transform news data
        List<SimplifiedResponse.News> newsList = newsResponse.getData().stream()
                .map(article -> {
                    SimplifiedResponse.News news = new SimplifiedResponse.News();
                    news.setTitle(article.getTitle());
                    news.setDescription(article.getDescription());
                    return news;
                })
                .collect(Collectors.toList());

        // Combine into a simplified response
        SimplifiedResponse simplifiedResponse = new SimplifiedResponse();
        simplifiedResponse.setWeather(weather);
        simplifiedResponse.setNews(newsList);

        return simplifiedResponse;
    }
}
