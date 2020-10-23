package pl.edu.pjwstk.simulator;

import org.junit.BeforeClass;
import org.junit.Test;
import pl.edu.pjwstk.simulator.models.Train;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TrainTests {
    private static Train train;
    @BeforeClass
    public static void init() throws IOException {
        train = new Train(0);
    }

    @Test
    public void getData() {
        System.out.println(train.whatDirection());
        System.out.println(train.getCompartments());
    }

    @Test
    public void exampleRoute() {
        ArrayList<Integer> passengers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 5, 4, 2, 1, 2, 3, 4, 1, 7, 8, 9, 8, 7, 9));
        train.addPassengers(passengers);
        for (int i = 0; i < 45; i++) {
            train.move();
            System.out.printf("~~~~~~~~~~~~~~~~%d~~~~~~~~~~~~~%n", train.getCurrentStation());
            for (int j = 0; j < train.getCompartments().size()-1; j++) {
                System.out.println(train.getCompartments().get(j).getPassengers());
            }
        }

    }
}
