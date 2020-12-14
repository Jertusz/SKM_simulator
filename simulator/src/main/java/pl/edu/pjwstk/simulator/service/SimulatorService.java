package pl.edu.pjwstk.simulator.service;

import org.springframework.stereotype.Service;
import pl.edu.pjwstk.simulator.models.Station;
import pl.edu.pjwstk.simulator.models.Train;
import pl.edu.pjwstk.simulator.repository.CompartmentRepository;
import pl.edu.pjwstk.simulator.repository.StationRepository;
import pl.edu.pjwstk.simulator.repository.TrainRepository;

import java.util.List;

@Service
public class SimulatorService {
    private final TrainRepository trainRepository;
    private final CompartmentRepository compartmentRepository;
    private final StationRepository stationRepository;

    public SimulatorService(TrainRepository trainRepository, CompartmentRepository compartmentRepository, StationRepository stationRepository) {
        this.trainRepository = trainRepository;
        this.compartmentRepository = compartmentRepository;
        this.stationRepository = stationRepository;
    }

    public void move() {
        for(Train train: trainRepository.findAll()) {
            Long nextStationId = train.getStation().getId();
            if (train.getDirection() == 0 && train.getCooldown() == 0) {
                nextStationId = nextStationId - 1;
                if (nextStationId == 1) {
                    train.setCooldown(2);
                    train.setDirection(1);
                }
            } else if (train.getCooldown() == 0){
                nextStationId = nextStationId + 1;
                if (nextStationId == 15) {
                    train.setCooldown(2);
                    train.setDirection(0);
                }
            } else {
                train.setCooldown(train.getCooldown()-1);
            }
            train.setStation(stationRepository.findById(nextStationId).get());
            trainRepository.save(train);
        }
    }

}
