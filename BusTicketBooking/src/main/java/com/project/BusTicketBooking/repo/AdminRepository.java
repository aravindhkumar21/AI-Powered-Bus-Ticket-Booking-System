package com.project.BusTicketBooking.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.BusTicketBooking.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
