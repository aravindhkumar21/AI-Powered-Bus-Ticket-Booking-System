package com.project.BusTicketBooking.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.BusTicketBooking.model.User;

public interface UserRepository extends JpaRepository<User,Long>{

}
