package dmitreev.testwork.javalightapp.service.impl;

import dmitreev.testwork.javalightapp.dto.ParticipationRequestDto;
import dmitreev.testwork.javalightapp.enums.EventStatus;
import dmitreev.testwork.javalightapp.enums.PCRtest;
import dmitreev.testwork.javalightapp.enums.RequestStatus;
import dmitreev.testwork.javalightapp.error.exception.ConflictException;
import dmitreev.testwork.javalightapp.error.exception.NotFoundException;
import dmitreev.testwork.javalightapp.mapper.ParticipationRequestMapper;
import dmitreev.testwork.javalightapp.model.Event;
import dmitreev.testwork.javalightapp.model.Participant;
import dmitreev.testwork.javalightapp.model.ParticipationRequest;
import dmitreev.testwork.javalightapp.repository.EventRepository;
import dmitreev.testwork.javalightapp.repository.ParticipantRepository;
import dmitreev.testwork.javalightapp.repository.ParticipationRequestRepository;
import dmitreev.testwork.javalightapp.service.ParticipationRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ParticipationRequestServiceImpl implements ParticipationRequestService {

    private final ParticipationRequestRepository requestRepository;
    private final EventRepository eventRepository;
    private final ParticipantRepository participantRepository;
    private final ParticipationRequestMapper participationRequestMapper;


    @Override
    public ParticipationRequestDto saveParticipationRequest(Long partId, Long eventId) {
        if (requestRepository.findByEventIdAndRequesterId(eventId, partId) != null) {
            throw new ConflictException("You cannot add a repeat request.");
        }
        Participant part = getParticipant(partId);
        Event event = getEvent(eventId);

        if (!event.getStatus().equals(EventStatus.PUBLISHED)) {
            throw new ConflictException("You cannot add a request for an unpublished event.");
        }

        ParticipationRequest request = new ParticipationRequest();
        request.setCreated(LocalDateTime.now());
        request.setRequester(part);
        request.setEvent(event);

        if (Boolean.TRUE.equals(event.getRequestModeration())) {
            request.setStatus(RequestStatus.PENDING);
        } else {
            request.setStatus(RequestStatus.CONFIRMED);
        }

        log.info("The participation request has been saved.");
        return participationRequestMapper.toParticipationRequestDto(requestRepository.save(request));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ParticipationRequestDto> getParticipationRequests(Long partId) {

        List<ParticipationRequest> requests = requestRepository.findAllByRequesterId(partId);
        log.info("Information about participant requests has been received.");
        return requests.stream()
                .map(participationRequestMapper::toParticipationRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    public ParticipationRequestDto rejectionParticipationRequest(Long partId, Long requestId) {
        ParticipationRequest request = requestRepository.findByIdAndRequesterId(requestId, partId)
                .orElseThrow(() -> new ConflictException("Only the person who left the request can cancel it."));
        if (request.getRequester().getPcrTest().equals(PCRtest.NO)) {
            request.setStatus(RequestStatus.REJECTED);
        }
        log.info("The request was rejected.");
        return participationRequestMapper.toParticipationRequestDto(requestRepository.save(request));
    }

    @Override
    public ParticipationRequestDto cancelParticipationRequest(Long partId, Long requestId) {
        ParticipationRequest request = requestRepository.findByIdAndRequesterId(requestId, partId)
                .orElseThrow(() -> new ConflictException("Only the person who left the request can cancel it."));
        request.setStatus(RequestStatus.CANCELED);
        log.info("The request was canceled.");
        return participationRequestMapper.toParticipationRequestDto(requestRepository.save(request));
    }


    private Participant getParticipant(Long partId) {
        return participantRepository.findById(partId).orElseThrow(() ->
                new NotFoundException(String.format("Participant with id=%d not found", partId)));
    }

    private Event getEvent(Long eventId) {
        return eventRepository.findById(eventId).orElseThrow(() ->
                new NotFoundException(String.format("Event with id=%d not found", eventId)));
    }
}
