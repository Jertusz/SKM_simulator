package pl.edu.pjwstk.simulator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.pjwstk.simulator.models.Compartment;
import pl.edu.pjwstk.simulator.models.Passenger;
import pl.edu.pjwstk.simulator.models.Station;

import java.util.ArrayList;
import java.util.Arrays;

public class CompartmentTests {
    Compartment comp;

    @Before
    public void init() {
        comp = new Compartment(6);
    }

    @Test
    public void addTooMuchPassengers() {

        ArrayList<Passenger> passengers = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            passengers.add(new Passenger());

        }
        comp.addPassengers(passengers);
        Assert.assertEquals(6, comp.getNumberOfPassengers());
    }
}
