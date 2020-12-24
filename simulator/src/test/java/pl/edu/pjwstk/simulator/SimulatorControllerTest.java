package pl.edu.pjwstk.simulator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.edu.pjwstk.simulator.controller.SimulatorController;
import pl.edu.pjwstk.simulator.service.SimulatorService;

@RunWith(SpringRunner.class)
@WebMvcTest(SimulatorController.class)
public class SimulatorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SimulatorService simulatorService;

    @Test
    public void movingSimulator() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/move"));
        Mockito.verify(simulatorService).move();

    }
}
