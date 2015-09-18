package com.demo.dao;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import javax.management.RuntimeErrorException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.jaxen.saxpath.base.XPathReader;

import com.demo.domain.User;

public class UserDaoImpl implements UserDao {
	String path="e:/db.xml";
	/*
	 * 查找用户
	 */
	public User findUserByName(String username) {
		/*
		 * 1,创建解析器
		 */
		SAXReader reader=new SAXReader();
		try {
			Document document=reader.read(path);
			/*
			 * 2,通过XPath查询
			 * 两条/代表无限查询,包括n多节点,后面表示要查询的属性值
			 */
			Element ele=(Element) document.selectSingleNode("//user[@username='"+username+"']");
			
			if(ele==null){
				return null;
			}
			/*
			 * 如果查出东西,封装操作
			 */
			User u=new User();
			String uname=ele.attributeValue("username");
			String upass=ele.attributeValue("password");
			u.setUsername(uname);
			u.setPassword(upass);
			return u;
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
	}
	/*
	 * 添加用户
	 */
	public void add(User user) {
		System.out.println(path);
		SAXReader reader=new SAXReader();
		try {
			Document document=reader.read(path);
			/*
			 * 通过根元素创建新的元素
			 */
			Element ele=document.getRootElement();
			Element newUser=ele.addElement("user");
			newUser.addAttribute("username",user.getUsername());
			newUser.addAttribute("password",user.getPassword());
			/*
			 * 保存文档
			 */
			OutputFormat format=new OutputFormat("\t", true);//缩进使用\t，是否换行
			format.setTrimText(true);//清空原来的换行和缩进
			
			XMLWriter writer=new XMLWriter(new OutputStreamWriter(new FileOutputStream(path), "utf-8"),format);
			writer.write(document);
			writer.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
