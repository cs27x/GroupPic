package edu.vanderbilt.cs278.grouppic.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import com.google.common.base.Objects;
import com.sun.org.apache.xml.internal.security.utils.Base64;



/**
 * Created by Ethan Dixius on 11/15/14.
 *
 * Basic object for storing user information
 */
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String username;
	
	private String password;
	
	public User()
	{
		username = "";
		password = "";
	}
	
	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public long getId()
	{
		return id;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	
	@Override
	public boolean equals (Object obj)
	{
		if (obj instanceof User)
		{
			User other = (User) obj;
			
			boolean uEquals = Objects.equal(username, other.username);
			boolean pEquals = Objects.equal(password, other.password);
			return uEquals && pEquals;
		}
		else
		{
			return false;
		}
	}
	
	
}
