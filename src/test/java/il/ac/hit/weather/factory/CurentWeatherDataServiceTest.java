package il.ac.hit.weather.factory;

import il.ac.hit.weather.json.GeterJsonWeather;
import il.ac.hit.weather.json.JsonCurentParserWeather;
import il.ac.hit.weather.model.Location;
import il.ac.hit.weather.model.WeatherData;
import il.ac.hit.weather.service.OpenWeatherMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * test of method getWeatherData() in class CurrentWeatherDataService implement IWeatherDataService interface
 * Created by galya & sergey on 1/13/2017.
 */
public class CurentWeatherDataServiceTest {
    private Location location1 = new Location();
    private Location location2=new Location();
    private  List<WeatherData> weatherDataList2 = new ArrayList<WeatherData>();
    private WeatherData weatherData1 = new WeatherData();


    @Before
    public void setUp() throws Exception {
        location1.setCity("tel-aviv");
        location1.setCountry("IL");
        location2.setCity("tel-aviv");


    }

    @After
    public void tearDown() throws Exception {
        location1 = null;
        location2 = null;
        weatherDataList2 = null;
        weatherData1 = null;
    }


    @Test
    public void getWeatherData() throws Exception {
        String url1 = OpenWeatherMap.stringCurrentWeatherByCityNameAndCountry(location1.getCity(),location1.getCountry());
        weatherData1 = JsonCurentParserWeather.getWeatherData(GeterJsonWeather.GetJSON(url1));
        weatherDataList2 = CurentWeatherDataService.getInstance().getWeatherData(location2);
        assertEquals("Current weather data by city and current weather data by country and city",weatherData1.getMainTemp(), weatherDataList2.get(0).getMainTemp(),0.1);
    }
}

