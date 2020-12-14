package pl.edu.pjwstk.simulator.models;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pjwstk.simulator.repository.TrainRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Simulator {

    public Simulator() {
    }

    private ArrayList<Passenger> addPassengers() {
        int numOfPassengers = ThreadLocalRandom.current().nextInt(2, 9);
        ArrayList<Passenger> passengers = new ArrayList<>();
        for (int i = 0; i < numOfPassengers; i++) {
            passengers.add(new Passenger());
        }
        return passengers;
    }

    public void move() {
        TrainRepository trainRepository = null;
        trainRepository.findAll();
    }

}
