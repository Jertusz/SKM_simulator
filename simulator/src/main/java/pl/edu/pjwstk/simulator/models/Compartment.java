package pl.edu.pjwstk.simulator.models;

import java.util.ArrayList;

public class Compartment {
    int size;
    ArrayList<Station> targetStation = new ArrayList<>();

    public Compartment(int size) {
        this.size = size;
    }

    public boolean isFull() {
        return targetStation.size() == size;
    }

    public int getNumberOfPassengers(){
        return targetStation.size();
    }

    public ArrayList<Station> getPassengers(){
        return targetStation;
    }

    public ArrayList<Station> addPassengers(ArrayList<Station> passengers){
        while(!isFull() & !passengers.isEmpty()){
            targetStation.add(passengers.get(0));
            passengers.remove(0);
        }
        return passengers;
    }

    public void removePassengers(Station station) {
        while (targetStation.contains(station)) {
            targetStation.remove(targetStation.indexOf(station));
        }
    }
}
