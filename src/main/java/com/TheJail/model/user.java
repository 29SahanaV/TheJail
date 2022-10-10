package com.TheJail.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString
public class user {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	@NotNull                       //to declare user name isnt null
	@Size(min=4,max=15,message="username to be more than 4 chars")    //min n max value for name
	private String userName;
	@NotNull                       //to declare user password isnt null
	@Pattern(regexp="[a-zA-Z0-9@#]{5,20}",message="Password should be more 5 and less than 20")    //min n max value for pwd
	private String userPassword;
	@NotNull
	@Pattern(regexp="[0-9]{10}",message="phone number should be 10 digits only")
	private String userPhone;
	@NotNull
	@Size(min=3,max=15,message="address should be more than 3 chars")
	private String userAddress;
	private int userFee;
	private String userRole;
	@ManyToOne
	private room userRoom;
	
}
