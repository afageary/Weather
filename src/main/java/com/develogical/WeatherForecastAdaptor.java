package com.develogical;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Forecaster;
import com.weather.Region;

public class WeatherForecastAdaptor implements WeatherForecastInterface {

    public WeatherForecastAdaptor(WeatherForecastInterface service){

    }

    public String getOutlook (String location, String day) {

        Region region  = null;
        if (location.equals("London"))
            region = Region.LONDON;

        return null;
    }

    public int getTemperature (String location, String day) {

        return 0;
    }

}
