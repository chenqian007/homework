package com.demo.service;

import com.demo.dao.UserDao;
import com.demo.dao.UserDaoFactory;
import com.demo.dao.UserDaoImpl;
import com.demo.domain.User;

public class UserServiceImple implements UserService {
	/*
	 * 数据处理对象
	 */
	UserDao dao=UserDaoFactory.getUserDao();
	/*
	 *注册方法
	 */
	public void regist(User user) throws UserException {
			/*
			 * 判断该用户是否被注册过了
			 */
			User newUser=dao.findUserByName(user.getUsername());
			/*
			 * 注册过了,抛出自定义异常
			 */
			if(newUser!=null){
				throw new UserException("账号"+newUser.getUsername()+"已经被注册了!");
			}
			/*
			 * 没有注册过,就添加到数据库
			 */
			dao.add(user);
	}
	/*
	 *登录方法
	 */
	public void login(User user) throws UserException {
		   User newUser=dao.findUserByName(user.getUsername());
		   if(newUser==null){
			   throw new UserException("用户"+user.getUsername()+"不存在!");
		   }else{
			   if(!user.getPassword().equals(newUser.getPassword())){
				 throw new UserException("用户名或者密码错误!");  
			   }
		   }
	}

}
