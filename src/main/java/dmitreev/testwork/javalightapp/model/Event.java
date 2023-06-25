package dmitreev.testwork.javalightapp.model;

import dmitreev.testwork.javalightapp.enums.EventStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(name = "eventDate")
    private LocalDateTime eventDate;

    @Column(name = "createdOn")
    private LocalDateTime createdOn;

    @Column(name = "rate")
    private Long rate;

    @Column(name = "request_moderation")
    private Boolean requestModeration;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @Enumerated(EnumType.STRING)
    private EventStatus status;
}
