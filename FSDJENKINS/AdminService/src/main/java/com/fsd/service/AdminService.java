package com.fsd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsd.entity.Admin;
import com.fsd.repository.AdminRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }

    public Admin updateAdmin(Long id, Admin adminDetails) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            admin.setName(adminDetails.getName());
            admin.setPassword(adminDetails.getPassword());
            admin.setEmail(adminDetails.getEmail());
            return adminRepository.save(admin);
        } else {
            throw new IllegalArgumentException("Admin not found with id: " + id);
        }
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
}

