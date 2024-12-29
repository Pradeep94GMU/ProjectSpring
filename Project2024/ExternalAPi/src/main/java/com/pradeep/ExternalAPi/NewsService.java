package com.pradeep.ExternalAPi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NewsService {
    @Value("${news.api.key}")
    private String apiKey;

    @Value("${news.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public NewsResponse getTopNews(String country, String category) {
        String url = String.format("%s?access_key=%s&countries=%s&categories=%s&languages=en", apiUrl, apiKey, country, category);

        String rawResponse = restTemplate.getForObject(url, String.class);
        System.out.println("Raw API Response: " + rawResponse);

        NewsResponse response = restTemplate.getForObject(url, NewsResponse.class);
        if (response != null) {
            System.out.println("Parsed Articles: " + response.getData());
        } else {
            System.out.println("No news data found.");
        }

        return response;
    }

}
