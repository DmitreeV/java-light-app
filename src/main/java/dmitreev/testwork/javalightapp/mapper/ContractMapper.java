package dmitreev.testwork.javalightapp.mapper;

import dmitreev.testwork.javalightapp.dto.ContractDto;
import dmitreev.testwork.javalightapp.model.Contract;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContractMapper {

    @Mapping(target = "admin", source = "admin.id")
    @Mapping(target = "principal", source = "principal.inn")
    ContractDto toContractDto(Contract contract);

    @Mapping(target = "admin.id", source = "admin")
    @Mapping(target = "principal.inn", source = "principal")
    Contract toContract(ContractDto contractDto);
}
