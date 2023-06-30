package dmitreev.testwork.javalightapp.controller;

import dmitreev.testwork.javalightapp.dto.ContractDto;
import dmitreev.testwork.javalightapp.dto.ContractUpdateDto;
import dmitreev.testwork.javalightapp.dto.NewContractDto;
import dmitreev.testwork.javalightapp.service.ContractService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/admin/{adminId}/contracts")
@Tag(name = "Operations with contracts available to the administrator.")
public class ContractController {

    private final ContractService contractService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Creating a new contract by the administrator.")
    public ContractDto saveContract(@PathVariable Long adminId, @Valid @RequestBody NewContractDto contractDto) {
        return contractService.saveContract(adminId, contractDto);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Viewing admin contracts sorted from new to old.")
    List<ContractDto> getAllContractsByAdminIdWithSortFromNewToOld(@PathVariable Long adminId,
                                                                   @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                                   @Positive @RequestParam(defaultValue = "10") Integer size) {
        return contractService.getAllContractsByAdminIdWithSortFromNewToOld(adminId, from, size);
    }

    @PatchMapping("/{contractId}/sent/{principalId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Sending the contract for signing to the principal.")
    public ContractDto sendContractForSigning(@PathVariable Long contractId, @PathVariable Long adminId,
                                              @PathVariable Long principalId) {
        return contractService.sendContractForSigning(contractId, adminId, principalId);
    }

    @PatchMapping("/{contractId}/review/{principalId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Review of the contract by the principal and subsequent signing/deviation.")
    public ContractUpdateDto reviewContractByPrincipal(@PathVariable Long adminId, @PathVariable Long contractId,
                                                       @PathVariable Long principalId, @RequestParam Boolean approved) {
        return contractService.reviewContractByPrincipal(adminId, contractId, principalId, approved);
    }

    @DeleteMapping("/{contractId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletion of the contract by the administrator.")
    public void adminDeleteContract(@PathVariable Long contractId, @PathVariable Long adminId) {
        contractService.adminDeleteContract(contractId, adminId);
    }
}
