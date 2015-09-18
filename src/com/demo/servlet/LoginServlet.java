package com.demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.domain.User;
import com.demo.service.UserException;
import com.demo.service.UserService;
import com.demo.service.UserServiceImple;
import com.wg.common.CommonUtils;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		/*
		 * 1,封装表单数据
		 */
		User user=CommonUtils.mapToBean(request.getParameterMap(),User.class);
		/*
		 * 调用Service方法
		 */
		UserService service=new UserServiceImple();
		try {
			service.login(user);
			/*
			 * 没有出现异常,登录成功
			 */
			request.getSession().setAttribute("user",user);
			response.sendRedirect(request.getContextPath()+"/Project/success.jsp");
		} catch (UserException e) {
			/*
			 * 出现异常,登录失败,获取失败原因并转发
			 */
			request.setAttribute("msg",e.getMessage());
			request.getRequestDispatcher("/Project/login.jsp").forward(request, response);
		}
	}

}
