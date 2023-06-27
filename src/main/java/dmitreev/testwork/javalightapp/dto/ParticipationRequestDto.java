package dmitreev.testwork.javalightapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dmitreev.testwork.javalightapp.enums.RequestStatus;
import lombok.*;

import javax.validation.constraints.Positive;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParticipationRequestDto {

    private Long id;

    @Positive
    private Long event;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String created;

    @Positive
    private Long requester;

    private RequestStatus status;
}
