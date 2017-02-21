package il.ac.hit.weather.factory;

import il.ac.hit.weather.model.*;
import il.ac.hit.weather.exception.*;

import java.util.List;

/**
 * Created by galya on 12/28/2016.
 */
public interface IWeatherDataService {
    /**
     * Return the Weather Data object
     * detailed information about the weather in accordance with the specified location
     *
     * @param location
     * @return object WeatherData
     * @throws WeatherDataServiceException
     */
    public List<WeatherData> getWeatherData(Location location) throws WeatherDataServiceException;
}
