package dmitreev.testwork.javalightapp.service;

import dmitreev.testwork.javalightapp.dto.EventDto;
import dmitreev.testwork.javalightapp.dto.NewEventDto;

import java.util.List;

public interface EventService {

    EventDto saveEvent(Long adminId, Long contractId, NewEventDto eventDto);

    EventDto getEventById(Long eventId);

    List<EventDto> getAllEventsByAdminIdWithSortFromNewToOld(Long adminId, Integer from, Integer size);

    void adminDeleteEvent(Long eventId, Long adminId);
}
