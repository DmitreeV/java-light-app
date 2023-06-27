package dmitreev.testwork.javalightapp;

import dmitreev.testwork.javalightapp.dto.NewContractDto;
import dmitreev.testwork.javalightapp.dto.NewEventDto;
import dmitreev.testwork.javalightapp.error.exception.ConflictException;
import dmitreev.testwork.javalightapp.service.ContractService;
import dmitreev.testwork.javalightapp.service.EventService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EventServiceImplTest {

    private final EventService eventService;
    private final ContractService contractService;
    private NewEventDto eventDto;
    private NewContractDto contractDto;

    @BeforeEach
    void init() {

        contractDto = NewContractDto
                .builder()
                .terms("Contract for the organization of the event.")
                .build();

        eventDto = NewEventDto
                .builder()
                .eventName("Scarlet sails.")
                .rate(5000L)
                .eventDate("2023-07-05T20:30:00.00000")
                .build();
    }

    @Test
    void testFailSaveEventContractNotSigned() {
        contractService.saveContract(1L, contractDto);

        ConflictException e = assertThrows(ConflictException.class,
                () -> eventService.saveEvent(1L, 1L, eventDto));
        Assertions.assertEquals(e.getMessage(), "It is impossible to create an event because there is no signed contract.");
    }

    @Test
    void testSaveEvent() {
        contractService.saveContract(1L, contractDto);
        contractService.sendContractForSigning(1L, 1L, 1L);
        contractService.reviewContractByPrincipal(1L, 1L, 1L, true);

        eventService.saveEvent(1L, 1L, eventDto);

        Assertions.assertEquals(eventDto.getEventName(), "Scarlet sails.");
        Assertions.assertEquals(eventDto.getRate(), 5000L);
        Assertions.assertEquals(eventDto.getEventDate(), "2023-07-05T20:30:00.00000");
    }
}
