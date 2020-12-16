package pl.edu.pjwstk.simulator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.edu.pjwstk.simulator.controller.TrainController;
import pl.edu.pjwstk.simulator.models.Station;
import pl.edu.pjwstk.simulator.models.Train;
import pl.edu.pjwstk.simulator.repository.TrainRepository;
import pl.edu.pjwstk.simulator.service.CompartmentService;
import pl.edu.pjwstk.simulator.service.SimulatorService;
import pl.edu.pjwstk.simulator.service.TrainService;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.core.Is.is;


@RunWith(SpringRunner.class)
@WebMvcTest(TrainController.class)
public class TrainTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    TrainRepository trainRepository;

    @MockBean
    CompartmentService compartmentService;

    @MockBean
    SimulatorService simulatorService;

    @MockBean
    TrainService trainService;

    @SpyBean
    TrainController trainController;

    @Captor
    ArgumentCaptor<Train> trainArgumentCaptor;
//    @Autowired
//    private TrainRepository trainRepository;
//    @Autowired
//    private StationRepository stationRepository;

    @BeforeClass
    public void init() {

    }

    @Test
    public void getTrainList() throws Exception {
        Train train = new Train();
        train.setCooldown(2);

        List<Train> allTrains = Collections.singletonList(train);

        BDDMockito.given(trainService.getAll()).willReturn(allTrains);
        mockMvc.perform(MockMvcRequestBuilders.get("/trains")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].cooldown", is(train.getCooldown())));
    }

    @Test
    public void getTrainByID() throws Exception {
        BDDMockito.given(trainController.getById(1l)).willReturn(new ResponseEntity<>(HttpStatus.OK));
        mockMvc.perform(MockMvcRequestBuilders.get("/trains/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTrain() throws Exception {
        BDDMockito.given(trainController.delete(1l)).willReturn(new ResponseEntity<>(HttpStatus.ACCEPTED));
        mockMvc.perform(MockMvcRequestBuilders.delete("/trains/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    public void addCorrectTrain() throws Exception {
        Train train = new Train();
        train.setDirection(1);
        train.setCooldown(2);
        train.setId(1l);
        Station station = new Station();
        station.setId(1l);
        station.setName("test");
        train.setStation(station);
        ObjectMapper jsonParser = new ObjectMapper();
        String jsonObject = jsonParser.writeValueAsString(train);
//        BDDMockito.given(trainController.add(train)).willReturn(new ResponseEntity<>(HttpStatus.CREATED));
        mockMvc.perform(MockMvcRequestBuilders
                .post("/trains")
                .content(jsonObject)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
        mockMvc.perform(MockMvcRequestBuilders
                .get("/trains/1")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        BDDMockito.verify(trainController).add(trainArgumentCaptor.capture());
        // TODO Find a better way to test endpoints
//        Train train = new Train();
//        train.setCooldown(0);
//        train.setStation(stationRepository.getOne(1l));
//        train.setDirection(1);
//        trainRepository.save(train);
//        Assertions.assertEquals(1, trainRepository.findAll().size());
//        trainRepository.delete(train);
//        Assertions.assertEquals(0, trainRepository.findAll().size());
    }

    @Test
    public void modifyTrain() throws Exception {

        Train train = new Train();
        train.setDirection(1);
        train.setCooldown(2);
        Station station = new Station();
        station.setId(1l);
        station.setName("test");
        train.setStation(station);
        ObjectMapper jsonParser = new ObjectMapper();
        String jsonObject = jsonParser.writeValueAsString(train);
        mockMvc.perform( MockMvcRequestBuilders
                .post("/trains")
                .content(jsonObject)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        train.setCooldown(0);
        train.setId(1l);
        String modify = jsonParser.writeValueAsString(train);
        mockMvc.perform( MockMvcRequestBuilders
                .put("/trains")
                .content(modify)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    public void addWrongTrain() throws Exception {
//        String test = "{\"name\":\"test\"}";
//        System.out.println(test);
//        Mockito.when(trainController.add(new Train())).thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
//        mockMvc.perform( MockMvcRequestBuilders
//                .post("/trains")
//                .content(test)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isInternalServerError());
    }


    @Test
    public void modifyTrainBadData() throws Exception {
        Train train = new Train();
        train.setDirection(1);
        train.setCooldown(2);
        Station station = new Station();
        station.setId(1l);
        station.setName("test");
        train.setStation(station);
        ObjectMapper jsonParser = new ObjectMapper();
        String jsonObject = jsonParser.writeValueAsString(train);
        mockMvc.perform( MockMvcRequestBuilders
                .post("/trains")
                .content(jsonObject)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        String test = "{\"name\":\"test\"}";
        mockMvc.perform( MockMvcRequestBuilders
                .put("/trains")
                .content(test)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
