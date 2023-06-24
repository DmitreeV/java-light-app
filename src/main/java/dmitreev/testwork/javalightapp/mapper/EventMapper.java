package dmitreev.testwork.javalightapp.mapper;

import dmitreev.testwork.javalightapp.dto.EventDto;
import dmitreev.testwork.javalightapp.model.Event;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventDto toAdminDto(Event event);

    Event toEvent(EventDto eventDto);
}
