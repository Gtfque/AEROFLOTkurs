package org.aeroflot.service;

import org.aeroflot.model.Flight;
import org.aeroflot.model.Airplane;
import org.aeroflot.model.CrewMember;

import java.util.List;

public class Administrator {

    public Flight createFlight(String flightNumber, Airplane airplane, List<CrewMember> crew) throws FlightException {
        if (airplane == null) {
            throw new FlightException("Нельзя создать рейс без самолета!");
        }
        if (crew == null || crew.isEmpty()) {
            throw new FlightException("Нельзя создать рейс без экипажа!");
        }
        return new Flight(flightNumber, airplane, crew);
    }
}
