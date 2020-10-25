package pl.edu.pjwstk.simulator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.pjwstk.simulator.models.Compartment;
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
        ArrayList<Station> passengers = new ArrayList<>(Arrays.asList(Station.GDANSK_OLIWA, Station.GDANSK_OLIWA, Station.GDANSK_STOCZNIA, Station.GDANSK_STOCZNIA, Station.GDYNIA_GLOWNA, Station.GDYNIA_ORLOWO, Station.GDYNIA_WZGORZE_SW_MAKSYMILIANA, Station.GDYNIA_WZGORZE_SW_MAKSYMILIANA, Station.GDANSK_WRZESZCZ));
        comp.addPassengers(passengers);
        Assert.assertEquals(6, comp.getNumberOfPassengers());
    }

    @Test
    public void removePassengersOnDestination() {
        ArrayList<Station> passengers = new ArrayList<>(Arrays.asList(Station.GDANSK_OLIWA, Station.GDANSK_OLIWA, Station.GDANSK_STOCZNIA, Station.GDANSK_STOCZNIA, Station.GDYNIA_GLOWNA, Station.GDYNIA_ORLOWO));
        comp.addPassengers(passengers);
        comp.removePassengers(Station.GDANSK_OLIWA);
        Assert.assertEquals(4, comp.getNumberOfPassengers());
    }
}
