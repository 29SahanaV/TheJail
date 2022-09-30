package com.TheJail.serviceimpl;

import java.util.Scanner;
import java.util.regex.Pattern;

import com.TheJail.App;
import com.TheJail.dao.TheJaildao;
import com.TheJail.daoimpl.TheJaildaoImpl;
import com.TheJail.exception.GlobalException;
import com.TheJail.model.user;
import com.TheJail.service.admindashboard;
import com.TheJail.service.loginregister;
import com.TheJail.service.userdashboard;

import org.apache.log4j.Logger;

public class loginregisterimpl implements loginregister{

	static Logger log=Logger.getLogger(App.class);
	static TheJaildao dao=new TheJaildaoImpl();
	static Scanner sa=new Scanner(System.in);
	
	//Registeration
	@Override
	public void registration() throws GlobalException{
		// TODO Auto-generated method stub
		
		log.info("welcome to registration");
		user u1=new user();
		log.info("Enter username");
		String name=sa.next();
		log.info("Create password");
		String pwd=sa.next();
		log.info("Enter Phone");
		String phone=sa.next();
		log.info("Enter Address");
		String adr=sa.next();

		//method calling 
		u1.setUserName(name);
		u1.setUserAddress(adr);
		u1.setUserPhone(phone);
		u1.setUserPassword(pwd);
		u1.setUserRole("student");
		u1.setUserFee(0);
		u1.setUserRoom(null);
		
		//validating user data
		if(Pattern.matches("[a-zA-Z]{4,}", name)&&Pattern.matches("[0-9]{10}", phone)&&Pattern.matches("[a-zA-Z0-9@#]{6,}", pwd))
		{
			//inserting user data
			int status=dao.registration(u1);	
			if(status==1) 
			{
			log.info("registration success");
			}
		}
		else {
			throw new GlobalException("Invalid  data");
		}
		
	}

	//Login
	@Override
	public void login()throws GlobalException {
		// TODO Auto-generated method stub
		
		log.info("welcome to login");
		log.info("Enter username");
		String uname=sa.next();
		log.info("Enter Password");
		String upwd=sa.next();
		//calling dao login method
		user u1=dao.login(uname, upwd);
		
		userdashboard ud1=new userdashboardimpl();   
		admindashboard ad1=new admindashboardimpl();
		
		if(u1!=null) {
			log.info("Hey "+u1.getUserName()+" login success");
			
			if(u1.getUserRole().equals("student"))       //After login -to check user Role
			{
				ud1.dashboard(u1.getUserId());   //using user id for ever dff file
			}
			else if(u1.getUserRole().equals("admin"))
			{
				ad1.dashboard();
			}
		}
	}

}
