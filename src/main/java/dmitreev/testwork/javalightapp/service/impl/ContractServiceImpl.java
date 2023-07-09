package dmitreev.testwork.javalightapp.service.impl;

import dmitreev.testwork.javalightapp.dto.ContractDto;
import dmitreev.testwork.javalightapp.dto.ContractUpdateDto;
import dmitreev.testwork.javalightapp.dto.NewContractDto;
import dmitreev.testwork.javalightapp.enums.ContractStatus;
import dmitreev.testwork.javalightapp.enums.UserRole;
import dmitreev.testwork.javalightapp.error.exception.AccessException;
import dmitreev.testwork.javalightapp.error.exception.ConflictException;
import dmitreev.testwork.javalightapp.error.exception.NotFoundException;
import dmitreev.testwork.javalightapp.mapper.ContractMapper;
import dmitreev.testwork.javalightapp.model.Admin;
import dmitreev.testwork.javalightapp.model.Contract;

import dmitreev.testwork.javalightapp.model.Principal;
import dmitreev.testwork.javalightapp.repository.AdminRepository;
import dmitreev.testwork.javalightapp.repository.ContractRepository;
import dmitreev.testwork.javalightapp.repository.PrincipalRepository;
import dmitreev.testwork.javalightapp.service.ContractService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final AdminRepository adminRepository;
    private final PrincipalRepository principalRepository;
    private final ContractMapper contractMapper;


    @Override
    public ContractDto saveContract(Long adminId, NewContractDto contractDto) {
        Admin admin = getAdmin(adminId);

        if (!admin.getRole().equals(UserRole.ADMIN)) {
            throw new AccessException("Access error.");
        }
        Contract contract = contractMapper.toContract(contractDto);

        contract.setAdmin(admin);
        contract.setCreatedOn(LocalDateTime.now());
        contract.setStatus(ContractStatus.CREATED);
        log.info("New contract {} has been created by the administrator with id {}.", contractDto, adminId);
        return contractMapper.toContractDto(contractRepository.save(contract));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContractDto> getAllContractsByAdminIdWithSortFromNewToOld(Long adminId, Integer from, Integer size) {
        getAdmin(adminId);
        Page<Contract> requests = contractRepository.findAllByAdminIdOrderByCreatedOnDesc(adminId, PageRequest.of(from / size, size));

        log.info("Received a list of all-admin with id {} contracts sorted from newer to older.", adminId);
        return requests.stream()
                .map(contractMapper::toContractDto)
                .collect(Collectors.toList());
    }

    @Override
    public ContractDto sendContractForSigning(Long contractId, Long adminId, Long principalId) {
        Principal principal = getPrincipal(principalId);

        Contract contract = contractRepository.findByNumberAndAdminId(contractId, adminId)
                .orElseThrow(() -> new ConflictException("Only the contract creator can send it for signing."));

        contract.setStatus(ContractStatus.SENT);
        contract.setPrincipal(principal);
        log.info("The contract has been sent to the signing.");
        return contractMapper.toContractDto(contractRepository.save(contract));
    }

    @Override
    public ContractUpdateDto reviewContractByPrincipal(Long adminId, Long contractId, Long principalId, Boolean approved) {
        getPrincipal(principalId);
        getAdmin(adminId);

        Contract contract = contractRepository.findByNumberAndPrincipalId(contractId, principalId)
                .orElseThrow(() -> new ConflictException("The principal must receive a contract for further actions."));

        if (!contract.getStatus().equals(ContractStatus.SENT)) {
            throw new ConflictException("Only submitted contracts can be processed.");
        }

        if (approved) {
            contract.setStatus(ContractStatus.SIGNED);
            contract.setSigningOn(LocalDateTime.now());
        } else {
            contract.setStatus(ContractStatus.NOT_SIGNED);
        }
        log.info("The contract has been reviewed.");
        return contractMapper.toContractUpdateDto(contractRepository.save(contract));
    }

    @Override
    public void adminDeleteContract(Long contractId, Long adminId) {
        getAdmin(adminId);
        Contract contract = getContract(contractId);

        if (!Objects.equals(contract.getAdmin().getId(), adminId)) {
            throw new ConflictException("Only the creator of the contract can delete it.");
        }
        if (contract.getStatus() != (ContractStatus.CREATED)) {
            throw new ConflictException("You can only delete contracts with the CREATED status.");
        }

        contractRepository.deleteById(contractId);
        log.info("The contract was deleted by the admin with id {}.", adminId);
    }

    private Admin getAdmin(Long adminId) {
        return adminRepository.findById(adminId).orElseThrow(() ->
                new NotFoundException(String.format("Admin with id=%d not found", adminId)));
    }

    private Principal getPrincipal(Long principalId) {
        return principalRepository.findById(principalId).orElseThrow(() ->
                new NotFoundException(String.format("Principal with id=%d not found", principalId)));
    }

    private Contract getContract(Long contractId) {
        return contractRepository.findById(contractId).orElseThrow(() ->
                new NotFoundException(String.format("Contract with id=%d not found", contractId)));
    }
}
