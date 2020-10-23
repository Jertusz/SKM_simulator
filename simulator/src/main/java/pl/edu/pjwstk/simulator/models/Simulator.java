package pl.edu.pjwstk.simulator.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Simulator {
    int train_count;
    SimulatorGetPropertyValues properties = new SimulatorGetPropertyValues();
    ArrayList<Train> trains = new ArrayList<>();

    public Simulator() throws IOException {
        this.train_count = properties.getPropValues().get("train_count");
        createTrains();
    }

    private void createTrains() throws IOException {
        for (int i = 0; i < train_count; i++) {
            trains.add(new Train(i));
        }
    }

    private ArrayList<Integer> addPassengers() {
        int numOfPassengers = ThreadLocalRandom.current().nextInt(2, 9);
        ArrayList<Integer> passengers = new ArrayList<>();
        for (int i = 0; i < numOfPassengers; i++) {
            passengers.add(ThreadLocalRandom.current().nextInt(0,16));
        }
        return passengers;
    }

    public void move() {
        for(Train train: trains) {
            train.addPassengers(addPassengers());
            train.move();

        }
    }

    public void describeState() {
        for (Train train: trains) {
            System.out.printf("~~~~~~~Id:%d~~~~%d~~~~~~~~~~~~~%n", train.getId(), train.getCurrentStation());
            for (int j = 0; j < train.getCompartments().size()-1; j++) {
                System.out.println(train.getCompartments().get(j).getPassengers());
            }
        }
    }

}
