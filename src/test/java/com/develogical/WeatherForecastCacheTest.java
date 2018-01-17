package com.develogical;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class WeatherForecastCacheTest {
    @Test
    public void delegatesOutlookWhenCacheIsEmpty() {
        WeatherForecastInterface delegate = mock(WeatherForecastInterface.class);
        String expectedOutlook = "sunny";
        when(delegate.getOutlook("here", "today")).thenReturn(expectedOutlook);

        WeatherForecastCache weatherForecastCache = new WeatherForecastCache(delegate);

        String actualOutlook = weatherForecastCache.getOutlook("here", "today");

        assertThat(actualOutlook, equalTo(expectedOutlook));
        verify(delegate, times(1)).getOutlook("here", "today");
    }

    @Test
    public void delegatesTemperatureWhenCacheIsEmpty() {
        WeatherForecastInterface delegate = mock(WeatherForecastInterface.class);
        int expectedTemperature = 11;
        when(delegate.getTemperature("here", "today")).thenReturn(expectedTemperature);

        WeatherForecastCache weatherForecastCache = new WeatherForecastCache(delegate);
        int actualTemperature = weatherForecastCache.getTemperature("here", "today");
        assertThat(actualTemperature, equalTo(expectedTemperature));
        verify(delegate, times(1)).getTemperature("here", "today");
    }

    @Test
    public void delegatesReturnsSameValueWhenCached() {
        WeatherForecastInterface delegate = mock(WeatherForecastInterface.class);
        int expectedTemperature = 11;

        when(delegate.getTemperature("here", "today")).thenReturn(expectedTemperature);

        WeatherForecastCache weatherForecastCache = new WeatherForecastCache(delegate);
        int actualTemperature = weatherForecastCache.getTemperature("here", "today");

        int actualTemperature2 = weatherForecastCache.getTemperature("here", "today");

        assertThat(actualTemperature, equalTo(actualTemperature2));
        verify(delegate, times(1)).getTemperature("here", "today");
    }

}