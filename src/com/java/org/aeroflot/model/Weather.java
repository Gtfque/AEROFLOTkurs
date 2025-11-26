package org.aeroflot.model;

public class Weather {
    private boolean badWeather;

    public Weather(boolean badWeather) {
        this.badWeather = badWeather;
    }

    public boolean isBadWeather() {
        return badWeather;
    }
}

