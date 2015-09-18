package com.demo.service;

import com.demo.domain.User;

public interface UserService {
		/*
		 * 注册方法
		 */
	public void regist(User user) throws UserException;
		/*
		 * 登录方法
		 */
	public void login(User user) throws UserException;
}
