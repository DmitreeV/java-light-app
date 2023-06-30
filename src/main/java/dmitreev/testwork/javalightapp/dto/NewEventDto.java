package dmitreev.testwork.javalightapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewEventDto {

    @NotBlank(message = "'eventName' can not be blank")
    @Schema(example = "international economic forum")
    private String eventName;

    @Positive
    @Schema(example = "3000")
    private Long rate;

    @NotBlank(message = "'eventDate' can not be blank")
    private String eventDate;
}
