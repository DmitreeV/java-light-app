package dmitreev.testwork.javalightapp.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrincipalDto {

    private Long id;

    @NotBlank(message = "'name' can not be blank")
    private String name;

    @Positive
    @Size(min = 10, max = 12)
    private Long inn;
}
