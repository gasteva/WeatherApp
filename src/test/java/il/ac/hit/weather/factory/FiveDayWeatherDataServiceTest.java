package il.ac.hit.weather.factory;

import il.ac.hit.weather.model.Location;
import il.ac.hit.weather.model.WeatherData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * test of method getWeatherData() in class FiveDayWeatherDataService implement IWeatherDataService interface
 * Created by galya & sergey on 1/13/2017.
 */
public class FiveDayWeatherDataServiceTest {
    private Location location1 = new Location();
    private Location location2=new Location();
    private List<WeatherData> weatherDataList1 = new ArrayList<WeatherData>();
    private List<WeatherData> weatherDataList2 = new ArrayList<WeatherData>();

    @Before
    public void setUp() throws Exception {
        location1.setCity("London");
        location1.setCountry("UK");
        location2.setCity("london");

    }

    @After
    public void tearDown() throws Exception {
        location1 = null;
        location2 = null;
        weatherDataList2 = null;
        weatherDataList1 = null;
    }

    @Test
    public void getWeatherData() throws Exception {
        WeatherData[] arr1 = new WeatherData[weatherDataList1.size()];
        weatherDataList1.toArray(arr1);
        WeatherData[] arr2 = new WeatherData[weatherDataList2.size()];
        weatherDataList2.toArray(arr2);
        weatherDataList1 = WeatherDataServiceFactory.getWeatherDataService("5 DAYS WEATHER").getWeatherData(location1);
        weatherDataList2 = FiveDayWeatherDataService.getInstance().getWeatherData(location1);
        assertArrayEquals("5 days weather data by city name and 5 days weather data by city name and counntry name", arr1,arr2);
    }
}