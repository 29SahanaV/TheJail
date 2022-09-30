package com.TheJail.serviceimpl;



import java.util.Scanner;

import com.TheJail.dao.userdao;
import com.TheJail.daoimpl.userdaoimpl;
import com.TheJail.exception.GlobalException;
import com.TheJail.model.room;
import com.TheJail.model.user;
import com.TheJail.service.userdashboard;

import org.apache.log4j.Logger;
import org.apache.log4j.helpers.Loader;

public class userdashboardimpl implements userdashboard{

	static Logger log=Logger.getLogger(userdashboardimpl.class);

	static int userId=0;    //initial user id is 0
	static Scanner bs=new Scanner(System.in);
	static userdashboard udl=new userdashboardimpl();
	static userdao dao=new userdaoimpl();
	@Override
	public void dashboard(int uId) throws GlobalException {
		// TODO Auto-generated method stub
		
		log.info("\t\t-----------------------welcome to user dashboard--------------------");

		userId=uId;        //while calling dashboard assening value for user id
	int op=0;
	//selection of operation 
	while(op<6)               //to repeat operation after one operation is done
	{
		log.info("\n\tPress 1 for view profile\t\tPress 2 for View Due Amount\n\tPress 3 for view Room\t\tPress 4 for update Phone number\n\tPress 5 for change Password");
		op=bs.nextInt();		

		switch(op) {

		case 1->udl.viewProfile();

		case 2->udl.viewDueAmount();

		case 3->udl.viewRoom();

		case 4->udl.updatePhone();

		case 5->udl.changePassword();

		}
	}
	}

	
	//view profile
	@Override
	public void viewProfile() {
		// TODO Auto-generated method stub
		
		//calling dao viewprofile
				user u1=dao.viewProfile(userId);
				log.info(u1);
	}

	//view fee details
	@Override
	public void viewDueAmount() {
		// TODO Auto-generated method stub
		
		int amount=dao.viewDueAmount(userId);
		log.info("Hello User your due amount is "+amount);
	}

	//view room 
	@Override
	public void viewRoom() {
		// TODO Auto-generated method stub
		
		user u1=dao.viewProfile(userId);
		room r1=u1.getUserRoom();
		log.info("your room number is :"+r1.getRoomId()+" name is :"+r1.getRoomName()+" and it is"+r1.getRoomType()+" room");
	}

	//Update phone number
	@Override
	public void updatePhone() throws GlobalException {
		// TODO Auto-generated method stub
		
		log.info("Enter new Phone number");
		String phone=bs.next();
		int status=dao.updatePhone(userId, phone);
		if(status==1) {
			log.info("phone number updated!...");
		}
		else {
			throw new GlobalException("something went wrong");
		}
	}

	//to change password, have to enter current password correctly
	@Override
	public void changePassword() throws GlobalException {
		// TODO Auto-generated method stub
		
		log.info("Enter current password");
		String oldpwd=bs.next();
		log.info("Enter New password");
		String newpwd=bs.next();
		int status=dao.changePassword(userId, oldpwd, newpwd);
		if(status==1) {
			log.info("password updated!...");
		}
		else {
			throw new GlobalException("something went wrong");
		}
	}

}
