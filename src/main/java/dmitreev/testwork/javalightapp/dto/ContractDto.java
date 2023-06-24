package dmitreev.testwork.javalightapp.dto;

import dmitreev.testwork.javalightapp.model.ContractStatus;
import lombok.*;

import javax.validation.constraints.Positive;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractDto {

    private  Long number;

    private ContractStatus status;

    @Positive
    private Long admin;

    @Positive
    private Long principal;
}
