package pl.edu.pjwstk.simulator.models;

import pl.edu.pjwstk.simulator.service.DbEntity;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "compartments")
public class Compartment implements DbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "train_id")
    Train train;
    int size;
//    ArrayList<Passenger> passengers = new ArrayList<>();

    public Compartment() {};

    public Compartment(int size) {
        this.size = size;
    }

    @Override
    public Long getId() {
        return id;
    }
//    public boolean isFull() {
//        return passengers.size() == size;
//    }

//    public int getNumberOfPassengers() {
//        return passengers.size();
//    }

//    public ArrayList<Passenger> getPassengers() {
//        return passengers;
//    }

//    public void setPassengers(ArrayList<Passenger> passengers) {
//        this.passengers = passengers;
//    }

    public int getSize() {
        return size;
    }

//    public ArrayList<Passenger> addPassengers(ArrayList<Passenger> passengers) {
//        while (!isFull() & !passengers.isEmpty()) {
//            this.passengers.add(passengers.get(0));
//            passengers.remove(0);
//        }
//        return passengers;
//    }

//    public void removePassengers(Station station) {
//        ArrayList<Passenger> passengers = new ArrayList<>();
//        for (Passenger passenger : this.passengers) {
//            if (!passenger.getTargetStation().equals(station)) {
//                passengers.add(passenger);
//            }
//        }
//        setPassengers(passengers);
//    }

//    public ArrayList<Station> getStation() {
//        ArrayList<Station> targets = new ArrayList<>();
//        for (Passenger passenger : passengers) {
//            targets.add(passenger.getTargetStation());
//        }
//        return targets;
//    }
}
