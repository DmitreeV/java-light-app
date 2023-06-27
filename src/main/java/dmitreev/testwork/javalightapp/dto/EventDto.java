package dmitreev.testwork.javalightapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dmitreev.testwork.javalightapp.enums.EventStatus;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String createdOn;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String eventDate;

    @Positive
    private Long admin;

    private EventStatus status;
}
