package pl.edu.pjwstk.simulator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.simulator.models.Station;
import pl.edu.pjwstk.simulator.models.Train;
import pl.edu.pjwstk.simulator.repository.CompartmentRepository;
import pl.edu.pjwstk.simulator.repository.StationRepository;
import pl.edu.pjwstk.simulator.repository.TrainRepository;
import pl.edu.pjwstk.simulator.service.TrainService;

import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TrainServiceTest {

    @MockBean
    TrainRepository trainRepository;

    @MockBean
    CompartmentRepository compartmentRepository;

    @MockBean
    StationRepository stationRepository;


    TrainService trainService;

    @Before
    public void setup() {
        trainService = new TrainService(trainRepository, compartmentRepository, stationRepository);
    }

    @Test
    public void addTrainWithoutID() {
        var train = new Train();
        var station = new Station();
        station.setName("Test");
        train.setStation(station);
        trainService.createOrUpdate(train);
        Mockito.verify(trainRepository).save(train);
    }

    @Test
    public void addTrainWithID() {
        var train = new Train();
        var station = new Station();
        station.setName("Test");
        train.setId(1L);
        Mockito.when(trainRepository.findById(1L)).thenReturn(Optional.empty());
        trainService.createOrUpdate(train);
        Mockito.verify(trainRepository).save(train);
    }

    @Test
    public void modifyTrain() {
        var train = Mockito.spy(new Train());
        var station = new Station();
        station.setName("Test");
        train.setId(1L);
        Mockito.when(train.getId()).thenReturn(1L);
        // This ensures that train "exists" in our db, hence goes to modify
        Mockito.when(trainRepository.findById(1L)).thenReturn(Optional.of(train));
        trainService.createOrUpdate(train);

        Mockito.verify(trainRepository).findById(1L);
        Mockito.verify(trainRepository).save(train);
    }


    // Tests for parametrized methods from CrudService
    @Test
    public void getTrainByID() {
        trainService.getById(1L);
        Mockito.verify(trainRepository).findById(1L);
    }

    @Test
    public void deleteTrainByID() {
        Train train = new Train();
        Optional<Train> trainOptional = Optional.of(train);
        Mockito.when(trainRepository.findById(1L)).thenReturn(trainOptional);
        trainService.delete(1L);
        Mockito.verify(trainRepository).findById(1L);
        Mockito.verify(trainRepository).delete(trainOptional.get());
    }

    @Test
    public void getAllTrains() {
        trainService.getAll();
        Mockito.verify(trainRepository).findAll();
    }
}