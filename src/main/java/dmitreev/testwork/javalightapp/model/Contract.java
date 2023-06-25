package dmitreev.testwork.javalightapp.model;

import dmitreev.testwork.javalightapp.enums.ContractStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long number;

    @Enumerated(EnumType.STRING)
    private ContractStatus status;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "signing_on")
    private LocalDateTime signingOn;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @ManyToOne
    @JoinColumn(name = "participant_inn")
    private Principal principal;
}
