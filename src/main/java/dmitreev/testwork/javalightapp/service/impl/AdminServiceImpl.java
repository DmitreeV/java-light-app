package dmitreev.testwork.javalightapp.service.impl;

import dmitreev.testwork.javalightapp.dto.AdminDto;
import dmitreev.testwork.javalightapp.error.exception.ConflictException;
import dmitreev.testwork.javalightapp.mapper.AdminMapper;
import dmitreev.testwork.javalightapp.model.Admin;
import dmitreev.testwork.javalightapp.repository.AdminRepository;
import dmitreev.testwork.javalightapp.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    @Override
    public AdminDto saveAdmin(AdminDto adminDto) {
        Admin admin = adminMapper.toAdmin(adminDto);
        try {
            adminRepository.save(admin);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Admin is already exists.");
        }
        log.info("Saved new admin {}.", adminDto);
        return adminMapper.toAdminDto(admin);
    }

    @Override
    public void deleteAdmin(Long adminId) {
        log.info("User with userId {} is deleted.", adminId);
        adminRepository.deleteById(adminId);
    }
}
