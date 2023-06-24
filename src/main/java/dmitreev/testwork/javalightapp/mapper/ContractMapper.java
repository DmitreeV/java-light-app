package dmitreev.testwork.javalightapp.mapper;

import dmitreev.testwork.javalightapp.dto.ContractDto;
import dmitreev.testwork.javalightapp.model.Contract;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContractMapper {

    ContractDto toContractDto(Contract contract);

    Contract toContract(ContractDto contractDto);
}
