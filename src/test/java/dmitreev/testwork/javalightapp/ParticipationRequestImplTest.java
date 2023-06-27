package dmitreev.testwork.javalightapp;

import dmitreev.testwork.javalightapp.dto.NewContractDto;
import dmitreev.testwork.javalightapp.dto.NewEventDto;
import dmitreev.testwork.javalightapp.dto.ParticipationRequestDto;
import dmitreev.testwork.javalightapp.enums.RequestStatus;
import dmitreev.testwork.javalightapp.error.exception.ConflictException;
import dmitreev.testwork.javalightapp.error.exception.NotFoundException;
import dmitreev.testwork.javalightapp.service.ContractService;
import dmitreev.testwork.javalightapp.service.EventService;
import dmitreev.testwork.javalightapp.service.ParticipationRequestService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ParticipationRequestImplTest {

    private final EventService eventService;
    private final ContractService contractService;
    private final ParticipationRequestService participationRequestService;

    @BeforeEach
    void init() {

        NewContractDto contractDto = NewContractDto
                .builder()
                .terms("Contract for the organization of the event.")
                .build();

        NewEventDto eventDto = NewEventDto
                .builder()
                .eventName("Scarlet sails.")
                .rate(5000L)
                .eventDate("2023-07-05T20:30:00.00000")
                .build();

        contractService.saveContract(1L, contractDto);
        contractService.sendContractForSigning(1L, 1L, 1L);
        contractService.reviewContractByPrincipal(1L, 1L, 1L, true);

        eventService.saveEvent(1L, 1L, eventDto);
    }

    @Test
    void testSaveParticipationRequest() {
        ParticipationRequestDto part = participationRequestService.saveParticipationRequest(1L, 1L);

        Assertions.assertEquals(part.getId(), 1L);
        Assertions.assertEquals(part.getStatus(), RequestStatus.CONFIRMED);
    }

    @Test
    void testSaveParticipationRequestFailPcrTestIsAbsent() {
        ParticipationRequestDto part = participationRequestService.saveParticipationRequest(2L, 1L);

        Assertions.assertEquals(part.getId(), 2L);
        Assertions.assertEquals(part.getStatus(), RequestStatus.REJECTED);
    }

    @Test
    void testSaveParticipationRequestFailEventNotFound() {
        NotFoundException e = assertThrows(NotFoundException.class,
                () -> participationRequestService.saveParticipationRequest(1L, 20L));
        Assertions.assertEquals(e.getMessage(), "Event with id=20 not found");
    }

    @Test
    void testGetParticipationRequests() {
        List<ParticipationRequestDto> requests = participationRequestService.getParticipationRequests(1L);

        Assertions.assertEquals(requests.size(), 1);
        Assertions.assertEquals(requests.get(0).getId(), 1);
        Assertions.assertEquals(requests.get(0).getStatus(), RequestStatus.CONFIRMED);
    }

    @Test
    void testCancelParticipationRequest() {
        ParticipationRequestDto part = participationRequestService.cancelParticipationRequest(1L, 1L);

        Assertions.assertEquals(part.getStatus(), RequestStatus.CANCELED);
    }

    @Test
    void testCancelParticipationRequestFailRequestRejected() {

        ConflictException e = assertThrows(ConflictException.class,
                () -> participationRequestService.cancelParticipationRequest(2L, 2L));
        Assertions.assertEquals(e.getMessage(), "You cannot cancel the request because it has been rejected.");
    }

    @Test
    void testCancelParticipationRequestFailWrongReq() {

        ConflictException e = assertThrows(ConflictException.class,
                () -> participationRequestService.cancelParticipationRequest(2L, 1L));
        Assertions.assertEquals(e.getMessage(), "Only the person who left the request can cancel it.");
    }
}
