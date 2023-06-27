package dmitreev.testwork.javalightapp.mapper;

import dmitreev.testwork.javalightapp.dto.ContractDto;
import dmitreev.testwork.javalightapp.dto.ContractUpdateDto;
import dmitreev.testwork.javalightapp.dto.NewContractDto;
import dmitreev.testwork.javalightapp.model.Contract;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContractMapper {

    @Mapping(target = "admin", source = "admin.id")
    @Mapping(target = "principal", source = "principal.id")
    ContractDto toContractDto(Contract contract);

    Contract toContract(NewContractDto contractDto);

    @Mapping(target = "admin", source = "admin.id")
    @Mapping(target = "principal", source = "principal.id")
    ContractUpdateDto toContractUpdateDto(Contract contract);
}
