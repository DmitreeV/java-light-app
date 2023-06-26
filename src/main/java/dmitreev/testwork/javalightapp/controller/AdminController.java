package dmitreev.testwork.javalightapp.controller;

import dmitreev.testwork.javalightapp.dto.AdminDto;
import dmitreev.testwork.javalightapp.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/admin")
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public AdminDto saveAdmin(@Valid @RequestBody AdminDto adminDto) {
        return adminService.saveAdmin(adminDto);
    }

    @DeleteMapping("/{adminId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAdmin(@PathVariable Long adminId) {
        adminService.deleteAdmin(adminId);
    }
}
