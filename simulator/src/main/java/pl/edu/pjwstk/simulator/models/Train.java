package pl.edu.pjwstk.simulator.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Train {
    Map<String, Integer> data;
    SimulatorGetPropertyValues properties = new SimulatorGetPropertyValues();
    int id;
    int station;
    ArrayList<Compartment> compartments = new ArrayList<Compartment>();
    int compartment_size;

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }

    public Train(int id) throws IOException {
        this.data = properties.getPropValues();
        createCompartments(data.get("compartments"));
        this.compartment_size = data.get("compartment_size");
        this.station = 0;
        this.id = id;
    }

    private void createCompartments(Integer numberOfCompartments) {
        for (int i=0; i<numberOfCompartments; i++) {
            this.compartments.add(new Compartment(this.compartment_size));
        }
    }
}
