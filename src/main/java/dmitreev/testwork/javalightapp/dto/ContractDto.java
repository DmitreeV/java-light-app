package dmitreev.testwork.javalightapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dmitreev.testwork.javalightapp.enums.ContractStatus;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractDto {

    private Long number;

    private String terms;

    private ContractStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String createdOn;

    private Long admin;

    private Long principal;
}
