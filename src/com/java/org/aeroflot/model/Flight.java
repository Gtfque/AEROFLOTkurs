package org.aeroflot.model;

import java.util.List;

public class Flight {
    private String flightNumber;
    private Airplane airplane;
    private List<CrewMember> crew;
    private boolean isCancelled;

    public Flight(String flightNumber, Airplane airplane, List<CrewMember> crew) {
        this.flightNumber = flightNumber;
        this.airplane = airplane;
        this.crew = crew;
        this.isCancelled = false;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public List<CrewMember> getCrew() {
        return crew;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void cancelFlight() {
        isCancelled = true;
    }

    @Override
    public String toString() {
        return "Flight [FlightNumber=" + flightNumber + ", Airplane=" + airplane + ", Crew=" + crew + ", Cancelled=" + isCancelled + "]";
    }
}

