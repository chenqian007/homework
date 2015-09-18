package com.demo.service;
/*
 * 自定义一个异常类
 *   只是给出父类的构造器就好，方便用来创建对象
 */
public class UserException extends Exception {

	public UserException() {
		super();
	}

	public UserException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserException(String message) {
		super(message);
	}

	public UserException(Throwable cause) {
		super(cause);
	}
		
}
