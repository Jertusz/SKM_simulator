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
import pl.edu.pjwstk.simulator.controller.TrainController;
import pl.edu.pjwstk.simulator.models.Compartment;
import pl.edu.pjwstk.simulator.models.Station;
import pl.edu.pjwstk.simulator.models.Train;
import pl.edu.pjwstk.simulator.service.TrainService;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TrainController.class)
public class TrainControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    TrainController trainController;

    @MockBean
    TrainService trainService;


    @Test
    public void addTrain() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/trains").contentType(MediaType.APPLICATION_JSON).content("{\"station\":{\"id\":\"2\"}, \"direction\":\"1\", \"cooldown\":\"0\"}")).andExpect(status().isCreated());
        Mockito.verify(trainService).createOrUpdate(Mockito.any(Train.class));
    }

    @Test
    public void deleteTrain() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/trains/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted());
        Mockito.verify(trainService).delete(1L);
    }

    @Test
    public void getAllTrains() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/trains").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        Mockito.verify(trainService).getAll();
    }

    @Test
    public void getTrainByID() throws Exception {
        Station station = new Station();
        station.setName("Test");
        Train train = Mockito.spy(new Train());
        train.setId(1L);
        train.setStation(station);
        Mockito.when(train.getCompartmentList()).thenReturn(List.of(new Compartment(), new Compartment()));
        Mockito.when(trainService.getById(1L)).thenReturn(train);
        mockMvc.perform(MockMvcRequestBuilders.get("/trains/1")).andExpect(status().isOk());
        Mockito.verify(trainService).getById(1L);
    }

    @Test
    public void getNonExistingTrainByID() throws Exception {
        Mockito.when(trainService.getById(1L)).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/trains/1")).andExpect(status().isInternalServerError());
        Mockito.verify(trainService).getById(1L);
    }

    @Test
    public void modifyTrain() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/trains").contentType(MediaType.APPLICATION_JSON).content("{\"station\":{\"id\":\"2\"}, \"direction\":\"1\", \"cooldown\":\"0\"}")).andExpect(status().isAccepted());
        Mockito.verify(trainService).createOrUpdate(Mockito.any(Train.class));
    }
}
