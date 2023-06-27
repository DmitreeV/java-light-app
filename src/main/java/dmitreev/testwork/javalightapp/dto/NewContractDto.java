package dmitreev.testwork.javalightapp.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class NewContractDto {

    @NotBlank(message = "'terms' can not be blank")
    private String terms;
}
