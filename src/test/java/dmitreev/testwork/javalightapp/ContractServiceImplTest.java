package dmitreev.testwork.javalightapp;

import dmitreev.testwork.javalightapp.dto.ContractDto;
import dmitreev.testwork.javalightapp.dto.NewContractDto;
import dmitreev.testwork.javalightapp.enums.ContractStatus;
import dmitreev.testwork.javalightapp.error.exception.ConflictException;
import dmitreev.testwork.javalightapp.error.exception.NotFoundException;
import dmitreev.testwork.javalightapp.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ContractServiceImplTest {

    private final ContractService contractService;
    private NewContractDto contractDto;
    private ContractDto contract;

    @BeforeEach
    void init() {

        contractDto = NewContractDto
                .builder()
                .terms("Contract for the organization of the event.")
                .build();
        contract = contractService.saveContract(1L, contractDto);
    }

    @Test
    void testSaveContract() {

        Assertions.assertEquals(contract.getTerms(), "Contract for the organization of the event.");
        Assertions.assertEquals(contract.getStatus(), ContractStatus.CREATED);
    }

    @Test
    void testSaveContractFailAdminNotFound() {
        NotFoundException e = assertThrows(NotFoundException.class,
                () -> contractService.saveContract(20L, contractDto));
        Assertions.assertEquals(e.getMessage(), "Admin with id=20 not found");
    }

    @Test
    void testSendContractForSigning() {
        ContractDto contractDto1 = contractService.sendContractForSigning(1L, 1L, 1L);

        Assertions.assertEquals(contractDto1.getStatus(), ContractStatus.SENT);
    }

    @Test
    void testSendContractForSigningFailWrongAdmin() {

        ConflictException e = assertThrows(ConflictException.class,
                () -> contractService.sendContractForSigning(1L, 5L, 1L));
        Assertions.assertEquals(e.getMessage(), "Only the contract creator can send it for signing.");
    }
}
