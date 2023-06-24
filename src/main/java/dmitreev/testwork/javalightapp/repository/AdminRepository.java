package dmitreev.testwork.javalightapp.repository;

import dmitreev.testwork.javalightapp.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
