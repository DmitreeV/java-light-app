package dmitreev.testwork.javalightapp.controller;

import dmitreev.testwork.javalightapp.dto.EventDto;
import dmitreev.testwork.javalightapp.dto.NewEventDto;
import dmitreev.testwork.javalightapp.service.EventService;
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
public class EventController {

    private final EventService eventService;

    @PostMapping("/{contractId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public EventDto saveEvent(@PathVariable Long adminId, @PathVariable Long contractId, @Valid @RequestBody NewEventDto eventDto) {
        return eventService.saveEvent(adminId, contractId, eventDto);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    List<EventDto> getAllEventsByAdminIdWithSortFromNewToOld(@PathVariable Long adminId,
                                                             @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                             @Positive @RequestParam(defaultValue = "10") Integer size) {
        return eventService.getAllEventsByAdminIdWithSortFromNewToOld(adminId, from, size);
    }

    @DeleteMapping("/{eventId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void adminDeleteEvent(@PathVariable Long eventId, @PathVariable Long adminId) {
        eventService.adminDeleteEvent(eventId, adminId);
    }
}
