package dmitreev.testwork.javalightapp.controller;

import dmitreev.testwork.javalightapp.dto.ParticipationRequestDto;
import dmitreev.testwork.javalightapp.service.ParticipationRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/participant/{partId}/requests")
public class ParticipationRequestController {

    private final ParticipationRequestService participationRequestService;

    @PostMapping("/{eventId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ParticipationRequestDto saveParticipationRequest(@PathVariable Long partId, @PathVariable Long eventId) {
        return participationRequestService.saveParticipationRequest(partId, eventId);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    List<ParticipationRequestDto> getParticipationRequests(@PathVariable Long partId) {
        return participationRequestService.getParticipationRequests(partId);
    }

    @PatchMapping("/{requestId}/cancel")
    @ResponseStatus(value = HttpStatus.OK)
    public ParticipationRequestDto cancelParticipationRequest(@PathVariable Long partId, @PathVariable Long requestId) {
        return participationRequestService.cancelParticipationRequest(partId, requestId);
    }
}
