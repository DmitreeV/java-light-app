package dmitreev.testwork.javalightapp.service;

import dmitreev.testwork.javalightapp.dto.ParticipationRequestDto;

import java.util.List;

public interface ParticipationRequestService {
    ParticipationRequestDto saveParticipationRequest(Long partId, Long eventId);

    List<ParticipationRequestDto> getParticipationRequests(Long partId);

    ParticipationRequestDto cancelParticipationRequest(Long partId, Long requestId);
}
