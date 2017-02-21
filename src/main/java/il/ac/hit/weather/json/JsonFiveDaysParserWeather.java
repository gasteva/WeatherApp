package il.ac.hit.weather.json;

import il.ac.hit.weather.model.WeatherData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by galya on 1/2/2017.
 */
public class JsonFiveDaysParserWeather extends JsonCurentParserWeather  {

    public static List<WeatherData> getWeatherDataList(String json) throws JSONException {
    	
        //logger.info(json.toString());
        List<WeatherData> weatherDataList = new LinkedList<WeatherData>();
        //WeatherData weatherData = new WeatherData();
        WeatherData weatherData;

        JSONObject jObj = new JSONObject(json);

        JSONObject cityObj = getObject("city", jObj);
        String cityName = getString("name", cityObj);
        
        //JSONObject countryObj = getObject("country", jObj);
        //String country = getString("country", countryObj);
        String country = getString("country", cityObj);


        JSONArray jList = jObj.getJSONArray("list");
        for (int n = 0; n < jList.length(); n++) {
        	
        	weatherData = new WeatherData();

            //long dt_secs = jObj.optLong("dt", Long.MIN_VALUE);
        	long dt_secs = jList.getJSONObject(n).optLong("dt", Long.MIN_VALUE);
        	
            if (dt_secs != Long.MIN_VALUE) {
                weatherData.setDate(new Date(dt_secs * 1000));
            } else {
                weatherData.setDate(null);
            }

            //JSONObject mainObj = getObject("main", jObj);
            JSONObject mainObj = getObject("main", jList.getJSONObject(n));
            
            weatherData.setMainHumidity(getInt("humidity", mainObj));
            weatherData.setMainPressure(getInt("pressure", mainObj));

            if (mainObj.has("temp_max"))
                weatherData.setMainTempMax(getFloat("temp_max", mainObj));
            else weatherData.setMainTempMax(0);

            if (mainObj.has("temp_min"))
                weatherData.setMainTempMin(getFloat("temp_min", mainObj));
            else weatherData.setMainTempMin(0);

            weatherData.setMainTemp(getFloat("temp", mainObj));

            //JSONArray jArr = jObj.getJSONArray("weather");
            JSONArray jArr = jList.getJSONObject(n).getJSONArray("weather");
            
            JSONObject JSONWeather = jArr.getJSONObject(0);
            weatherData.setWeatherID(getInt("id", JSONWeather));
            weatherData.setWeatherMain(getString("main", JSONWeather));
            weatherData.setWeatherDescription(getString("description", JSONWeather));
            weatherData.setWeatherIcon(getString("icon", JSONWeather));


            //JSONObject wObj = getObject("wind", jObj);
            JSONObject wObj = getObject("wind", jList.getJSONObject(n));
            
            weatherData.setWindSpeed(getFloat("speed", wObj));
            if (wObj.has("deg"))
                weatherData.setWindDeg(getFloat("deg", wObj));
            else
                weatherData.setWindDeg(0);

            //JSONObject cObj = getObject("clouds", jObj);
            JSONObject cObj = getObject("clouds", jList.getJSONObject(n));
            
            weatherData.setCloudiness(getInt("all", cObj));

            weatherData.setLocationJSON(new String(cityName + ", " + country));

            //weatherDataList.add(weatherData);

            //JSONObject dtTXTObj = getObject("dt_txt", jObj);
            
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
            
            //String dt = getString("dt_txt", dtTXTObj);
            String dt = getString("dt_txt", jList.getJSONObject(n));
            
            try {
                Date date = inputDateFormat.parse(dt);
                weatherData.setDtTXT(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            
            weatherDataList.add(weatherData);

        }
        return weatherDataList;
    }



}
