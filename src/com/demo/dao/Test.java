package com.demo.dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import com.demo.domain.User;

public class Test {
		public static void main(String[] args) {
			User user=new User();
			user.setUsername("王刚");
			user.setPassword("123");
			UserDao dao=new UserDaoImpl();
//			dao.add(user);
			User u=dao.findUserByName("wang");
			System.out.println(u);
	
		}
}
