package dmitreev.testwork.javalightapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dmitreev.testwork.javalightapp.enums.ContractStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractUpdateDto {

    @Schema(example = "contract number 1")
    private Long number;

    @NotBlank(message = "'terms' can not be blank")
    @Schema(example = "contract for the organization of the event.")
    private String terms;

    private ContractStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String createdOn;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String signingOn;

    @Positive
    @Schema(example = "1")
    private Long admin;

    @Positive
    @Schema(example = "1")
    private Long principal;
}
