package com.user.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "userName")
	private String userName;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User() {

	}

	public User(String name, String userName, String email, String phone) {
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	@Override
	public String toString() {
		return "User [Name=" + name + ", UserName=" + userName + ", Email=" + email + ", Phone=" + phone + "]";
	}

}
