package dmitreev.testwork.javalightapp.controller;

import dmitreev.testwork.javalightapp.dto.ParticipantDto;
import dmitreev.testwork.javalightapp.service.ParticipantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/participant")
public class ParticipantController {

    private final ParticipantService participantService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ParticipantDto saveParticipant(@Valid @RequestBody ParticipantDto participantDto) {
        return participantService.saveParticipant(participantDto);
    }

    @DeleteMapping("/{partId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteParticipant(@PathVariable Long partId) {
        participantService.deleteParticipant(partId);
    }
}
