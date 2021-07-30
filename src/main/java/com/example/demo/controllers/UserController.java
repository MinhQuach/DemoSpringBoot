package com.example.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.User;
import com.example.demo.models.UserDAO;
import com.example.demo.services.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String addOrEdit(ModelMap model) {
		User u = new User();
//		u.setUsername("NameNV");
		model.addAttribute("USER", u);
		model.addAttribute("ACTION", "saveOrUpdate");
		return "register-user";
	}

	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(ModelMap model, @ModelAttribute("USER") User user) {
//		UserDAO dao = new UserDAO();
//		dao.save(user);
		userService.save(user);
		return "register-user";
	}

	@RequestMapping("list")
	public String list(ModelMap model) {
//		UserDAO dao = new UserDAO();		
		model.addAttribute("USERS", userService.findAll());
		return "view-user";
	}

	@RequestMapping("/edit/{username}")
	public String edit(ModelMap model, @PathVariable(name = "username") String username) {
//		UserDAO dao = new UserDAO();
		Optional<User> u = userService.findById(username);
		if(u.isPresent()) {
			model.addAttribute("USER", u.get());
		}else {
			model.addAttribute("USER", new User());
		}		
		model.addAttribute("ACTION", "/saveOrUpdate");
		return "register-user";
	}

	@RequestMapping("/delete/{username}")
	public String delete(ModelMap model, @PathVariable(name = "username") String username) {
//		UserDAO dao = new UserDAO();
//		dao.delete(username);
		userService.deleteById(username);
		model.addAttribute("USERS", userService.findAll());
		return "view-user";
	}
}
