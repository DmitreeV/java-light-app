package dmitreev.testwork.javalightapp.dto;

import dmitreev.testwork.javalightapp.enums.ContractStatus;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewContractDto {

    private  Long number;

    private ContractStatus status;
}
