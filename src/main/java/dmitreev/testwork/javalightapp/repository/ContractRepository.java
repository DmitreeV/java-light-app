package dmitreev.testwork.javalightapp.repository;

import dmitreev.testwork.javalightapp.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {
}
