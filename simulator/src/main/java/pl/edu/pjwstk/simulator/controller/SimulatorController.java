package pl.edu.pjwstk.simulator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.simulator.models.Simulator;

import java.io.IOException;

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

    @GetMapping("/state")
    public Simulator getState() {
        simulator.describeState();
        return simulator;
    }
}
