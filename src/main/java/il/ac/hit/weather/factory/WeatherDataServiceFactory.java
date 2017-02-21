package il.ac.hit.weather.factory;

/**
 * Created by galya on 1/1/2017.
 */
public class WeatherDataServiceFactory {
	
	public static final String OPEN_WEATHER_MAP_CURRENT = "CURRENT WEATHER";
	public static final String OPEN_WEATHER_MAP_5_DAYS = "5 DAYS WEATHER";

    public WeatherDataServiceFactory() {
    }

    public static IWeatherDataService getWeatherDataService(String service){
        if(service == null){
            return null;
        }
        if(service.equalsIgnoreCase(OPEN_WEATHER_MAP_CURRENT)){
            return CurentWeatherDataService.getInstance();
        } else if(service.equalsIgnoreCase(OPEN_WEATHER_MAP_5_DAYS)){
                    return FiveDayWeatherDataService.getInstance();
                }
                return null;
            }
}
