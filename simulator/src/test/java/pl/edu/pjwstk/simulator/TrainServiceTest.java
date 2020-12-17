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

    @MockBean
    Train trainMock;

    @Before
    public void setup() {
        trainService = new TrainService(trainRepository, compartmentRepository, stationRepository);
    }

    @Test
    public void train_without_id_is_added_correctly() {
        var train = new Train();
        var station = new Station();
        station.setName("Test");
        train.setStation(station);
        trainService.createOrUpdate(train);
        Mockito.verify(trainRepository).save(train);
    }

    @Test
    public void train_with_id_is_added_correctly() {
        var train = new Train();
        var station = new Station();
        station.setName("Test");
        train.setId(1L);
        Mockito.when(trainRepository.findById(1L)).thenReturn(Optional.empty());
        trainService.createOrUpdate(train);
        Mockito.verify(trainRepository).save(train);
    }

    @Test
    public void train_is_modified_correctly() {
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
}