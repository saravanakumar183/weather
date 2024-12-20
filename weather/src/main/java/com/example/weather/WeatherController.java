package com.example.weather;

import com.example.weather.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {

    private final WeatherService weatherService;

    @Value("${weather.api.key}")
    private String apiKey;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public String home() {
        return "weather";
    }

    @GetMapping("/weather")
    public String getWeather(@RequestParam String city, Model model) {
        WeatherResponse response = weatherService.getWeatherForecast(city, apiKey);
        model.addAttribute("city", response.getName());
        model.addAttribute("temperature", response.getMain().getTemp());
        model.addAttribute("description", response.getWeather()[0].getDescription());
        return "weather";
    }
}
