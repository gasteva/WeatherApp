package il.ac.hit.weather.model;

import java.util.Date;

/**
 * Created by galya on 12/28/2016.
 */
public class WeatherData {
    private long weatherID;
    private String weatherMain;
    private String weatherDescription;
    private String weatherIcon;

    private double mainTemp;
    private int mainPressure;
    private int mainHumidity;
    private double mainTempMin;
    private double mainTempMax;
    private int mainSeaLevel;
    private int mainGroundLevel;

    private double windSpeed;
    private double windDeg;

    private double cloudiness;

    private double rainVolume;

    private double snowVolume;

    private Date date;
    private Date sunrise;
    private Date sunset;

    private Date dtTXT;

    private String locationJSON;


    public WeatherData() {
    }

    public long getWeatherID() {
        return weatherID;
    }

    public void setWeatherID(long weatherID) {
        this.weatherID = weatherID;
    }

    public String getWeatherMain() {
        return weatherMain;
    }

    public void setWeatherMain(String weatherMain) {
        this.weatherMain = weatherMain;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public double getMainTemp() {
        return mainTemp;
    }

    public void setMainTemp(double mainTemp) {
        this.mainTemp = mainTemp;
    }

    public int getMainPressure() {
        return mainPressure;
    }

    public void setMainPressure(int mainPressure) {
        this.mainPressure = mainPressure;
    }

    public int getMainHumidity() {
        return mainHumidity;
    }

    public void setMainHumidity(int mainHumidity) {
        this.mainHumidity = mainHumidity;
    }

    public double getMainTempMin() {
        return mainTempMin;
    }

    public void setMainTempMin(double mainTempMin) {
        this.mainTempMin = mainTempMin;
    }

    public double getMainTempMax() {
        return mainTempMax;
    }

    public void setMainTempMax(double mainTempMax) {
        this.mainTempMax = mainTempMax;
    }

    public int getMainSeaLevel() {
        return mainSeaLevel;
    }

    public void setMainSeaLevel(int mainSeaLevel) {
        this.mainSeaLevel = mainSeaLevel;
    }

    public int getMainGroundLevel() {
        return mainGroundLevel;
    }

    public void setMainGroundLevel(int mainGroundLevel) {
        this.mainGroundLevel = mainGroundLevel;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(double windDeg) {
        this.windDeg = windDeg;
    }

    public double getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(double cloudiness) {
        this.cloudiness = cloudiness;
    }

    public double getRainVolume() {
        return rainVolume;
    }

    public void setRainVolume(double rainVolume) {
        this.rainVolume = rainVolume;
    }

    public double getSnowVolume() {
        return snowVolume;
    }

    public void setSnowVolume(double snowVolume) {
        this.snowVolume = snowVolume;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getSunrise() {
        return sunrise;
    }

    public void setSunrise(Date sunrise) {
        this.sunrise = sunrise;
    }

    public Date getSunset() {
        return sunset;
    }

    public void setSunset(Date sunset) {
        this.sunset = sunset;
    }

    public String getLocationJSON() {
        return locationJSON;
    }

    public void setLocationJSON(String locationJSON) {
        this.locationJSON = locationJSON;
    }

    public Date getDtTXT() {
        return dtTXT;
    }

    public void setDtTXT(Date dtTXT) {
        this.dtTXT = dtTXT;
    }
}
