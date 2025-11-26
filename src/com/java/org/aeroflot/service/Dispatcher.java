package org.aeroflot.service;

import org.aeroflot.model.Flight;
import org.aeroflot.model.Weather;
import org.aeroflot.model.TechnicalFailure;

public class Dispatcher {

    public void cancelFlight(Flight flight, Weather weather, TechnicalFailure failure) throws FlightException {
        if (weather.isBadWeather()) {
            flight.cancelFlight();
            System.out.println("Рейс отменен из-за плохих погодных условий.");
        } else if (failure != null) {
            flight.cancelFlight();
            System.out.println("Рейс отменен из-за неисправности: " + failure.getDescription());
        } else {
            System.out.println("Рейс выполняется по плану.");
        }
    }
}