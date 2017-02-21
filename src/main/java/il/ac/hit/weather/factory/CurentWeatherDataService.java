package il.ac.hit.weather.factory;

import il.ac.hit.weather.exception.CityNotFoundException;
import il.ac.hit.weather.model.Location;
import il.ac.hit.weather.model.WeatherData;
import il.ac.hit.weather.exception.WeatherDataServiceException;
import il.ac.hit.weather.service.OpenWeatherMap;
import il.ac.hit.weather.json.GeterJsonWeather;
import il.ac.hit.weather.json.JsonCurentParserWeather;
import org.json.*;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by galya on 1/2/2017.
 */
public class CurentWeatherDataService implements IWeatherDataService {
	
	private String url;
	private static CurentWeatherDataService instance;
	private WeatherData data = new WeatherData();
	
	private CurentWeatherDataService() {}

    public static CurentWeatherDataService getInstance() {

        if (instance == null) {

            instance = new CurentWeatherDataService();
        }
        
        return instance;
    }

    public List<WeatherData> getWeatherData(Location location) throws WeatherDataServiceException {
    	
    	List<WeatherData> dataList = new LinkedList<WeatherData>();

        try {
            if (location.getCountry() != null) {

                url = OpenWeatherMap.stringCurrentWeatherByCityNameAndCountry(location.getCity(), location.getCountry());
                data = JsonCurentParserWeather.getWeatherData(GeterJsonWeather.GetJSON(url));
            } else {
                url = OpenWeatherMap.stringToGetCurrentWeatherByCityName(location.getCity());
                data = JsonCurentParserWeather.getWeatherData(GeterJsonWeather.GetJSON(url));
            }
            dataList.add(data);


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (CityNotFoundException e) {
        	
        	throw new WeatherDataServiceException(e.getMessage(), e);
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        }
        
        return dataList;
    }
}
