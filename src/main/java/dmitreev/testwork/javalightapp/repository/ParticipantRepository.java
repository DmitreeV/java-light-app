package dmitreev.testwork.javalightapp.repository;

import dmitreev.testwork.javalightapp.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
