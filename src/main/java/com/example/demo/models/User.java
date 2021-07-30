package com.example.demo.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // xac dinh lop hien tại là một entity
@Table(name="Users") // xác định tên mà bảng ánh xạ sang database
public class User implements Serializable {
	@Id // xác định primary key trong bảng CSDL
	@Column(name = "username", length = 50) // xác định thuộc tính hiện tại của một cột trong CSDL
	private String username;
	
	@Column(name = "password", length = 50)
	private String password;
	
	@Column(name = "fullname", length = 50)
	private String fullname;
	
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "gender")
	private boolean gender;
			
	public User() {
		super();
	}

	public User(String username, String password, String fullname, Integer age, boolean gender) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.age = age;
		this.gender = gender;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}
	
	
	
	
}
