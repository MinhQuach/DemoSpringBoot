package com.example.demo.controllers;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.User;
import com.example.demo.models.UserDAO;
import com.example.demo.services.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("/register")
	public String addOrEdit(ModelMap model) {
		User u = new User();
		model.addAttribute("USER", u);
		model.addAttribute("ACTION", "saveOrUpdate");
		return "register-user";
	}

	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(ModelMap model, @ModelAttribute("USER") User user) {
//		UserDAO dao = new UserDAO();
//		dao.save(user);
		Optional<User> u = userService.findById(user.getUsername());
		if (u.isPresent()) {
			userService.save(user);
			model.addAttribute("MSG", "Edit User Successful");
			return "register-user";
		} else {
			userService.save(user);
			model.addAttribute("MSG", "Register Successful");
			return "register-user";

		}
	}

//	@RequestMapping("list")
//	public String list(ModelMap model, HttpSession session) {
////		UserDAO dao = new UserDAO();		
//		if (session.getAttribute("USERNAME") != null) {
//			model.addAttribute("USERS", userService.findAll());
//			return "view-user";
//		} else {
//			return "login";
//		}
//	}

	@RequestMapping("/edit/{username}")
	public String edit(ModelMap model, @PathVariable(name = "username") String username) {
//		UserDAO dao = new UserDAO();
		Optional<User> u = userService.findById(username);
		if (u.isPresent()) {
			model.addAttribute("USER", u.get());
		} else {
			model.addAttribute("USER", new User());
		}
		model.addAttribute("ACTION", "/saveOrUpdate");
		return "register-user";
	}

	@RequestMapping("/delete/{username}")
	public String delete(ModelMap model, @PathVariable(name = "username") String username) {
//		UserDAO dao = new UserDAO();
		userService.deleteById(username);
		model.addAttribute("USERS", userService.findAll());
		return "view-user";
	}

	// =======================================
	// login
	@RequestMapping("/")
	public String showLogin(ModelMap model, HttpSession session) {
		if (session.getAttribute("USERNAME") != null) {
			String username = session.getAttribute("USERNAME").toString();
			if(username.equals("admin")) {
				model.addAttribute("USERS", userService.findAll());				
				return "view-user";
			}else {
				Optional<User> u = userService.findById(username);
				model.addAttribute("USER", u.get());
				return "UserDetail";
			}	
		} else {
			return "login";
		}
	}

	// check user login
	@PostMapping("/checkLogin")
	public String checkLogin(ModelMap model, @RequestParam("username") String username,
			@RequestParam("password") String password, HttpSession ses) {
		if (userService.checkLogin(username, password)) {
			if(username.equals("admin")) {
				model.addAttribute("USERS", userService.findAll());
				ses.setAttribute("USERNAME", username);
				return "view-user";
			}else {
				Optional<User> u = userService.findById(username);
				ses.setAttribute("USERNAME", username);
				model.addAttribute("USER", u.get());
				return "UserDetail";
			}		
		} else {
			model.addAttribute("ERROR", "Username or Password not exist");
			return "login";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("USERNAME");
		return "login";
	}
		
}
