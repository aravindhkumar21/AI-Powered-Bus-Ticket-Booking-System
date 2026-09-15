package com.project.BusTicketBooking.service;

import java.util.List;

import com.project.BusTicketBooking.dto.admin.AdminRequestDTO;
import com.project.BusTicketBooking.dto.admin.AdminResponseDTO;
import com.project.BusTicketBooking.dto.login.LoginRequestDTO;
import com.project.BusTicketBooking.dto.login.LoginResponseDTO;
import com.project.BusTicketBooking.model.Admin;

public interface AdminService {
	
	AdminResponseDTO saveAdmin(AdminRequestDTO dto);

    List<AdminResponseDTO> getAllAdmins();

    AdminResponseDTO getAdminById(Long id);

    AdminResponseDTO updateAdmin(Long id, AdminRequestDTO dto);

    void deleteAdmin(Long id);
    
    LoginResponseDTO login(LoginRequestDTO dto);
    
}
