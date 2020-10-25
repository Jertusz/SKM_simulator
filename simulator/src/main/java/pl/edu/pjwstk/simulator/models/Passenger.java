package pl.edu.pjwstk.simulator.models;

import java.util.concurrent.ThreadLocalRandom;

public class Passenger {
    String firstName;
    String lastName;
    Station targetStation;

    public Passenger() {

        firstName = String.valueOf(FirstName.VALUES.get(ThreadLocalRandom.current().nextInt(0, 100)));
        lastName = String.valueOf(LastName.VALUES.get(ThreadLocalRandom.current().nextInt(0, 94)));
        targetStation = Station.VALUES.get(ThreadLocalRandom.current().nextInt(0, 14));
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Station getTargetStation() {
        return targetStation;
    }

    public void setTargetStation(Station targetStation) {
        this.targetStation = targetStation;
    }

}
