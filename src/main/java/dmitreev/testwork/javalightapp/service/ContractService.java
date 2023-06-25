package dmitreev.testwork.javalightapp.service;

import dmitreev.testwork.javalightapp.dto.ContractDto;
import dmitreev.testwork.javalightapp.dto.NewContractDto;

import java.util.List;

public interface ContractService {

    ContractDto saveContract(Long adminId, NewContractDto contractDto);

    List<ContractDto> getAllContractsByAdminIdWithSortFromNewToOld(Long adminId, Integer from, Integer size);

    ContractDto sendContractForSigning(Long contractId, Long adminId, Long principalId);

    ContractDto reviewContractByPrincipal(Long contractId, Long principalId, Boolean approved);

    void adminDeleteContract(Long contractId, Long adminId);
}
