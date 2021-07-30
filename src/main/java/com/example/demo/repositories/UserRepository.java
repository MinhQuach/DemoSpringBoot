package com.example.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.User;

public interface UserRepository extends CrudRepository<User, String> {
	// Đây là một interface giúp chúng ta thao tác với CSDL
	// Trong Spring Data JPA có hỗ trợ "CRUD Repository" nó đã cung cấp các phương thức CRUD cơ bản như save,delete,findById.
	// Khi chúng ta kế thừa Spring Data JPA sẽ dúng các generic và reflection
	// để sinh ra implementation tương ứng với interface.
	// Tham số trong CrudRepository sẽ là tên entity(User) và kiểu dữ liệu của Primary key(String)--Username

}
