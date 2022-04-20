package ksafinalproject.gbt.admin.controller;

import io.swagger.annotations.Api;
import ksafinalproject.gbt.admin.model.Admin;
import ksafinalproject.gbt.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = {"어드민"})
@RestController
@CrossOrigin
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final AdminService adminService;

    @PostMapping("")
    public int adminSave(@RequestBody Admin admin) {
        try {
            return adminService.saveAdmin(admin);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @PutMapping("/{id}")
    public int adminUpdate(@RequestBody Admin admin, @PathVariable Long id) {
        try {
            return adminService.updateAdmin(admin, id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @GetMapping("{id}")
    public Optional<Admin> adminGetById(@PathVariable Long id) {
        try {
            return adminService.getAdminById(id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @GetMapping("/all")
    public List<Admin> adminGetAll() {
        try {
            return adminService.getAllAdmin();
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public int adminDeleteById(@PathVariable Long id) {
        try {
            return adminService.deleteAdminById(id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @GetMapping("/all/admin/{adminId}")
    public List<Admin> adminGetAllByAdminIdContains(@PathVariable String adminId) {
        try {
            return adminService.getAllAdminByAdminIdContains(adminId);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }
}
