package com.project.BusTicketBooking.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.BusTicketBooking.dto.admin.AdminRequestDTO;
import com.project.BusTicketBooking.dto.admin.AdminResponseDTO;
import com.project.BusTicketBooking.dto.login.LoginRequestDTO;
import com.project.BusTicketBooking.dto.login.LoginResponseDTO;
import com.project.BusTicketBooking.model.Admin;
import com.project.BusTicketBooking.service.AdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admins")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Save Admin
    @PostMapping("/register-admin")
    public ResponseEntity<AdminResponseDTO> saveAdmin(@Valid @RequestBody AdminRequestDTO admin) {

    	AdminResponseDTO savedAdmin = adminService.saveAdmin(admin);

        return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
    }

    // Get All Admins
    @GetMapping("/alladmins")
    public ResponseEntity<List<AdminResponseDTO>> getAllAdmins() {

        List<AdminResponseDTO> admins = adminService.getAllAdmins();

        return ResponseEntity.ok(admins);
    }

    // Get Admin By Id
    @GetMapping("/admin/{id}")
    public ResponseEntity<AdminResponseDTO> getAdminById(@PathVariable Long id) {

    	AdminResponseDTO admin = adminService.getAdminById(id);

        return ResponseEntity.ok(admin);
    }

    // Update Admin
    @PutMapping("/update-admin/{id}")
    public ResponseEntity<AdminResponseDTO> updateAdmin(@PathVariable Long id,
                                             @Valid @RequestBody AdminRequestDTO admin) {

    	AdminResponseDTO updatedAdmin = adminService.updateAdmin(id, admin);

        return ResponseEntity.ok(updatedAdmin);
    }

    // Delete Admin
    @DeleteMapping("/delete-admin/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {

        adminService.deleteAdmin(id);

        return ResponseEntity.ok("Admin deleted successfully.");
    }
    
    @PostMapping("/login")
    public LoginResponseDTO login(@Valid @RequestBody LoginRequestDTO dto) {
        return adminService.login(dto);
    }

}
