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

    @Test
    public void canRetrieveDifferentValues() {
        WeatherForecastInterface delegate = mock(WeatherForecastInterface.class);

        when(delegate.getTemperature("here", "today")).thenReturn(1);
        when(delegate.getTemperature("somewhere", "tomorrow")).thenReturn(999);

        WeatherForecastCache weatherForecastCache = new WeatherForecastCache(delegate);

        weatherForecastCache.getTemperature("here", "today");

        int actualTemperature = weatherForecastCache.getTemperature("somewhere", "tomorrow");

        assertThat(actualTemperature, equalTo(999));
        verify(delegate, times(1)).getTemperature("here", "today");
        verify(delegate, times(1)).getTemperature("somewhere", "tomorrow");
    }

    @Test
    public void cacheSizeIsLimitedToSpecificValue() {
        WeatherForecastInterface delegate = mock(WeatherForecastInterface.class);

        when(delegate.getTemperature("here", "today")).thenReturn(1);
        when(delegate.getTemperature("somewhere", "tomorrow")).thenReturn(999);
        when(delegate.getTemperature("Khartoum", "tomorrow")).thenReturn(40);
        when(delegate.getTemperature("Oxford", "tomorrow")).thenReturn(2);
        WeatherForecastCache weatherForecastCache = new WeatherForecastCache(delegate);

        weatherForecastCache.getTemperature("here", "today");
        weatherForecastCache.getTemperature("Khartoum", "tomorrow");
        weatherForecastCache.getTemperature("Oxford", "tomorrow");
        verify(delegate, times(1)).getTemperature("here", "today");
        int actualTemperature = weatherForecastCache.getTemperature("here", "today");
        verify(delegate, times(2)).getTemperature("here", "today");
        assertThat(actualTemperature, equalTo(1));

    }
}