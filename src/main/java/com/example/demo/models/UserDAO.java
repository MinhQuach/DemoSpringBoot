package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	// dinh nghia cac phuong thuc them nhap xoa sua;
	
	public static List<User> ls = new ArrayList<>();
	
	public User findByUsername(String username) {
		for(User user: ls) {
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		
		return null;		
	}
	
	public int update(User user) {
		for (int i = 0; i < ls.size(); i++) {
			if(ls.get(i).getUsername().equals(user.getUsername())) {
				ls.set(i, user);
				return i;				
			}			
		}
		return 0;
	}
	
	public int save(User user) {
		if(findByUsername(user.getUsername()) != null) {
			update(user);
		}else {
			ls.add(user);
		}
		return 1;
	}
	
	public int delete(String user) {
		User u = findByUsername(user);
		if(u != null) {
			ls.remove(u);
			return 1;
		}
		return 0;
	}
	
	public List<User> getAll(){
		return ls;
	}
	
	
	

}
