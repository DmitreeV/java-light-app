package dmitreev.testwork.javalightapp.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {

    private Long id;

    @NotBlank(message = "'name' can not be blank")
    private String eventName;

    @Positive
    private Long rate;

    @Positive
    private Long admin;
}
