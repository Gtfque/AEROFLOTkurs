package org.aeroflot.service;

import org.aeroflot.model.Flight;
import org.aeroflot.model.Weather;

public class Dispatcher {

    public void cancelFlight(Flight flight, Weather weather, Object failure) {
        if (weather.isBadWeather()) {
            flight.cancelFlight();
            System.out.println("Рейс отменен из-за плохих погодных условий.");
        } else {
            System.out.println("Рейс не отменен, погода хорошая.");
        }
    }
}

