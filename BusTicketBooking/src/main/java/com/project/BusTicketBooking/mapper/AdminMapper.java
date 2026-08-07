package com.project.BusTicketBooking.mapper;

import com.project.BusTicketBooking.dto.admin.AdminRequestDTO;
import com.project.BusTicketBooking.dto.admin.AdminResponseDTO;
import com.project.BusTicketBooking.model.Admin;

public class AdminMapper {

    // Convert RequestDTO -> Entity
    public static Admin toEntity(AdminRequestDTO dto) {

        Admin admin = new Admin();

        admin.setName(dto.getName());
        admin.setEmail(dto.getEmail());
        admin.setPassword(dto.getPassword());
        admin.setPhone(dto.getPhone());

        return admin;
    }

    // Convert Entity -> ResponseDTO
    public static AdminResponseDTO toResponseDTO(Admin admin) {

        AdminResponseDTO dto = new AdminResponseDTO();

        dto.setAdminId(admin.getAdminId());
        dto.setName(admin.getName());
        dto.setEmail(admin.getEmail());
        dto.setPhone(admin.getPhone());

        return dto;
    }
}
