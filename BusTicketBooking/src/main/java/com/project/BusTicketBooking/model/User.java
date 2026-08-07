package com.project.BusTicketBooking.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Column(unique = true)
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @Pattern(regexp = "^[6-9]\\d{9}$",message = "Phone number must be 10 digits")
    private String phone;

    @Size(min = 6, message = "Password must contain at least 6 characters")
    private String password;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Booking> bookings;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	@Override
	public String toString() {
	    return "User{" +
	            "userId=" + userId +
	            ", name='" + name + '\'' +
	            ", email='" + email + '\'' +
	            ", phone='" + phone + '\'' +
	            '}';
	}

	public User(Long userId, String name, String email, String phone, String password, List<Booking> bookings) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.bookings = bookings;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
    

}
