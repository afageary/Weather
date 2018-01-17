package com.develogical;

import com.weather.Forecast;

public interface WeatherForecastInterface {
    String getOutlook (String region, String day);
    int getTemperature (String region, String day);

}
