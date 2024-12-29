package com.pradeep.ExternalAPi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    private final CombinedService combinedService;

    public ApiController(CombinedService combinedService) {
        this.combinedService = combinedService;
    }

    @GetMapping("/api/simplified-data")
    public SimplifiedResponse getSimplifiedData(
            @RequestParam String city,
            @RequestParam String country,
            @RequestParam(required = false, defaultValue = "general") String category
    ) {
        return combinedService.getSimplifiedData(city, country, category);
    }
}
