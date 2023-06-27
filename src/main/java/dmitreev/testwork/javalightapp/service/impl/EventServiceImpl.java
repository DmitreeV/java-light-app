package dmitreev.testwork.javalightapp.service.impl;

import dmitreev.testwork.javalightapp.dto.EventDto;
import dmitreev.testwork.javalightapp.dto.NewEventDto;
import dmitreev.testwork.javalightapp.enums.EventStatus;
import dmitreev.testwork.javalightapp.error.exception.ConflictException;
import dmitreev.testwork.javalightapp.error.exception.NotFoundException;
import dmitreev.testwork.javalightapp.mapper.EventMapper;
import dmitreev.testwork.javalightapp.model.Admin;
import dmitreev.testwork.javalightapp.model.Contract;
import dmitreev.testwork.javalightapp.enums.ContractStatus;
import dmitreev.testwork.javalightapp.model.Event;
import dmitreev.testwork.javalightapp.repository.AdminRepository;
import dmitreev.testwork.javalightapp.repository.ContractRepository;
import dmitreev.testwork.javalightapp.repository.EventRepository;
import dmitreev.testwork.javalightapp.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final AdminRepository adminRepository;
    private final ContractRepository contractRepository;
    private final EventMapper eventMapper;

    @Override
    public EventDto saveEvent(Long adminId, Long contractId, NewEventDto eventDto) {
        Admin admin = getAdmin(adminId);

        Contract contract = contractRepository.findByNumberAndAdminId(contractId, adminId)
                .orElseThrow(() -> new ConflictException("Only the contract creator can send it for signing."));

        if (!contract.getStatus().equals(ContractStatus.SIGNED)) {
            throw new ConflictException("It is impossible to create an event because there is no signed contract.");
        }
        Event event = eventMapper.toEvent(eventDto);
        event.setAdmin(admin);
        event.setCreatedOn(LocalDateTime.now());
        event.setStatus(EventStatus.PUBLISHED);
        log.info("New event {} has been created by the administrator with id {}.", eventDto, adminId);
        return eventMapper.toEventDto(eventRepository.save(event));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventDto> getAllEventsByAdminIdWithSortFromNewToOld(Long adminId, Integer from, Integer size) {
        getAdmin(adminId);
        Page<Event> events = eventRepository.findAllByAdminIdOrderByCreatedOnDesc(adminId, PageRequest.of(from / size, size));

        log.info("Received a list of all-admin with id {} events sorted from newer to older.", adminId);
        return events.stream()
                .map(eventMapper::toEventDto)
                .collect(Collectors.toList());
    }

    @Override
    public EventDto cancelEventByAdmin(Long adminId, Long eventId) {

        Event event = eventRepository.findByIdAndAdminId(eventId, adminId)
                .orElseThrow(() -> new ConflictException("Only the event organizer can cancel it."));

        event.setStatus(EventStatus.CANCELED);
        ;
        log.info("The event cancelled by the admin with id {}.", adminId);
        return eventMapper.toEventDto(eventRepository.save(event));
    }

    @Override
    public void adminDeleteEvent(Long eventId, Long adminId) {
        getAdmin(adminId);
        Event event = getEvent(eventId);

        if (!Objects.equals(event.getAdmin().getId(), adminId)) {
            throw new ConflictException("Only the creator of the event can delete it.");
        }
        if (event.getStatus() != (EventStatus.CANCELED)) {
            throw new ConflictException("You can only delete events with the CANCELED status.");
        }

        eventRepository.deleteById(eventId);
        log.info("The event was deleted by the admin with id {}.", adminId);
    }

    private Admin getAdmin(Long adminId) {
        return adminRepository.findById(adminId).orElseThrow(() ->
                new NotFoundException(String.format("Admin with id=%d not found", adminId)));
    }

    private Event getEvent(Long eventId) {
        return eventRepository.findById(eventId).orElseThrow(() ->
                new NotFoundException(String.format("Event with id=%d not found", eventId)));
    }
}
