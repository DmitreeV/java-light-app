package dmitreev.testwork.javalightapp.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "eventName")
    private String eventName;

    @Column(name = "rate")
    private Long rate;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
}
