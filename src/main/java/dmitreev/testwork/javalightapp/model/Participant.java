package dmitreev.testwork.javalightapp.model;

import dmitreev.testwork.javalightapp.enums.PCRtest;
import dmitreev.testwork.javalightapp.enums.UserRole;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "participants")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "fio")
    private String fio;

    @Column(name = "age")
    private int age;

    @Column(name = "email")
    private String email;

    @Column(name = "pcr_test")
    @Enumerated(EnumType.STRING)
    private PCRtest pcrTest;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
