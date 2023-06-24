package dmitreev.testwork.javalightapp.mapper;

import dmitreev.testwork.javalightapp.dto.ParticipantDto;
import dmitreev.testwork.javalightapp.model.Participant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParticipantMapper {

    ParticipantDto toParticipantDto(Participant participant);

    Participant toParticipant(ParticipantDto participantDto);
}
