package pl.edu.pjwstk.simulator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.pjwstk.simulator.models.Compartment;

import java.util.ArrayList;
import java.util.Arrays;

public class CompartmentTests {
    Compartment comp;
    @Before
    public void init(){
        comp = new Compartment(6);
    }

    @Test
    public void addTooMuchPassengers() {
        ArrayList<Integer> passengers= new ArrayList<>(Arrays.asList(1, 5, 3, 2, 4, 5, 6, 6, 2));
        comp.addPassengers(passengers);
        Assert.assertEquals(6, comp.getNumberOfPassengers());
    }

    @Test
    public void removePassengersOnDestination() {
        ArrayList<Integer> passengers= new ArrayList<>(Arrays.asList(1, 5, 5, 2, 4, 2));
        comp.addPassengers(passengers);
        comp.removePassengers(5);
        Assert.assertEquals(4, comp.getNumberOfPassengers());
    }
}
