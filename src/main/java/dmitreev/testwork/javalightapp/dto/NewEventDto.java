package dmitreev.testwork.javalightapp.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewEventDto {

    private String eventName;

    private Long rate;

    private String eventDate;
}
