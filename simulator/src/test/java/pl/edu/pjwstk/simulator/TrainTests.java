package pl.edu.pjwstk.simulator;

import org.junit.BeforeClass;
import org.junit.Test;
import pl.edu.pjwstk.simulator.models.Passenger;
import pl.edu.pjwstk.simulator.models.Train;

import java.util.ArrayList;

public class TrainTests {
    private static Train train;

    @BeforeClass
    public static void init() {
        train = new Train();
    }

    @Test
    public void getData() {
//        System.out.println(train.whatDirection());
//        System.out.println(train.getCompartments());
    }

    @Test
    public void exampleRoute() {
        ArrayList<Passenger> passengers = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            passengers.add(new Passenger());
        }
//        train.addPassengers(passengers);
//        for (int i = 0; i < 45; i++) {
//            train.move();
//            System.out.println(train.getCurrentStation());
//            for (int j = 0; j < train.getCompartments().size() - 1; j++) {
//                System.out.println(train.getCompartments().get(j).getPassengers());
//            }
//        }

    }
}
