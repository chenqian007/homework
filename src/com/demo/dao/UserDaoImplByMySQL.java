package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.demo.domain.User;
import com.jdbc.utils.JdbcUtils;

public class UserDaoImplByMySQL implements UserDao{
	private Connection con=JdbcUtils.getConnection();
	/**
	 * 根据用户名来查找这个用户是否存在
	 */
	public User findUserByName(String username) {
		PreparedStatement ps=null;
		ResultSet rs=null;
		User user=null;
		/*
		 * 定制sql模板
		 */
		String findSql="select * from user where uname=?";
		/*
		 * 获得预处理语句
		 */
		try {
			ps=con.prepareStatement(findSql);
			/*
			 * 设置参数
			 */
			ps.setString(1,username);
			/*
			 * 获得结果集，并遍历
			 */
			rs=ps.executeQuery();
			if(rs.next()){
				user=new User();
				user.setUsername(rs.getString("uname"));
				user.setPassword(rs.getString("upass"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(ps!=null){
					ps.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return user;
	}
	/**
	 * 添加用户
	 */
	public void add(User user) {
		String sql="insert into user values(null,?,?)";
		PreparedStatement ps=null;
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1,user.getUsername());
			ps.setString(2,user.getPassword());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(ps!=null){
					ps.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
