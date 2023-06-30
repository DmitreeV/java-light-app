package dmitreev.testwork.javalightapp.controller;

import dmitreev.testwork.javalightapp.dto.EventDto;
import dmitreev.testwork.javalightapp.dto.NewEventDto;
import dmitreev.testwork.javalightapp.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/admin/{adminId}/events")
@Tag(name = "Operations with events available to the administrator.")
public class EventController {

    private final EventService eventService;

    @PostMapping("/{contractId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Creating a new event by the administrator.")
    public EventDto saveEvent(@PathVariable Long adminId, @PathVariable Long contractId,
                              @Valid @RequestBody NewEventDto eventDto) {
        return eventService.saveEvent(adminId, contractId, eventDto);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Viewing admin events sorted from new to old.")
    List<EventDto> getAllEventsByAdminIdWithSortFromNewToOld(@PathVariable Long adminId,
                                                             @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                             @Positive @RequestParam(defaultValue = "10") Integer size) {
        return eventService.getAllEventsByAdminIdWithSortFromNewToOld(adminId, from, size);
    }

    @PatchMapping("/{eventId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Cancellation of the event by the administrator.")
    public EventDto cancelEventByAdmin(@PathVariable Long adminId, @PathVariable Long eventId) {
        return eventService.cancelEventByAdmin(adminId, eventId);
    }

    @DeleteMapping("/{eventId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletion of the event by the administrator.")
    public void adminDeleteEvent(@PathVariable Long eventId, @PathVariable Long adminId) {
        eventService.adminDeleteEvent(eventId, adminId);
    }
}
