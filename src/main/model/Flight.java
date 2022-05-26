package main.model;



public class Flight {
    private String flight;
    private String flightTime;
    private String departure;
    private String destination;

    public String getDeparture() {
        return departure;
    }

    public String getFlight() {
        return flight;
    }

    public String getDestination() {
        return destination;
    }


    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public String getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(String flightTime) {
        this.flightTime = flightTime;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flight='" + flight + '\'' +
                ", flightTime='" + flightTime + '\'' +
                ", departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}
