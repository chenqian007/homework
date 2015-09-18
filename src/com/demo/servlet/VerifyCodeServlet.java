package com.demo.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wg.verify.VerifyCode;

public class VerifyCodeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		/*
		 * 输出验证码图片
		 */
		VerifyCode verifyCode=new VerifyCode();
		BufferedImage img=verifyCode.getVerifyCodeImage();
		String text=verifyCode.getText();
		/*
		 * 显示图片并把该图片的验证码文字保存到session中
		 */
		HttpSession session=request.getSession();
		session.setAttribute("verifyCode", text);
		VerifyCode.outputImage(img, response.getOutputStream());
		
	}

}
