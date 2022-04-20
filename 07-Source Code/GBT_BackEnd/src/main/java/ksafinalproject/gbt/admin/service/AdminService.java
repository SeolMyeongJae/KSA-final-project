package ksafinalproject.gbt.admin.service;

import ksafinalproject.gbt.admin.model.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    int saveAdmin(Admin admin);

    int updateAdmin(Admin admin, Long id);

    Optional<Admin> getAdminById(Long id);

    List<Admin> getAllAdmin();

    int deleteAdminById(Long id);

    List<Admin> getAllAdminByAdminIdContains(String adminId);

}
