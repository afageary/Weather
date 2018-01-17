package com.develogical;

import java.util.*;

public class WeatherForecastCache implements WeatherForecastInterface {
    private WeatherForecastInterface delegate;

    Map<Collection, Integer> TemperatureCache = new HashMap<>();
    Map<Collection, String> outlookCache = new HashMap<>();
    List<Collection> keys = new ArrayList<>();

    public WeatherForecastCache(WeatherForecastInterface delegate) {
        this.delegate = delegate;
    }

    public String getOutlook(String region, String day) {
        Collection key = new ArrayList();
        key.add(region);
        key.add(day);

        removeOldest(key);

        if (outlookCache.containsKey(key)) {
            return outlookCache.get(key);
        }
        String outlook = delegate.getOutlook(region, day);
        outlookCache.put(key, outlook);

        return outlook;
    }

    public int getTemperature(String region, String day) {
        Collection key = new ArrayList();
        key.add(region);
        key.add(day);

        removeOldest(key);

        if (TemperatureCache.containsKey(key)) {
            return TemperatureCache.get(key);
        }
        int temperature = delegate.getTemperature(region, day);
        TemperatureCache.put(key, temperature);

        return temperature;
    }

    private void removeOldest(Collection key) {
        if (keys.size() < 3) {
            keys.add(key);
        } else {
            outlookCache.remove(keys.get(0));
            TemperatureCache.remove(keys.get(0));
            keys.remove(0);
        }
    }
}
