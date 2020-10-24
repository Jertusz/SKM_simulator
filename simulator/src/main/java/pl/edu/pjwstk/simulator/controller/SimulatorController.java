package pl.edu.pjwstk.simulator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.simulator.models.Simulator;
import pl.edu.pjwstk.simulator.models.Train;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class SimulatorController {
    private Simulator simulator;

    public SimulatorController() throws IOException {
        simulator = new Simulator();
    }

    @PostMapping("/move")
    public void moveSimulator() {
        simulator.move();
    }

    @GetMapping("/trains")
    public ArrayList<Integer> getTrains() {
        ArrayList<Integer> res = new ArrayList<>();
        for(Train train: simulator.getTrains()){
            res.add(train.getId());
        }
        return res;
    }

    @GetMapping("/trains/{id}")
    public Train getTrainDetails(@PathVariable("id") int id) {
        Train queryTrain = null;
        for(Train train: simulator.getTrains()){
            if (id == train.getId()){
                queryTrain = train;
            }
        }
        return queryTrain;
    }
}
