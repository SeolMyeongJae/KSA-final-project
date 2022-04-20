package ksafinalproject.gbt.admin.repository;

import ksafinalproject.gbt.admin.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    List<Admin> findAllByAdminIdContains(String adminId);

}
