package pl.edu.pjwstk.simulator.models;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Train {
    int id;
    Station currentStation;
    int station;
    boolean direction = new Random().nextBoolean();   //True if going to 15, false if going to 0
    int cooldown;
    int fillPercentage;
    HashMap<Integer, Compartment> compartments = new HashMap<>();

    public Train(int id, int compartments, int compartments_size) {
        createCompartments(compartments, compartments_size);
        this.station = ThreadLocalRandom.current().nextInt(0, 15);
        this.currentStation = Station.VALUES.get(station);
        checkDirection();
        this.cooldown = 0;
        this.id = id;
    }

    public Station getCurrentStation() {
        return currentStation;
    }

    public int getStation() {
        return station;
    }

    public int getId() {
        return id;
    }

    public HashMap<Integer, Compartment> getCompartments() {
        return compartments;
    }

    public void setCurrentStation(Station currentStation) {
        this.currentStation = currentStation;
    }

    public int getFillPercentage() {
        return fillPercentage;
    }

    private void createCompartments(int numberOfCompartments, int size) {
        for (int i = 0; i < numberOfCompartments; i++) {
            this.compartments.put(i, new Compartment(size));
        }
    }

    public boolean whatDirection() {
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addPassengers(ArrayList<Station> passengers) {
        int compartmentId = 0;
        while (!passengers.isEmpty() & compartmentId < compartments.size() - 1) {
            passengers = compartments.get(compartmentId).addPassengers(passengers);
            compartmentId += 1;
        }
        setFillPercentage();
    }

    public void removePassengers() {
        for (int i = 0; i < compartments.size(); i++) {
            compartments.get(i).removePassengers(getCurrentStation());
        }
    }

    public void move() {  // Moves the train by one square
        if (cooldown == 0) {
            if (whatDirection()) {    // Check the direction to go
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

    public void checkDirection() {   // Check if it's the end of road, true changes direction, adds cooldown
        if (getStation() == 14) {
            setDirection(false);
            this.cooldown = 2;
        } else if (getStation() == 0) {
            setDirection(true);
            this.cooldown = 2;
        }
    }

    public void setFillPercentage() {
        double totalPassengers = 0;
        for (Compartment compartment : this.compartments.values()) {
            totalPassengers += compartment.getNumberOfPassengers();
        }
        double totalSize = this.compartments.get(0).getSize();
        totalSize = totalSize * this.compartments.size();
        this.fillPercentage = (int) ((totalPassengers / totalSize) * 100);
    }
}
