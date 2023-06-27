package dmitreev.testwork.javalightapp.model;

import dmitreev.testwork.javalightapp.enums.RequestStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "requests")
public class ParticipationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "requester_id")
    private Participant requester;

    @Column(name = "created")
    private LocalDateTime created;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;
}
