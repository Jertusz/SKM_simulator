package pl.edu.pjwstk.simulator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.simulator.models.Compartment;
import pl.edu.pjwstk.simulator.models.Passenger;
import pl.edu.pjwstk.simulator.models.Station;
import pl.edu.pjwstk.simulator.models.Train;
import pl.edu.pjwstk.simulator.repository.CompartmentRepository;
import pl.edu.pjwstk.simulator.repository.PassengerRepository;
import pl.edu.pjwstk.simulator.repository.StationRepository;
import pl.edu.pjwstk.simulator.repository.TrainRepository;
import pl.edu.pjwstk.simulator.service.SimulatorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SimulatorServiceTest {
    @Captor
    ArgumentCaptor<List<Passenger>> passengersToDeleteCaptor;

    @MockBean
    TrainRepository trainRepository;

    @MockBean
    CompartmentRepository compartmentRepository;

    @MockBean
    PassengerRepository passengerRepository;

    @MockBean
    StationRepository stationRepository;

    SimulatorService simulatorService;

    @Before
    public void setup() {
        simulatorService = new SimulatorService(trainRepository, stationRepository, passengerRepository);
    }

    @Test
    public void moveWorksProperly() {

        List<Station> stations = new ArrayList<>();

        // Creating station list, id's in list = station id - 1
        for (int i = 1; i<16; i++) {
            Station station = new Station();
            station.setId(i);
            station.setName("Station" + i);
            stations.add(station);
        }

        List<Train> trains = new ArrayList<>();
        Train trainOne = Mockito.spy(new Train());
        trainOne.setDirection(0);
        trainOne.setCooldown(0);
        trainOne.setStation(stations.get(1));
        Train trainTwo = Mockito.spy(new Train());
        trainTwo.setDirection(1);
        trainOne.setCooldown(0);
        trainTwo.setStation(stations.get(13));
        trains.add(trainOne);
        trains.add(trainTwo);

        Compartment compartmentOne = Mockito.spy(new Compartment());
        compartmentOne.setTrain(trainOne);
        compartmentOne.setSize(5);
        Compartment compartmentTwo = Mockito.spy(new Compartment());
        compartmentTwo.setTrain(trainTwo);
        compartmentTwo.setSize(5);

        Mockito.when(trainRepository.findAll()).thenReturn(trains);
        for (int i = 1; i < 16; i++) {
            Mockito.when(stationRepository.findById(Long.valueOf(i))).thenReturn(Optional.of(stations.get(i-1)));
        }
        Mockito.when(trainOne.getCompartmentList()).thenReturn(List.of(compartmentOne));
        Mockito.when(trainTwo.getCompartmentList()).thenReturn(List.of(compartmentTwo));
        Mockito.when(compartmentOne.getPassengerList()).thenReturn(new ArrayList<>());
        Mockito.when(compartmentTwo.getPassengerList()).thenReturn(new ArrayList<>());
        // This check for change of directions and coming back to the same station
        for (int i = 0; i < 5; i++) {
            simulatorService.move();
        }
        Assert.assertEquals(1, trainOne.getDirection());
        Assert.assertEquals(0, trainTwo.getDirection());
        Assert.assertTrue(3L == trainOne.getStation().getId());
        Assert.assertTrue(13L == trainTwo.getStation().getId());


    }

    @Test
    public void addPassengersToTrain() {
        Train trainOne = Mockito.spy(new Train());
        trainOne.setDirection(0);
        trainOne.setCooldown(0);
        Compartment compartmentOne = Mockito.spy(new Compartment());
        compartmentOne.setTrain(trainOne);
        compartmentOne.setSize(5);
        Compartment compartmentTwo = Mockito.spy(new Compartment());
        compartmentTwo.setTrain(trainOne);
        compartmentTwo.setSize(5);
        Compartment compartmentThree = new Compartment();
        compartmentTwo.setTrain(trainOne);
        compartmentTwo.setSize(5);

        Mockito.when(trainOne.getCompartmentList()).thenReturn(List.of(compartmentOne, compartmentTwo, compartmentThree));
        Mockito.when(compartmentOne.getPassengerList()).thenReturn(new ArrayList<>());
        Mockito.when(compartmentTwo.getPassengerList()).thenReturn(new ArrayList<>());
        simulatorService.addPassengers(trainOne);
        Mockito.verify(passengerRepository, Mockito.atMost(7)).save(any(Passenger.class));
    }

    @Test
    public void addPassengersToFullTrain() {
        Train trainOne = Mockito.spy(new Train());
        Station testStation = new Station();
        testStation.setName("Test");
        trainOne.setStation(testStation);
        trainOne.setDirection(0);
        trainOne.setCooldown(0);
        Compartment compartmentOne = Mockito.spy(new Compartment());
        compartmentOne.setId(1L);
        compartmentOne.setTrain(trainOne);
        compartmentOne.setSize(4);
        List<Passenger> passengersInCompartment = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Passenger temporaryPassenger = Mockito.spy(new Passenger());
            temporaryPassenger.setTargetStation(testStation);
            temporaryPassenger.setCompartment(compartmentOne);
            passengersInCompartment.add(temporaryPassenger);
        }
        Mockito.when(trainOne.getCompartmentList()).thenReturn(List.of(compartmentOne));
        Mockito.when(compartmentOne.getPassengerList()).thenReturn(passengersInCompartment);
        simulatorService.addPassengers(trainOne);
        Mockito.verifyNoInteractions(passengerRepository);
    }

    @Test
    public void removePassengersFromTrain() {
        Train trainOne = Mockito.spy(new Train());
        Station testStation = new Station();
        testStation.setName("Test");
        trainOne.setStation(testStation);
        trainOne.setDirection(0);
        trainOne.setCooldown(0);
        Compartment compartmentOne = Mockito.spy(new Compartment());
        compartmentOne.setId(1L);
        compartmentOne.setTrain(trainOne);
        compartmentOne.setSize(5);
        List<Passenger> passengersInCompartment = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Passenger temporaryPassenger = Mockito.spy(new Passenger());
            temporaryPassenger.setTargetStation(testStation);
            temporaryPassenger.setCompartment(compartmentOne);
            passengersInCompartment.add(temporaryPassenger);
        }
        Mockito.when(trainOne.getCompartmentList()).thenReturn(List.of(compartmentOne));
        Mockito.when(compartmentOne.getPassengerList()).thenReturn(passengersInCompartment);
        simulatorService.removePassengers(trainOne);
        for (Passenger passenger : passengersInCompartment) {
            Mockito.verify(passenger).getTargetStation();
        }
        Mockito.verify(passengerRepository).deleteInBatch(passengersToDeleteCaptor.capture());
        Assert.assertEquals(passengersInCompartment, passengersToDeleteCaptor.getValue());
    }
}