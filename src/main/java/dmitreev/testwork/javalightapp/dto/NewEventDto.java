package dmitreev.testwork.javalightapp.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewEventDto {

    private String eventName;

    private Long rate;

    private String eventDate;
}
