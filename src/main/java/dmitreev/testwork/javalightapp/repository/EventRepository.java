package dmitreev.testwork.javalightapp.repository;

import dmitreev.testwork.javalightapp.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
