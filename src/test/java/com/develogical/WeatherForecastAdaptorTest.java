package com.develogical;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.mock;

public class WeatherForecastAdaptorTest {

    @Test
    public void testServiceBeingCalled() throws Exception {
        WeatherForecastInterface delegate = mock(WeatherForecastInterface.class);

        WeatherForecastAdaptor adaptor = new WeatherForecastAdaptor(delegate);
        adaptor.getOutlook("London", "Monday");
    }
}
