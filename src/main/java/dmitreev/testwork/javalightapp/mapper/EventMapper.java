package dmitreev.testwork.javalightapp.mapper;

import dmitreev.testwork.javalightapp.dto.EventDto;
import dmitreev.testwork.javalightapp.dto.NewEventDto;
import dmitreev.testwork.javalightapp.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "admin", source = "admin.id")
    EventDto toEventDto(Event event);

    @Mapping(target = "admin.id", source = "admin")
    Event toEvent(EventDto eventDto);

    Event toEvent(NewEventDto eventDto);
}
