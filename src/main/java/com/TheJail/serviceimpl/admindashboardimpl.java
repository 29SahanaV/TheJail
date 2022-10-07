package com.TheJail.serviceimpl;

import java.util.List;
import java.util.Scanner;

import com.TheJail.dao.admindao;
import com.TheJail.daoimpl.admindaoimpl;
import com.TheJail.daoimpl.userdaoimpl;
import com.TheJail.exception.GlobalException;
import com.TheJail.model.room;
import com.TheJail.model.user;
import com.TheJail.service.admindashboard;

import org.apache.log4j.Logger;



public class admindashboardimpl implements admindashboard{

	
	//creation of global objects
		static Logger log=Logger.getLogger(userdaoimpl.class);

		static Scanner sa=new Scanner(System.in);
		static admindashboardimpl service=new admindashboardimpl();
		static admindao dao=new admindaoimpl();
	@Override
	public void dashboard() throws GlobalException  {
		// TODO Auto-generated method stub
		
		log.info("\t\t-----------welcome to Admin Dashboard---------------\t\t");
		
		int op=0;
		while(op<10)
		{
			//reading choice of operation
		log.info("\tPress 1 for view rooms\t\tPress 2 for view users\n\tPress 3 for create room\t\tPress 4 for allot room\n\tPress 5 for addDue Amount \t\tPress 6 for payDue amount\n\tPress 7 for view user profile\t\tPress 8 for  delete user\n\tPress 9 for view users in a room");
		op=sa.nextInt();

		switch(op) {
			//calling methods as per choice
			case 1->service.viewRooms();
			case 2->service.viewUsers();
			case 3->service.createRoom();
			case 4->service.allotRoom();
			case 5->service.addDueAmount();
			case 6->service.payDueAmount();
			case 7->service.viewUserProfile();
			case 8->service.deleteUser();
			case 9->service.userInRoom();
			default->System.exit(0);
			}
		}
	}
	
	//view available rooms in db
	@Override
	public void viewRooms() {
		// TODO Auto-generated method stub
		List<room> roomList=dao.viewRooms();
		for(room r:roomList)
		{
			log.info(r);
		}
	}
	
	//vieww all users
	@Override
	public void viewUsers() {
		// TODO Auto-generated method stub
		List<user> userList=dao.viewUsers();
		for(user u1:userList)
			log.info(u1);
	}
	
	//creating new room
	@Override
	public void createRoom() throws GlobalException {
		// TODO Auto-generated method stub
		log.info("Enter room Id");
		int rId=sa.nextInt();
		log.info("Enter roomName");
		String rName=sa.next();
		log.info("Enter roomType");
		String rType=sa.next();

		room r1=new room();
		r1.setRoomId(rId);
		r1.setRoomName(rName);
		r1.setRoomType(rType);

		int st=dao.createRoom(r1);
		if(st==1) {
			log.info("Room added successfully");
		}
	}
	
	//alloting new room to user
	@Override
	public void allotRoom() {
		// TODO Auto-generated method stub
		log.info("Enter userId");
		int uid=sa.nextInt();
		log.info("Enter roomId");
		int rid=sa.nextInt();
		int st=dao.allotRoom(uid, rid);
		if(st==1) {
			log.info("room alloted!...");
		}
	}
	
	//adding due amt to the user
	@Override
	public void addDueAmount() {
		// TODO Auto-generated method stub
		log.info("Enter user id");
		int uid=sa.nextInt();
		log.info("Enter amount to add");
		int amount=sa.nextInt();
		int st=dao.addDueAmount(uid, amount);
		if(st==1) 
		{
			log.info("fee updated");
		}
	}
	
	//pay the due amount
	@Override
	public void payDueAmount() {
		// TODO Auto-generated method stub
		log.info("Enter user id");
		int uid=sa.nextInt();
		log.info("Enter amount to add");
		int amount=sa.nextInt();
		int st=dao.payDueAmount(uid, amount);
		if(st==1)
		{
			log.info("fee updated");
		}
	}
	
	//deleting the user
	@Override
	public void deleteUser() {
		// TODO Auto-generated method stub
		log.info("Enter user id");
		int uid=sa.nextInt();
		int st=dao.deleteUser(uid);
		if(st==1) 
		{
		log.info("deleted!....");
	    }
	}
	
	//view users in a room
	@Override
	public void userInRoom() {
		// TODO Auto-generated method stub
		log.info("Enter room Id");
		int rid=sa.nextInt();
		List<user> userList=dao.userInRoom(rid);
		for(user u1:userList)
			log.info(u1);
	}
	
	//viewing user profile 
	@Override
	public void viewUserProfile() {
		// TODO Auto-generated method stub
		log.info("Enter userid");
		int uid=sa.nextInt();
		user u1=dao.viewUserProfile(uid);
		log.info(u1);
	}

}
