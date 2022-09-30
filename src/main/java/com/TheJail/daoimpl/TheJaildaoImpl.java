package com.TheJail.daoimpl;

import com.TheJail.config.hibernateutility;
import com.TheJail.dao.TheJaildao;
import com.TheJail.exception.GlobalException;
import com.TheJail.model.user;

import org.hibernate.Session;



public class TheJaildaoImpl implements TheJaildao{

	@Override
	public int registration(user u1) throws GlobalException {
		//auto closable session
		try(Session ses=hibernateutility.getSession()){

			ses.beginTransaction();
			//getting username to check availabilty of username
			String username=u1.getUserName();
			user u2=null;
			//checking username whether existed or not
			u2=(user)ses.createQuery("from user where userName=:username").setParameter("username", username).uniqueResult();
			if(u2==null)
			{
				//saving the user, if username is unique
			ses.save(u1);
			ses.getTransaction().commit();
			return 1;
			}
			else {
				throw new GlobalException("Username already taken");
			}
			}

	}

	@Override
	public user login (String username,String password)throws GlobalException
	{
		
		//auto closable session
				try(Session ses=hibernateutility.getSession()){

					ses.beginTransaction();
					//retriving user details based on entered username
				user u2=(user)ses.createQuery("from user where userName=:username").setParameter("username", username).uniqueResult();	
					//if user is found
				if(u2!=null) {
					//lets check for the password
					if(u2.getUserPassword().equals(password)) {
						return u2;
					}
					else {
						throw new GlobalException("wrong username or password");
					}
				}
				else {
					throw new GlobalException("user not found");
				}


				}
	}

}
