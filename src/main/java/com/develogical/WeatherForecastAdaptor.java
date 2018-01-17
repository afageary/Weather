package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;

public class WeatherForecastAdaptor implements WeatherForecastInterface {
    public String getOutlook(String location, String day) {
        Forecaster forecaster = new Forecaster();
        // do some translation
        Forecast londonForecast = forecaster.forecastFor(Region.BIRMINGHAM, Day.FRIDAY);
        return londonForecast.summary();
    }

    public int getTemperature(String location, String day) {
        Forecaster forecaster = new Forecaster();
        // do some translation
        Forecast londonForecast = forecaster.forecastFor(Region.BIRMINGHAM, Day.FRIDAY);
        return londonForecast.temperature();
    }

}
