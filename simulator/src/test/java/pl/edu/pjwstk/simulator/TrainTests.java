package pl.edu.pjwstk.simulator;

import org.junit.BeforeClass;
import org.junit.Test;
import pl.edu.pjwstk.simulator.models.Station;
import pl.edu.pjwstk.simulator.models.Train;

import java.util.ArrayList;
import java.util.Arrays;

public class TrainTests {
    private static Train train;

    @BeforeClass
    public static void init() {
        train = new Train(0, 5, 7);
    }

    @Test
    public void getData() {
        System.out.println(train.whatDirection());
        System.out.println(train.getCompartments());
    }

    @Test
    public void exampleRoute() {
        ArrayList<Station> passengers = new ArrayList<>(Arrays.asList(Station.GDANSK_OLIWA, Station.GDANSK_OLIWA, Station.GDANSK_STOCZNIA, Station.GDANSK_STOCZNIA, Station.GDYNIA_GLOWNA, Station.GDYNIA_ORLOWO, Station.GDYNIA_WZGORZE_SW_MAKSYMILIANA, Station.GDYNIA_WZGORZE_SW_MAKSYMILIANA, Station.GDANSK_WRZESZCZ));
        train.addPassengers(passengers);
        for (int i = 0; i < 45; i++) {
            train.move();
            System.out.println(train.getCurrentStation());
            for (int j = 0; j < train.getCompartments().size() - 1; j++) {
                System.out.println(train.getCompartments().get(j).getPassengers());
            }
        }

    }
}
