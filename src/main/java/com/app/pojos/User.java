package com.app.pojos;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
//id | name    | email          | password | reg_amt | reg_date   | role
	private String name;

	private String email;

	private String password;

	@Column(name = "reg_amt")
	private double regAmt;

	@Column(name = "reg_date")
	private LocalDate regDate;

	@Enumerated(EnumType.STRING)
	private UserRole role;



	public User() {}

	public User(String name, String email, String password, double regAmt, LocalDate regDate, UserRole role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.regAmt = regAmt;
		this.regDate = regDate;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public double getRegAmt() {
		return regAmt;
	}

	public LocalDate getRegDate() {
		return regDate;
	}

	public UserRole getRole() {
		return role;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRegAmt(double regAmt) {
		this.regAmt = regAmt;
	}

	public void setRegDate(LocalDate regDate) {
		this.regDate = regDate;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User id : " + getId() + " [name=" + name + ", email=" + email + ", regAmt="
				+ regAmt + ", regDate=" + regDate + ", role=" + role + "]";
	}

}
