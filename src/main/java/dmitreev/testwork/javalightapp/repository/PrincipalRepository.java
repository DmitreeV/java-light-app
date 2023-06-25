package dmitreev.testwork.javalightapp.repository;

import dmitreev.testwork.javalightapp.model.Principal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrincipalRepository extends JpaRepository<Principal, Long> {
}
