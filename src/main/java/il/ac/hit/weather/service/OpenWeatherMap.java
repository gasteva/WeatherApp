package il.ac.hit.weather.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by galya on 1/2/2017.
 */
public class OpenWeatherMap {

    private static final String URL_API = "http://api.openweathermap.org/data/2.5/";
    private static String URL_CURRENT;

    private static final String PARAM_CITY_NAME = "q=";
    private static final String PARAM_CITY_ID = "id=";
    private static final String PARAM_UNITS = "units=";
    private static final String PARAM_APPID = "appId=";
    private static final String ENCODING = "UTF-8";
    private static final String UNITS = "metric";
    private static final String APPID = "09436d0b2b54f4d8724b3e0203b9e0e3";

    //http://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=09436d0b2b54f4d8724b3e0203b9e0e3

    /**
     * Building the link to get current information
     *
     * @param cityName
     * @return (string )link with specified name of city
     * @throws UnsupportedEncodingException
     */
    public static String stringToGetCurrentWeatherByCityName(String cityName) throws UnsupportedEncodingException {
        URL_CURRENT = "weather?";
        return new StringBuilder()
                .append(URL_API).append(URL_CURRENT)
                .append(PARAM_CITY_NAME).append(URLEncoder .encode(cityName, ENCODING)).append("&")
                // .append(PARAM_MODE).append(MODE).append("&")
                .append(PARAM_UNITS).append(UNITS).append("&")
                //  .append(PARAM_LANG).append(LANG).append("&")
                .append(PARAM_APPID).append(APPID)
                .toString();
    }

    /**
     * Building the link to get current information
     *
     * @param cityCode
     * @return (string )link with specified code of city
     */
    public static String stringCurrentWeatherByCityCode(long cityCode) {
        URL_CURRENT = "weather?";
        return new StringBuilder()
                .append(URL_API).append(URL_CURRENT)
                .append(PARAM_CITY_ID).append(Long.toString(cityCode)).append("&")
                //.append(PARAM_MODE).append(MODE).append("&")
                .append(PARAM_UNITS).append(UNITS).append("&")
                //    .append(PARAM_LANG).append(LANG).append("&")
                .append(PARAM_APPID).append(APPID)
                .toString();
    }

    /**
     * Building the link to get information
     *
     * @param cityname
     * @param country
     * @return (string )link with specified name of city and country
     * @throws UnsupportedEncodingException
     */

    public static String stringCurrentWeatherByCityNameAndCountry(String cityname, String country) throws UnsupportedEncodingException {
        URL_CURRENT = "weather?";
        return new StringBuilder()
                .append(URL_API).append(URL_CURRENT)
                .append(PARAM_CITY_NAME).append(URLEncoder.encode(cityname, ENCODING)).append(",")
                .append(country)
                .append("&")
                //.append(PARAM_MODE).append(MODE).append("&")
                .append(PARAM_UNITS).append(UNITS).append("&")
                //    .append(PARAM_LANG).append(LANG).append("&")
                .append(PARAM_APPID).append(APPID)
                .toString();
    }

    /**
     * Building the link to get information
     *
     * @param cityName
     * @return (string )link with specified name of city
     * @throws UnsupportedEncodingException
     */
    public static String stringToGetWeatherByCityName(String cityName) throws UnsupportedEncodingException {
        URL_CURRENT = "forecast?";
        return new StringBuilder()
                .append(URL_API).append(URL_CURRENT)
                .append(PARAM_CITY_NAME).append(URLEncoder .encode(cityName, ENCODING)).append("&")
                // .append(PARAM_MODE).append(MODE).append("&")
                .append(PARAM_UNITS).append(UNITS).append("&")
                //  .append(PARAM_LANG).append(LANG).append("&")
                .append(PARAM_APPID).append(APPID)
                .toString();
    }

    /**
     * Building the link to get information
     *
     * @param cityCode
     * @return (string )link with specified code of city
     */
    public static String stringWeatherByCityCode(long cityCode) {
        URL_CURRENT = "forecast?";
        return new StringBuilder()
                .append(URL_API).append(URL_CURRENT)
                .append(PARAM_CITY_ID).append(Long.toString(cityCode)).append("&")
                //.append(PARAM_MODE).append(MODE).append("&")
                .append(PARAM_UNITS).append(UNITS).append("&")
                //    .append(PARAM_LANG).append(LANG).append("&")
                .append(PARAM_APPID).append(APPID)
                .toString();
    }

    /**
     * Building the link to get information
     *
     * @param cityname
     * @param country
     * @return (string )link with specified name of city and country
     * @throws UnsupportedEncodingException
     */

    public static String stringWeatherByCityNameAndCountry(String cityname, String country) throws UnsupportedEncodingException {
        URL_CURRENT = "forecast?";
        return new StringBuilder()
                .append(URL_API).append(URL_CURRENT)
                .append(PARAM_CITY_NAME).append(URLEncoder.encode(cityname, ENCODING)).append(",")
                .append(country)
                .append("&")
                //.append(PARAM_MODE).append(MODE).append("&")
                .append(PARAM_UNITS).append(UNITS).append("&")
                //    .append(PARAM_LANG).append(LANG).append("&")
                .append(PARAM_APPID).append(APPID)
                .toString();}

    //http://api.openweathermap.org/data/2.5/forecast?lat=35&lon=139&appid=09436d0b2b54f4d8724b3e0203b9e0e3
}
