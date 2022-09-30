package com.TheJail.dao;

import com.TheJail.exception.GlobalException;
import com.TheJail.model.user;

public interface TheJaildao {

	public int registration(user u1)throws GlobalException;
	public user login(String username,String password)throws GlobalException;


}