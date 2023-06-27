package dmitreev.testwork.javalightapp.repository;

import dmitreev.testwork.javalightapp.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Page<Event> findAllByAdminIdOrderByCreatedOnDesc(Long adminId, Pageable pageable);

    Optional<Event> findByIdAndAdminId(Long eventId, Long adminId);
}
