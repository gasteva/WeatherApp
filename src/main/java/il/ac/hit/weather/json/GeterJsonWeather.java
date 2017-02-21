package il.ac.hit.weather.json;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import il.ac.hit.weather.exception.CityNotFoundException;

/**
 * Created by galya on 1/1/2017.
 */
public class GeterJsonWeather {

    public static String GetJSON(String url) throws CityNotFoundException {
        URL nUrl;
        String json = new String();

        try {
            nUrl = new URL(url);
            Scanner scan = new Scanner(nUrl.openStream());
            while (scan.hasNext())
                json += scan.nextLine();

            scan.close();

        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {

        	throw new CityNotFoundException(e.getMessage(), e);
        }
        
        return json;
    }
}
