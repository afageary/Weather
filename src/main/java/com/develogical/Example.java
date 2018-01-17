package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;

public class Example {
    public static void main(String[] args) {
        WeatherForecastInterface weatherForecast = new WeatherForecastCache(new WeatherForecastAdaptor());

        System.out.println("weatherForecast = " + weatherForecast.getTemperature("London", "today"));
    }
}