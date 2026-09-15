package com.project.BusTicketBooking.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.BusTicketBooking.dto.admin.AdminRequestDTO;
import com.project.BusTicketBooking.dto.admin.AdminResponseDTO;
import com.project.BusTicketBooking.dto.login.LoginRequestDTO;
import com.project.BusTicketBooking.dto.login.LoginResponseDTO;
import com.project.BusTicketBooking.exception.AdminNotFoundException;
import com.project.BusTicketBooking.mapper.AdminMapper;
import com.project.BusTicketBooking.model.Admin;
import com.project.BusTicketBooking.model.User;
import com.project.BusTicketBooking.repo.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {
	
	private final AdminRepository adminRepo;
	
	public AdminServiceImpl(AdminRepository adminRepo) {
		this.adminRepo=adminRepo;
	}

	@Override
	public AdminResponseDTO saveAdmin(AdminRequestDTO dto) {
		Admin admin = AdminMapper.toEntity(dto);

	    Admin savedAdmin = adminRepo.save(admin);

	    return AdminMapper.toResponseDTO(savedAdmin);
	}

	@Override
	public List<AdminResponseDTO> getAllAdmins() {
	    return adminRepo.findAll()
	            .stream()
	            .map(AdminMapper::toResponseDTO)
	            .collect(Collectors.toList());
	}

	@Override
	public AdminResponseDTO getAdminById(Long id) {
		Admin admin = adminRepo.findById(id).orElseThrow(()->new AdminNotFoundException("Admin id : "+id+" not found"));
		return AdminMapper.toResponseDTO(admin);
	}

	@Override
	public AdminResponseDTO updateAdmin(Long id, AdminRequestDTO dto) {
	    Admin admin = adminRepo.findById(id)
	            .orElseThrow(() ->
	                    new AdminNotFoundException("Admin not found with id : " + id));

	    admin.setName(dto.getName());
	    admin.setEmail(dto.getEmail());
	    admin.setPassword(dto.getPassword());
	    admin.setPhone(dto.getPhone());

	    Admin updatedAdmin = adminRepo.save(admin);
	    return AdminMapper.toResponseDTO(updatedAdmin);
	}

	@Override
	public void deleteAdmin(Long id) {
		Admin existingAdmin = adminRepo.findById(id).orElseThrow(()->new AdminNotFoundException("Admin id : "+id+" not found for deletion"));
		adminRepo.deleteById(id);
	}

	@Override
	public LoginResponseDTO login(LoginRequestDTO dto) {

	    Admin admin = adminRepo.findByEmail(dto.getEmail())
	            .orElseThrow(() -> new RuntimeException("Invalid email or password"));

	    if (!admin.getPassword().equals(dto.getPassword())) {
	        throw new RuntimeException("Invalid email or password");
	    }

	    return new LoginResponseDTO(
	    	    admin.getAdminId(),
	    	    admin.getName(),
	    	    admin.getEmail(),
	    	    "ADMIN",
	    	    "Login successful",
	    	    admin.getPhone()
	    	);
	}

}
