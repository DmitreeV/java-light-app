package dmitreev.testwork.javalightapp.service;

import dmitreev.testwork.javalightapp.dto.AdminDto;

public interface AdminService {

    AdminDto saveAdmin(AdminDto adminDto);

    void deleteAdmin(Long adminId);
}
