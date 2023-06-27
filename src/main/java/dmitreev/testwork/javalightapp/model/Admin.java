package dmitreev.testwork.javalightapp.model;

import dmitreev.testwork.javalightapp.enums.UserRole;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
