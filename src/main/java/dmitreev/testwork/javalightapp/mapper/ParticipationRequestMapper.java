package dmitreev.testwork.javalightapp.mapper;

import dmitreev.testwork.javalightapp.dto.ParticipationRequestDto;
import dmitreev.testwork.javalightapp.model.ParticipationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ParticipationRequestMapper {

    @Mapping(target = "requester", source = "requester.id")
    @Mapping(target = "event", source = "event.id")
    ParticipationRequestDto toParticipationRequestDto(ParticipationRequest participationRequest);

    @Mapping(target = "requester.id", source = "requester")
    @Mapping(target = "event.id", source = "event")
    ParticipationRequest toParticipationRequest(ParticipationRequestDto participationRequestDto);
}
