package edu.neu.madcourse.numad21fa_sihengwei;

public class ItemWeather {

    private final String date;
    private final String state;
    private final String minTemp;
    private final String maxTemp;

    public ItemWeather(String date, String state, String minTemp, String maxTemp) {
        this.date = date;
        this.state = state;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    public String getDate() {
        return date;
    }

    public String getState() {
        return state;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }
}
