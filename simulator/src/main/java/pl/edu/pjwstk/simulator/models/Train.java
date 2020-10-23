package pl.edu.pjwstk.simulator.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Train {
    SimulatorGetPropertyValues properties = new SimulatorGetPropertyValues();
    int id;
    int currentStation;
    boolean direction = new Random().nextBoolean();   //True if going to 15, false if going to 0
    int cooldown;
    HashMap<Integer, Compartment> compartments = new HashMap<>();

    public Train(int id) throws IOException {
        createCompartments(properties.getPropValues().get("compartments"));
        this.currentStation = ThreadLocalRandom.current().nextInt(0, 16);
        checkDirection();
        cooldown=0;
        this.id = id;
    }

    public int getCurrentStation() {
        return currentStation;
    }

    private void createCompartments(Integer numberOfCompartments) throws IOException {
        for (int i=0; i<numberOfCompartments; i++) {
            this.compartments.put(i, new Compartment(properties.getPropValues().get("compartment_size")));
        }
    }

    public boolean whatDirection() {
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<Integer, Compartment> getCompartments() {
        return compartments;
    }

    public void addPassengers(ArrayList<Integer> passengers){
        int compartmentId = 0;
        while (!passengers.isEmpty() & compartmentId<compartments.size()-1){
            passengers = compartments.get(compartmentId).addPassengers(passengers);
            compartmentId += 1;
        }
    }

    public void removePassengers(){
        for (int i = 0; i < compartments.size(); i++) {
            compartments.get(i).removePassengers(getCurrentStation());
        }
    }

    public void move(){  // Moves the train by one square
        if(cooldown == 0){
            if(whatDirection()){    // Check the direction to go
                currentStation += 1;
            } else {
                currentStation -= 1;
            }
            checkDirection();
            removePassengers();
        } else {
            cooldown -= 1;
        }
    }

    public void checkDirection(){   // Check if it's the end of road, true changes direction, adds cooldown
        if(currentStation==15 || currentStation==0){
            setDirection(!direction);
            cooldown = 2;
        }
    }
}
