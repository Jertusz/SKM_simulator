package pl.edu.pjwstk.simulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pjwstk.simulator.models.Train;

public interface StationRepository extends JpaRepository<Train, Integer> {
}
