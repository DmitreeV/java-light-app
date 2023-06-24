package dmitreev.testwork.javalightapp.mapper;

import dmitreev.testwork.javalightapp.dto.AdminDto;
import dmitreev.testwork.javalightapp.model.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    AdminDto toAdminDto(Admin admin);

    Admin toAdmin(AdminDto adminDto);
}
