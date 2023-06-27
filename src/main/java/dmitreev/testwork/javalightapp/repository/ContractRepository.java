package dmitreev.testwork.javalightapp.repository;

import dmitreev.testwork.javalightapp.model.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {

    Page<Contract> findAllByAdminIdOrderByCreatedOnDesc(Long adminId, Pageable pageable);

    Optional<Contract> findByNumberAndAdminId(Long contractId, Long adminId);

    Optional<Contract> findByNumberAndPrincipalId(Long contractId, Long principalId);
}
