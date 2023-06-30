package dmitreev.testwork.javalightapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "contract for the organization of the event.")
    private String terms;
}
