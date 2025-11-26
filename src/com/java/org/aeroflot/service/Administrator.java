package org.aeroflot.service;

import org.aeroflot.model.Flight;
import org.aeroflot.model.Airplane;
import org.aeroflot.model.CrewMember;

import java.util.List;

public class Administrator {

    public Flight createFlight(String flightNumber, Airplane airplane, List<CrewMember> crew) {
        return new Flight(flightNumber, airplane, crew);
    }
}
