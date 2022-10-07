package com.TheJail.daoimpl;

import java.util.List;

import com.TheJail.config.hibernateutility;
import com.TheJail.dao.admindao;
import com.TheJail.exception.GlobalException;
import com.TheJail.model.room;
import com.TheJail.model.user;

import org.hibernate.Session;

public class admindaoimpl implements admindao{

	//viewing list of room
	@Override
	public List<room> viewRooms() {
		
		try(Session ses=hibernateutility.getSession()){
			
			List<room> roomList=ses.createQuery("from room").getResultList();
			return roomList;	
		}
	}

	//viewing  list of users who is "STUDENT"
	@Override
	public List<user> viewUsers() {
		
		try(Session ses=hibernateutility.getSession()){
			ses.beginTransaction();
			List<user> userList=ses.createQuery("from user where userRole='student'").getResultList();
			ses.getTransaction().commit();
			return userList;	
		}
	}

	//creating rooms
	@Override
	public int createRoom(room r1) throws GlobalException {
		
		String roomname=r1.getRoomName();
		try(Session ses=hibernateutility.getSession()){
		ses.beginTransaction();
			room r2=(room)ses.createQuery(" from room where roomName=:roomname").setParameter("roomname", roomname).uniqueResult();

			if(r2!=null) {
				throw new GlobalException("Room name already existed");
			}
			else {
				ses.save(r1);
				ses.getTransaction().commit();
				return 1;
			}
		}
	}
	
	//alloting room for user
	@Override
	public int allotRoom(int uId, int rId) {
		
		try(Session ses=hibernateutility.getSession()){
			ses.beginTransaction();
			int status=ses.createQuery("update user set userRoom_roomId=:roomid where userId=:userid")
					.setParameter("roomid", rId).setParameter("userid", uId)
					.executeUpdate();
			ses.getTransaction().commit();
			return status;
		}
	}

	//adding amt to particular user id
	@Override
	public int addDueAmount(int uId, int amount) {
		
		try(Session ses=hibernateutility.getSession())
		{
			ses.beginTransaction();
			user u2=ses.get(user.class, uId);

			int dueAmount=u2.getUserFee();
			dueAmount+=amount;
			int status=ses.createQuery("update user set userFee=:amount where userId=:uid")
					.setParameter("amount", dueAmount).setParameter("uid", uId).executeUpdate();

			ses.getTransaction().commit();
			return status;
		}
	}

	//deducting amt which hs to be paid
	@Override
	public int payDueAmount(int uId, int amount) {
		
		try(Session ses=hibernateutility.getSession()){
			ses.beginTransaction();

			user u2=ses.get(user.class, uId);
			int dueAmount=u2.getUserFee();
			dueAmount-=amount;
			int status=ses.createQuery("update user set userFee=:amount where userId=:uid")
					.setParameter("amount", dueAmount).setParameter("uid", uId).executeUpdate();

			ses.getTransaction().commit();
			return status;

		}
	}

	//deleting  user by user id
	@Override
	public int deleteUser(int uId) {
		
		try(Session ses=hibernateutility.getSession()){

			ses.beginTransaction();
			int status=ses.createQuery("delete from user where userId=:uid").setParameter("uid",uId).executeUpdate();
			ses.getTransaction().commit();
			return status;
		}
	}

	//users in each room id
	@Override
	public List<user> userInRoom(int rId) {
		
		try(Session ses=hibernateutility.getSession()){

			List<user> userList=ses.createQuery("from user where userRoom_roomId=:rid").setParameter("rid",rId).getResultList();
			return userList;
		}
	}

	//viewing user profile
	@Override
	public user viewUserProfile(int uId) {
		
		try(Session ses=hibernateutility.getSession()){

			user u2=ses.get(user.class, uId);
			return u2;
		}
	}

}
