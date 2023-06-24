package dmitreev.testwork.javalightapp.dto;

import dmitreev.testwork.javalightapp.model.PCRtest;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantDto {

    private Long id;

    @NotBlank(message = "'fio' can not be blank")
    private String fio;

    @Positive
    private int age;

    @Email
    @NotBlank(message = "'email' can not be blank")
    private String email;

    private PCRtest pcrTest;
}
