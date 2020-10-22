package pl.edu.pjwstk.simulator.models;

import java.io.IOException;
import java.util.Map;

public class Train {
    Map<String, Integer> data;
    SimulatorGetPropertyValues properties = new SimulatorGetPropertyValues();
    int station;

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }

    public Train(Map<String, Integer> data) throws IOException {
        this.data = properties.getPropValues();
        this.station = 0;
    }
}
