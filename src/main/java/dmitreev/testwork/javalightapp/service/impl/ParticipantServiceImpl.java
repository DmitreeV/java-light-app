package dmitreev.testwork.javalightapp.service.impl;

import dmitreev.testwork.javalightapp.dto.ParticipantDto;
import dmitreev.testwork.javalightapp.error.exception.ConflictException;
import dmitreev.testwork.javalightapp.mapper.ParticipantMapper;
import dmitreev.testwork.javalightapp.model.Participant;
import dmitreev.testwork.javalightapp.repository.ParticipantRepository;
import dmitreev.testwork.javalightapp.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepository;
    private final ParticipantMapper participantMapper;

    @Override
    public ParticipantDto saveParticipant(ParticipantDto participantDto) {
        Participant part = participantMapper.toParticipant(participantDto);
        try {
            participantRepository.save(part);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Participant is already exists.");
        }
        log.info("Saved new participant {}.", participantDto);
        return participantMapper.toParticipantDto(part);
    }

    @Override
    public void deleteParticipant(Long partId) {
        log.info("Participant with partId {} is deleted.", partId);
        participantRepository.deleteById(partId);
    }
}
