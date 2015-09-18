package com.demo.dao;

import com.demo.domain.User;

/*
 * Data Access Object
 * 数据库操作
 */
public interface UserDao {
	//查找用户
	public User findUserByName(String username);
	//添加用户
	public void add(User user);
}
