package il.ac.hit.weather.factory;

import il.ac.hit.weather.exception.CityNotFoundException;
import il.ac.hit.weather.model.Location;
import il.ac.hit.weather.model.WeatherData;
import il.ac.hit.weather.exception.WeatherDataServiceException;
import il.ac.hit.weather.service.OpenWeatherMap;
import il.ac.hit.weather.json.GeterJsonWeather;
import il.ac.hit.weather.json.JsonFiveDaysParserWeather;
import org.json.JSONException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by galya on 1/2/2017.
 */
public class FiveDayWeatherDataService implements IWeatherDataService {
	
	private String url;
	private List<WeatherData> dataList = new LinkedList<WeatherData>();

	private static FiveDayWeatherDataService instance;
	
	private FiveDayWeatherDataService() {}

    public static FiveDayWeatherDataService getInstance() {

        if (instance == null) {

            instance = new FiveDayWeatherDataService();
        }
        
        return instance;
    }
    						
    public List<WeatherData> getWeatherData(Location location) throws WeatherDataServiceException {

        try {
            if (location.getCountry() != null) {

                url = OpenWeatherMap.stringWeatherByCityNameAndCountry(location.getCity(), location.getCountry());
                dataList = (List<WeatherData>) JsonFiveDaysParserWeather.getWeatherDataList(GeterJsonWeather.GetJSON(url));
            } else {
                url = OpenWeatherMap.stringToGetWeatherByCityName(location.getCity());
                dataList = (List<WeatherData>) JsonFiveDaysParserWeather.getWeatherDataList(GeterJsonWeather.GetJSON(url));
            }
        } catch (UnsupportedEncodingException e) {
        	e.printStackTrace();
        } catch (CityNotFoundException e) {
        	
        	throw new WeatherDataServiceException(e.getMessage(), e);
        	
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        }

        return dataList;
    }
}
