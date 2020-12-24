package pl.edu.pjwstk.simulator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.edu.pjwstk.simulator.controller.CompartmentController;
import pl.edu.pjwstk.simulator.models.Compartment;
import pl.edu.pjwstk.simulator.models.Passenger;
import pl.edu.pjwstk.simulator.models.Station;
import pl.edu.pjwstk.simulator.models.Train;
import pl.edu.pjwstk.simulator.service.CompartmentService;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CompartmentController.class)
public class CompartmentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CompartmentController compartmentController;

    @MockBean
    CompartmentService compartmentService;


    @Test
    public void addCompartment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/compartments").contentType(MediaType.APPLICATION_JSON).content("{\"size\":\"1\", \"id\":\"1\"}")).andExpect(status().isCreated());
        Mockito.verify(compartmentService).createOrUpdate(Mockito.any(Compartment.class));
    }

    @Test
    public void deleteCompartment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/compartments/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted());
        Mockito.verify(compartmentService).delete(1L);
    }

    @Test
    public void getAllCompartments() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/compartments").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        Mockito.verify(compartmentService).getAll();
    }

    @Test
    public void getCompartmentByID() throws Exception {
        Station test = new Station();
        test.setName("Test");
        test.setId(1);
        Compartment compartment = Mockito.spy(new Compartment());
        compartment.setId(1L);
        compartment.setSize(5);
        compartment.setTrain(new Train());
        Passenger one = new Passenger();
        one.setTargetStation(test);
        Passenger two = new Passenger();
        two.setTargetStation(test);
        Mockito.when(compartment.getPassengerList()).thenReturn(List.of(one, two));
        Mockito.when(compartmentService.getById(1L)).thenReturn(compartment);
        mockMvc.perform(MockMvcRequestBuilders.get("/compartments/1")).andExpect(status().isOk());
        Mockito.verify(compartmentService).getById(1L);
    }

    @Test
    public void getNonExistingCompartmentByID() throws Exception {
        Mockito.when(compartmentService.getById(1L)).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/compartments/1")).andExpect(status().isInternalServerError());
        Mockito.verify(compartmentService).getById(1L);
    }

    @Test
    public void modifyCompartment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/compartments").contentType(MediaType.APPLICATION_JSON).content("{\"id\":\"1\", \"size\":\"1\"}")).andExpect(status().isAccepted());
        Mockito.verify(compartmentService).createOrUpdate(Mockito.any(Compartment.class));
    }
}
