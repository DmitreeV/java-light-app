package dmitreev.testwork.javalightapp.mapper;

import dmitreev.testwork.javalightapp.dto.PrincipalDto;
import dmitreev.testwork.javalightapp.model.Principal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PrincipalMapper {

    PrincipalDto toPrincipalDto(Principal principal);

    Principal toPrincipal(PrincipalDto principalDto);
}
