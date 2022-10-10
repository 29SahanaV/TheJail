package com.TheJail;

import com.TheJail.dao.TheJaildao;
import com.TheJail.dao.admindao;
import com.TheJail.dao.userdao;
import com.TheJail.daoimpl.TheJaildaoImpl;
import com.TheJail.daoimpl.admindaoimpl;
import com.TheJail.daoimpl.userdaoimpl;
import com.TheJail.exception.GlobalException;
import com.TheJail.model.room;
import com.TheJail.model.user;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	
	//testing for registration from jail dao
	@Test
	@Disabled
	public void testregistration() throws GlobalException {
		TheJaildao dao=new TheJaildaoImpl();  //creating object from jail dao n impl
		user u1=new user();
		u1.setUserName("shagun");
		u1.setUserPhone("9878675778");
		u1.setUserPassword("Shan123");
		u1.setUserAddress("coimbatore");
		u1.setUserRole("student");
		
		user u2=new user();
		u2.setUserName("sahana");
		u2.setUserPhone("5454546464");
		u2.setUserPassword("Sahana#");
		u2.setUserAddress("nelmangala");
		u2.setUserRole("student");
	
		assertAll(
				()->assertEquals(1,dao.registration(u1)),
				()->assertThrows(GlobalException.class,()->dao.registration(u2))
				);
	}
	
	
	// testing password from user dao
	@Test
	//@Disabled
	public void testpassword() {

		userdao dao=new userdaoimpl(); //creating object from user dao n impl

		assertEquals(-1,dao.changePassword(2, "S@#ilkumar", "Suni144"));

	}
	
	//testing for room details from admin dao
	@Test
	@Disabled
	public void testroom() throws GlobalException {

		admindao dao=new admindaoimpl();
		room r1=new room();
		r1.setRoomId(105);
		r1.setRoomName("lalitha");
		r1.setRoomType("non-AC");

		room r2=new room();
		r2.setRoomId(101);
		r2.setRoomName("sheshadri");
		r2.setRoomType("AC");

		assertAll(
		()->assertEquals(1,dao.createRoom(r1)),
		()->assertThrows(GlobalException.class,()->dao.createRoom(r2))
		);


	}

}
