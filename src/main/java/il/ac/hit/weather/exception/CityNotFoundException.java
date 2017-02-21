package il.ac.hit.weather.exception;

/**
 * Created by Sergey on 2/16/2017.
 * Class CityNotFoundException
 */
public class CityNotFoundException extends Exception
{
	private static final long serialVersionUID = 1L;

	public CityNotFoundException(String msg) {
        super(msg);
    }

	public CityNotFoundException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
