package il.ac.hit.weather.json;

import il.ac.hit.weather.model.WeatherData;
//import org.apache.log4j.Logger;
import org.json.*;

import java.util.Date;

/**
 * Created by galya on 1/2/2017.
 */
public class JsonCurentParserWeather {
    //static Logger logger;

    public static WeatherData getWeatherData(String json) throws JSONException {

        //logger.info(json.toString());
        WeatherData weatherData = new WeatherData();

        JSONObject jObj = new JSONObject(json);

        JSONObject sysObj = getObject("sys", jObj);

        long ss_secs = sysObj.optLong("sunrise", Long.MIN_VALUE);
        if (ss_secs != Long.MIN_VALUE) {
            weatherData.setSunrise(new Date(ss_secs * 1000));
        } else {
            weatherData.setSunrise(null);
        }

        long sr_secs = sysObj.optLong("sunset", Long.MIN_VALUE);
        if (sr_secs != Long.MIN_VALUE) {
            weatherData.setSunset(new Date(sr_secs * 1000));
        } else {
            weatherData.setSunset(null);
        }

        String country = getString("country", sysObj);

        JSONArray jArr = jObj.getJSONArray("weather");

        JSONObject JSONWeather = jArr.getJSONObject(0);
        weatherData.setWeatherID(getInt("id", JSONWeather));
        weatherData.setWeatherMain(getString("main", JSONWeather));
        weatherData.setWeatherDescription(getString("description", JSONWeather));
        weatherData.setWeatherIcon(getString("icon", JSONWeather));

        long dt_secs = jObj.optLong("dt", Long.MIN_VALUE);
        if (dt_secs != Long.MIN_VALUE) {
            weatherData.setDate(new Date(dt_secs * 1000));
        } else {
            weatherData.setDate(null);
        }

        JSONObject mainObj = getObject("main", jObj);
        weatherData.setMainHumidity(getInt("humidity", mainObj));
        weatherData.setMainPressure(getInt("pressure", mainObj));

        if(mainObj.has("temp_max"))
            weatherData.setMainTempMax(getFloat("temp_max", mainObj));
        else weatherData.setMainTempMax(0);

        if(mainObj.has("temp_min"))
            weatherData.setMainTempMin(getFloat("temp_min", mainObj));
        else weatherData.setMainTempMin(0);

        weatherData.setMainTemp(getFloat("temp", mainObj));

        String city = getString("name", jObj);

        JSONObject wObj = getObject("wind", jObj);
        weatherData.setWindSpeed(getFloat("speed", wObj));
        if(wObj.has("deg"))
            weatherData.setWindDeg(getFloat("deg", wObj));
        else
            weatherData.setWindDeg(0);

        JSONObject cObj = getObject("clouds", jObj);
        weatherData.setCloudiness(getInt("all", cObj));

        weatherData.setLocationJSON(new String(city + ", " + country));

        return weatherData;
    }


    protected static JSONObject getObject(String tagName, JSONObject jObj)  throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    protected static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }

    protected static float  getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(tagName);
    }

    protected static int  getInt(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getInt(tagName);
    }
}
