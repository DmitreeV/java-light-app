package dmitreev.testwork.javalightapp.service;

import dmitreev.testwork.javalightapp.dto.ParticipantDto;

public interface ParticipantService {

    ParticipantDto saveParticipant(ParticipantDto participantDto);

    void deleteParticipant(Long partId);
}
