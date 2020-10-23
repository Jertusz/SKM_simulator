package pl.edu.pjwstk.simulator;

import org.junit.BeforeClass;
import org.junit.Test;
import pl.edu.pjwstk.simulator.models.Simulator;

import java.io.IOException;

public class SimulatorTests {
    private static Simulator simulator;
    @BeforeClass
    public static void init() throws IOException {
        simulator = new Simulator();
    }

    @Test
    public void runTheSimulation10Times() {
        for (int i = 0; i < 10; i++) {
            simulator.move();
            simulator.describeState();

        }
    }
}
