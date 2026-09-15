package com.project.BusTicketBooking.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.BusTicketBooking.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
	Optional<User> findByEmail(String email);

}
