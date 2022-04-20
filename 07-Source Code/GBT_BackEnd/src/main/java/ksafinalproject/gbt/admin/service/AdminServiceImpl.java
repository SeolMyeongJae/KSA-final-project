package ksafinalproject.gbt.admin.service;

import ksafinalproject.gbt.admin.model.Admin;
import ksafinalproject.gbt.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public int saveAdmin(Admin admin) {
        log.info("save admin : {}", admin);
        try {
            adminRepository.save(admin);
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Transactional
    @Override
    public int updateAdmin(Admin admin, Long id) {
        log.info("update admin : {}, id : {}", admin, id);
        try {
            Admin admin2 = adminRepository.findById(id).orElseThrow();
            admin2.setAdminId(admin.getAdminId());
            admin2.setPassword(admin.getPassword());
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Override
    public Optional<Admin> getAdminById(Long id) {
        log.info("find admin by id : {}", id);
        try {
            return adminRepository.findById(id);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<Admin> getAllAdmin() {
        log.info("find all admin");
        try {
            return adminRepository.findAll();
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public int deleteAdminById(Long id) {
        log.info("delete admin by id : {}", id);
        try {
            adminRepository.deleteById(id);
            return 1;
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return -1;
        }
    }

    @Override
    public List<Admin> getAllAdminByAdminIdContains(String adminId) {
        log.info("find all admin by admin id contains : {}", adminId);
        try {
            return adminRepository.findAllByAdminIdContains(adminId);
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());
            return null;
        }
    }
}
