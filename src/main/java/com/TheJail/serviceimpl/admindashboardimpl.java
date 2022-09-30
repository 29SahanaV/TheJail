package com.TheJail.serviceimpl;

import com.TheJail.service.admindashboard;

import org.apache.log4j.Logger;

public class admindashboardimpl implements admindashboard{

	static Logger log=Logger.getLogger(admindashboardimpl.class);
	@Override
	public void dashboard() {
		// TODO Auto-generated method stub
		
		log.info("\t\t-----------welcome to Admin Dashboard---------------\t\t");
	}

}
