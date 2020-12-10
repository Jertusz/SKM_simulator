package pl.edu.pjwstk.simulator.service;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.simulator.models.Train;
import pl.edu.pjwstk.simulator.repository.CompartmentRepository;
import pl.edu.pjwstk.simulator.repository.TrainRepository;

import java.util.Optional;

@Service
public class TrainService extends CrudService<Train>{
    private final CompartmentRepository compartmentRepository;

    public TrainService(TrainRepository trainRepository, CompartmentRepository compartmentRepository) {
        super(trainRepository);
        this.compartmentRepository = compartmentRepository;
    }

    @Override
    public Train createOrUpdate(Train updateEntity) {
        if (updateEntity.getId() == null) {
            return repository.save(updateEntity);
        }
//
//        Optional<Train> trainInDb = repository.findById(updateEntity.getId());
//
//        if (trainInDb.isPresent()) {
//            Train dbEntity = trainInDb.get();
//        }
        return null;
    }
}
