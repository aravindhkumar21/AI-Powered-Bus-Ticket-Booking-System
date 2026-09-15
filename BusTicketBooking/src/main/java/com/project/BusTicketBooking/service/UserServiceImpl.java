package com.project.BusTicketBooking.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.BusTicketBooking.dto.login.LoginRequestDTO;
import com.project.BusTicketBooking.dto.login.LoginResponseDTO;
import com.project.BusTicketBooking.dto.user.UserRequestDTO;
import com.project.BusTicketBooking.dto.user.UserResponseDTO;
import com.project.BusTicketBooking.exception.UserNotFoundException;
import com.project.BusTicketBooking.mapper.UserMapper;
import com.project.BusTicketBooking.model.User;
import com.project.BusTicketBooking.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	
	private final UserRepository userRepo;
	
	public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

	@Override
	public UserResponseDTO saveUser(UserRequestDTO dto) {
		User user=UserMapper.toEntity(dto);
		User savedUser = userRepo.save(user);
		return UserMapper.toResponseDTO(savedUser);
	}

	@Override
	public List<UserResponseDTO> getAllUsers() {
		return userRepo.findAll().stream().map(UserMapper::toResponseDTO).collect(Collectors.toList());
	}

	@Override
	public UserResponseDTO getUserById(Long id) {
		User user = userRepo.findById(id).orElseThrow(()->new UserNotFoundException("user id not found")) ;
		return UserMapper.toResponseDTO(user);
	}

	@Override
	public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {

		User user = userRepo.findById(id)
	            .orElseThrow(() ->
	                    new UserNotFoundException("User not found with id : " + id));

	    UserMapper.updateEntity(user, dto);

	    User updatedUser = userRepo.save(user);

	    return UserMapper.toResponseDTO(updatedUser);
	}

	@Override
	public void deleteUser(Long id) {
		Optional<User> check = userRepo.findById(id);
		if(check.isPresent()) {
			userRepo.deleteById(id);
		}else {
			throw new UserNotFoundException("user not found for deletion");
		}
	            
	}

	@Override
	public LoginResponseDTO login(LoginRequestDTO dto) {

	    User user = userRepo.findByEmail(dto.getEmail())
	            .orElseThrow(() -> new RuntimeException("Invalid email or password"));

	    if (!user.getPassword().equals(dto.getPassword())) {
	        throw new RuntimeException("Invalid email or password");
	    }

	    return new LoginResponseDTO(
	            user.getUserId(),
	            user.getName(),
	            user.getEmail(),
	            "USER",
	            "Login successful",
	            user.getPhone()
	    );
	}

}
