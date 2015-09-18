package com.demo.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.domain.User;
import com.demo.service.UserException;
import com.demo.service.UserService;
import com.demo.service.UserServiceImple;
import com.wg.common.CommonUtils;

public class RegistServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		/*
		 * 创建Map<String,String>错误信息的集合
		 */
		Map<String,String> errors=new HashMap<String,String>();
		/*
		 * 1,封装表单数据(使用自己的beanUtils)
		 */
		User user=CommonUtils.mapToBean(request.getParameterMap(),User.class);
		/*
		 * 表单校验
		 */
		/*
		 * 验证用户名,密码
		 */
		if(user.getUsername()==null||user.getUsername().trim().isEmpty()){
			errors.put("username","用户名不能为空");
		}else if(user.getUsername().length()<3||user.getUsername().length()>20){
			errors.put("username","用户名长度在3-20之间");
		}
		if(user.getPassword()==null||user.getPassword().trim().isEmpty()){
			errors.put("password","密码不能为空");
		}else if(user.getPassword().length()<3||user.getPassword().length()>20){
			errors.put("password","密码长度在3-20之间");
		}
		/*
		 * 判断验证码是否正确
		 */
		HttpSession session=request.getSession();
		String code=(String)session.getAttribute("verifyCode");
		if(user.getVerifyCode().isEmpty()||user.getVerifyCode()==null){
			//如果验证码为空
			errors.put("verifyCode","验证码不能为空！");
		}else{
			if(!code.equalsIgnoreCase(user.getVerifyCode())){
				errors.put("verifyCode","验证码错误,请重新输入！");
			}
		}
		/*
		 * 校验处理
		 */
		if(errors!=null&&errors.size()>0){
			request.setAttribute("user",user);
			request.setAttribute("errors",errors);
			request.getRequestDispatcher("/Project/regist.jsp").forward(request, response);
			return;
		}
		/*
		 * 2,调用Service的regist方法
		 */
		UserService service=new UserServiceImple();
		try {
			/*
			 * 验证码如果正确了,再注册用户!
			 */
			service.regist(user);
			/*
			 * 跳转到注册成功
			 */
			response.getWriter().write("<body align='center'><h1>注册成功</h1>" +
					"<a href='"+request.getContextPath()+"/index.jsp'>返回主页</a>" +
							"&nbsp&nbsp<a href='"+request.getContextPath()+"/Project/login.jsp'>登录</a></body>");
			
		} catch (UserException e) {
			/*
			 * 为了回显数据,将错误的信息保存到request
			 */
			request.setAttribute("user",user);
			/*
			 * 抛出异常的话,就把错误信息报错到request域中,并转发
			 */
			request.setAttribute("msg",e.getMessage());
			request.getRequestDispatcher("/Project/regist.jsp").forward(request, response);
		}
	}

}
