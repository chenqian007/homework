package com.demo.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory {
		/*
		 * 通过加载配置文件来加载接口的实现类
		 */
	private static Properties config=null;
		/*
		 * 通过静态代码块加载只需要创建一次的对象
		 */
	static{
		/*
		 * 通过反射获得对象
		 */
		config=new Properties();
		/*
		 * 获得配置文件的流
		 */
		InputStream is=UserDaoFactory.class.getClassLoader().getResourceAsStream("config.properties");
		try {
			config.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * 获得UserDao接口
	 */
	public static UserDao getUserDao(){
		UserDao dao=null;
		try {
			Class object=Class.forName(config.getProperty("com.demo.dao.UserDao"));
			dao=(UserDao) object.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao;
	}
}
