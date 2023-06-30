package dmitreev.testwork.javalightapp.controller;

import dmitreev.testwork.javalightapp.dto.ParticipationRequestDto;
import dmitreev.testwork.javalightapp.service.ParticipationRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/participant/{partId}/requests")
@Tag(name = "Operations with requests available to the participant.")
public class ParticipationRequestController {

    private final ParticipationRequestService participationRequestService;

    @PostMapping("/{eventId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Creating a new request by the participant.")
    public ParticipationRequestDto saveParticipationRequest(@PathVariable Long partId, @PathVariable Long eventId) {
        return participationRequestService.saveParticipationRequest(partId, eventId);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Viewing participant requests.")
    List<ParticipationRequestDto> getParticipationRequests(@PathVariable Long partId) {
        return participationRequestService.getParticipationRequests(partId);
    }

    @PatchMapping("/{requestId}/cancel")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Cancellation of the request by the participant.")
    public ParticipationRequestDto cancelParticipationRequest(@PathVariable Long partId, @PathVariable Long requestId) {
        return participationRequestService.cancelParticipationRequest(partId, requestId);
    }
}
