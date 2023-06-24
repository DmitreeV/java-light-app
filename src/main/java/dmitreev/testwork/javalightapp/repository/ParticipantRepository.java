package dmitreev.testwork.javalightapp.repository;

import dmitreev.testwork.javalightapp.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
