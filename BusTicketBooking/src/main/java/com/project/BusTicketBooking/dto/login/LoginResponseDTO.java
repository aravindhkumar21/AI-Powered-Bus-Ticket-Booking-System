package com.project.BusTicketBooking.dto.login;

public class LoginResponseDTO {

    private Long userId;
    private String name;
    private String email;
    private String message;
    private String role;
    private String phone;

    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LoginResponseDTO() {
    }


    public LoginResponseDTO(Long userId, String name, String email, String role, String message, String phone) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.message = message;
		this.role = role;
		this.phone=phone;
	}

	public Long getUserId() {
        return userId;
    }

    public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
