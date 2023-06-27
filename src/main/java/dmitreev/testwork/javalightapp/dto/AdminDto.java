package dmitreev.testwork.javalightapp.dto;

import com.sun.istack.NotNull;
import dmitreev.testwork.javalightapp.enums.UserRole;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminDto {

    private Long id;

    @NotBlank(message = "'name' can not be blank")
    private String orgName;

    @Email
    @NotBlank(message = "'email' can not be blank")
    private String email;

    @NotNull
    private UserRole role;
}
