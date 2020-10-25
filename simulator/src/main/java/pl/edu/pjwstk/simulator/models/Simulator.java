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
            trains.add(new Train(i, properties.getPropValues().get("compartments"), properties.getPropValues().get("compartment_size")));
        }
    }

    private ArrayList<Station> addPassengers() {
        int numOfPassengers = ThreadLocalRandom.current().nextInt(2, 9);
        ArrayList<Station> passengers = new ArrayList<>();
        for (int i = 0; i < numOfPassengers; i++) {
            passengers.add(Station.VALUES.get(ThreadLocalRandom.current().nextInt(0, 14)));
        }
        return passengers;
    }

    public void move() {
        for (Train train : trains) {
            train.addPassengers(addPassengers());
            train.move();

        }
    }

    public ArrayList<Train> getTrains() {
        return trains;
    }
}
