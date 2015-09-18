package com.jdbc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
		private static Properties config=null;
		static{
			config=new Properties();
			InputStream is=JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
			try {
				config.load(is);
				/*
				 * 加载驱动
				 */
				Class.forName(config.getProperty("driverClassName"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*
		 * 获得Jdbc连接
		 */
		public static Connection getConnection(){
			Connection con=null;
			try {
				con=DriverManager.getConnection(config.getProperty("url"),config.getProperty("username"),config.getProperty("password"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return con;
		}
}
