package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;

public interface UserService {
	// Đây là nơi xử lý các bussiness logic

	void deleteAll();

	void deleteAll(List<User> entities);

	void deleteAllById(List<String> ids);

	void delete(User entity);

	void deleteById(String id);

	long count();

	List<User> findAllById(List<String> ids);

	List<User> findAll();

	boolean existsById(String id);

	Optional<User> findById(String id);

	List<User> saveAll(List<User> entities);

	User save(User entity);

	boolean checkLogin(String username, String password);

	
	
}
