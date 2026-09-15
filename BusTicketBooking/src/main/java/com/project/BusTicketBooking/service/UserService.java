package com.project.BusTicketBooking.service;

import java.util.List;

import com.project.BusTicketBooking.dto.login.LoginRequestDTO;
import com.project.BusTicketBooking.dto.login.LoginResponseDTO;
import com.project.BusTicketBooking.dto.user.UserRequestDTO;
import com.project.BusTicketBooking.dto.user.UserResponseDTO;

public interface UserService {
	
	UserResponseDTO saveUser(UserRequestDTO dto);

    List<UserResponseDTO> getAllUsers();

    UserResponseDTO getUserById(Long id);

    UserResponseDTO updateUser(Long id, UserRequestDTO dto);

    void deleteUser(Long id);
    
    LoginResponseDTO login(LoginRequestDTO dto);


}
