package dmitreev.testwork.javalightapp.service;

import dmitreev.testwork.javalightapp.dto.EventDto;
import dmitreev.testwork.javalightapp.dto.NewEventDto;

import java.util.List;

public interface EventService {

    EventDto saveEvent(Long adminId, Long contractId, NewEventDto eventDto);

    List<EventDto> getAllEventsByAdminIdWithSortFromNewToOld(Long adminId, Integer from, Integer size);

    EventDto cancelEventByAdmin(Long adminId, Long eventId);

    void adminDeleteEvent(Long eventId, Long adminId);
}
