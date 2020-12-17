package pl.edu.pjwstk.simulator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.simulator.models.Compartment;
import pl.edu.pjwstk.simulator.repository.CompartmentRepository;
import pl.edu.pjwstk.simulator.repository.PassengerRepository;
import pl.edu.pjwstk.simulator.service.CompartmentService;

import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CompartmentServiceTest {

    @MockBean
    PassengerRepository passengerRepository;

    @MockBean
    CompartmentRepository compartmentRepository;

    CompartmentService compartmentService;


    @Before
    public void setup() {
        compartmentService = new CompartmentService(compartmentRepository, passengerRepository);
    }

    @Test
    public void compartment_without_id_is_added_correctly() {
        var compartment = new Compartment();
        compartmentService.createOrUpdate(compartment);
        Mockito.verify(compartmentRepository).save(compartment);
    }

    @Test
    public void compartment_with_id_is_added_correctly() {
        var compartment = new Compartment();
        compartment.setId(1L);
        Mockito.when(compartmentRepository.findById(1L)).thenReturn(Optional.empty());
        compartmentService.createOrUpdate(compartment);
        Mockito.verify(compartmentRepository).save(compartment);
    }

    @Test
    public void compartment_is_modified_correctly() {
        var compartment = Mockito.spy(new Compartment());
        compartment.setId(1L);
        Mockito.when(compartment.getId()).thenReturn(1L);
        // This ensures that compartment "exists" in our db, hence goes to modify
        Mockito.when(compartmentRepository.findById(1L)).thenReturn(Optional.of(compartment));
        compartmentService.createOrUpdate(compartment);

        Mockito.verify(compartmentRepository).findById(1L);
        Mockito.verify(compartmentRepository).save(compartment);
    }


    // Tests for parametrized methods from CrudService
    @Test
    public void getCompartmentByID() {
        compartmentService.getById(1L);
        Mockito.verify(compartmentRepository).findById(1L);
    }

    @Test
    public void deleteCompartmentByID() {
        Compartment compartment = new Compartment();
        Optional<Compartment> compartmentOptional = Optional.of(compartment);
        Mockito.when(compartmentRepository.findById(1L)).thenReturn(compartmentOptional);
        compartmentService.delete(1L);
        Mockito.verify(compartmentRepository).findById(1L);
        Mockito.verify(compartmentRepository).delete(compartmentOptional.get());
    }

    @Test
    public void getAllCompartments() {
        compartmentService.getAll();
        Mockito.verify(compartmentRepository).findAll();
    }
}