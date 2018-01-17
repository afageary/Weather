package com.develogical;

import java.util.*;

public class WeatherForecastCache implements WeatherForecastInterface {
    private WeatherForecastInterface delegate;

//    Map<Collection, Integer> TemperatureCache = new HashMap<>();
//    Map<Collection, String> outlookCache = new HashMap<>();
    private String outlook;
    private int temperature=-1;

    public WeatherForecastCache(WeatherForecastInterface delegate) {
        this.delegate = delegate;
    }

    public String getOutlook(String region, String day) {
//        Collection key = new ArrayList();
//        key.add(region);
//        key.add(day);
        if (outlook != null) {
            return outlook;
        }

        outlook = delegate.getOutlook(region, day);
//        outlookCache.put(key, outlook);

        return outlook;
    }


    public int getTemperature(String region, String day) {
//        Collection key = new ArrayList();
//        key.add(region);
//        key.add(day);

        if (temperature != -1) {
            return temperature;
        }
        temperature = delegate.getTemperature(region, day);
//        TemperatureCache.put(key, temperature);

        return temperature;
    }
}
