package pl.edu.pjwstk.simulator.models;

import pl.edu.pjwstk.simulator.service.DbEntity;

import javax.persistence.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Table(name = "trains")
public class Train implements DbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "station_id")
    Station station;
    int direction = ThreadLocalRandom.current().nextInt(0, 2);   //0 if going to station 15, 1 if going to station 0
    int cooldown;
    // TODO implement with relations
    // HashMap<Integer, Compartment> compartments = new HashMap<>();

    public Train() {

    }

    public Train(int id, int compartments, int compartments_size) {
//        createCompartments(compartments, compartments_size);
//        this.station = ThreadLocalRandom.current().nextInt(0, 15);
//        this.currentStation = Station.VALUES.get(station);
//        checkDirection();
//        this.cooldown = 0;
//        this.id = id;
    }

//    public Station getCurrentStation() {
//        return currentStation;
//    }

    public Station getStation() {
        return station;
    }

    @Override
    public Long getId() {
        return id;
    }

//    public HashMap<Integer, Compartment> getCompartments() {
//        return compartments;
//    }

    public void setCurrentStation(Station currentStation) {
        this.station = currentStation;
    }

//    private void createCompartments(int numberOfCompartments, int size) {
//        for (int i = 0; i < numberOfCompartments; i++) {
//            this.compartments.put(i, new Compartment(size));
//        }
//    }

    public int whatDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

//    public ArrayList<Passenger> addPassengers(ArrayList<Passenger> passengers) {
//        int compartmentId = 0;
//        while (!passengers.isEmpty() & compartmentId < compartments.size() - 1) {
//            passengers = compartments.get(compartmentId).addPassengers(passengers);
//            compartmentId += 1;
//        }
//        setFillPercentage();
//        return passengers;
//    }

//    public void removePassengers() {
//        for (int i = 0; i < compartments.size(); i++) {
//            compartments.get(i).removePassengers(getCurrentStation());
//        }
//    }

//    public void move() {  // Moves the train by one square
//        if (cooldown == 0) {
//            if (whatDirection()) {    // Check the direction to go
//                station += 1;
//            } else {
//                station -= 1;
//            }
//            currentStation = Station.VALUES.get(station);
//            checkDirection();
//            removePassengers();
//        } else {
//            cooldown -= 1;
//        }
//    }

//    public void checkDirection() {   // Check if it's the end of road, true changes direction, adds cooldown
//        if (getStation() == 14) {
//            setDirection(false);
//            this.cooldown = 2;
//        } else if (getStation() == 0) {
//            setDirection(true);
//            this.cooldown = 2;
//        }
//    }

//    public int getTotalPassengers() {
//        int totalPassengers = 0;
//        for (Compartment compartment : this.compartments.values()) {
//            totalPassengers += compartment.getNumberOfPassengers();
//        }
//        return totalPassengers;
//
//    }
}
