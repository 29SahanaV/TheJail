package com.TheJail;

import java.util.Scanner;

import com.TheJail.exception.GlobalException;
import com.TheJail.service.loginregister;
import com.TheJail.serviceimpl.loginregisterimpl;

import org.apache.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
    static Logger log=Logger.getLogger(App.class);
	public static void main( String[] args ) throws GlobalException
    {
    	Scanner sa=new Scanner(System.in);
    	log.info("\t\t--------------The  Jail----------------------");
    	log.info("press 1 for Registration \n press 2 for Login");
    	int op=sa.nextInt();
    	loginregister logreg=new loginregisterimpl();  //import loginregister from service and loginregisterimpl from serviceimpl
    	switch(op) {
    	case 1->logreg.registration();
    	case 2->logreg.login();
    	}
    }
}
