package il.ac.hit.weather.exception;

/**
 * Created by Galya on 12/28/2016.
 * Class WeatherDataServiceException
 */
public class WeatherDataServiceException extends Exception {

    private static final long serialVersionUID = 1L;

    public WeatherDataServiceException(String msg) {
        super(msg);
    }

    public WeatherDataServiceException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

}
