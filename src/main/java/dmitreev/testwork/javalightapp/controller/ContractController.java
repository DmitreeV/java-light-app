package dmitreev.testwork.javalightapp.controller;

import dmitreev.testwork.javalightapp.dto.ContractDto;
import dmitreev.testwork.javalightapp.dto.ContractUpdateDto;
import dmitreev.testwork.javalightapp.dto.NewContractDto;
import dmitreev.testwork.javalightapp.service.ContractService;
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
public class ContractController {

    private final ContractService contractService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ContractDto saveContract(@PathVariable Long adminId, @Valid @RequestBody NewContractDto contractDto) {
        return contractService.saveContract(adminId, contractDto);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    List<ContractDto> getAllContractsByAdminIdWithSortFromNewToOld(@PathVariable Long adminId,
                                                                   @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                                   @Positive @RequestParam(defaultValue = "10") Integer size) {
        return contractService.getAllContractsByAdminIdWithSortFromNewToOld(adminId, from, size);
    }

    @PatchMapping("/{contractId}/sent/{principalId}")
    @ResponseStatus(value = HttpStatus.OK)
    public ContractDto sendContractForSigning(@PathVariable Long contractId, @PathVariable Long adminId,
                                              @PathVariable Long principalId) {
        return contractService.sendContractForSigning(contractId, adminId, principalId);
    }

    @PatchMapping("/{contractId}/review/{principalId}")
    @ResponseStatus(value = HttpStatus.OK)
    public ContractUpdateDto reviewContractByPrincipal(@PathVariable Long adminId, @PathVariable Long contractId,
                                                       @PathVariable Long principalId, @RequestParam Boolean approved) {
        return contractService.reviewContractByPrincipal(adminId, contractId, principalId, approved);
    }

    @DeleteMapping("/{contractId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void adminDeleteContract(@PathVariable Long contractId, @PathVariable Long adminId) {
        contractService.adminDeleteContract(contractId, adminId);
    }
}
