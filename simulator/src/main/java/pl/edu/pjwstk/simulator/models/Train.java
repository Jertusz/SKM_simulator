package pl.edu.pjwstk.simulator.models;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Train {
    SimulatorGetPropertyValues properties = new SimulatorGetPropertyValues();
    int id;
    Station currentStation;
    int station;
    boolean direction = new Random().nextBoolean();   //True if going to 15, false if going to 0
    int cooldown;
    HashMap<Integer, Compartment> compartments = new HashMap<>();

    public Train(int id) throws IOException {
        createCompartments(properties.getPropValues().get("compartments"));
        int station = ThreadLocalRandom.current().nextInt(0, 15);
        currentStation = Station.VALUES.get(station);
        checkDirection();
        cooldown=0;
        this.id = id;
    }

    public Station getCurrentStation() {
        return currentStation;
    }

    public int getStation() {
        return station;
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

    public void addPassengers(ArrayList<Station> passengers){
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
                station += 1;
            } else {
                station -= 1;
            }
            currentStation = Station.VALUES.get(station);
            checkDirection();
            removePassengers();
        } else {
            cooldown -= 1;
        }
    }

    public void checkDirection(){   // Check if it's the end of road, true changes direction, adds cooldown
        if(station==14){
            setDirection(false);
            cooldown = 2;
        } else if (station==0){
            setDirection(true);
            cooldown = 2;
        }
    }
}
