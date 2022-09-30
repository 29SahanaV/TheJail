package com.TheJail.config;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.TheJail.model.room;
import com.TheJail.model.user;

public class hibernateutility {

	
		private static SessionFactory sesFact;
		
		public static SessionFactory getSessionFactory() {
			
			if(sesFact==null)
			{
				try {
					
					Configuration conf=new Configuration();
					Properties pro=new Properties();
					pro.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
					pro.put(Environment.URL, "jdbc:mysql://localhost:3306/TheJail");
					pro.put(Environment.USER, "root");
					pro.put(Environment.PASS, "90089");
					pro.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
					pro.put(Environment.HBM2DDL_AUTO, "update");
					
					conf.setProperties(pro);
					conf.addAnnotatedClass(user.class);
					conf.addAnnotatedClass(room.class);
					
					sesFact=conf.buildSessionFactory();
					
					
					} 
				catch(Exception e) {
						
					}
			}
				return sesFact;
				
			}
		public static Session getSession() {
			return getSessionFactory().openSession();
		}
}

