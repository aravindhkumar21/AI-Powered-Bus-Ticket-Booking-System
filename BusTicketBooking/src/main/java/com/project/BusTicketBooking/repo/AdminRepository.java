package com.project.BusTicketBooking.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.BusTicketBooking.model.Admin;
import com.project.BusTicketBooking.model.User;

public interface AdminRepository extends JpaRepository<Admin, Long> {
	Optional<Admin> findByEmail(String email);
}
