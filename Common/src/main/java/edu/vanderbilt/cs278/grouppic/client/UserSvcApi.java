package edu.vanderbilt.cs278.grouppic.client;

import java.util.Collection;

import edu.vanderbilt.cs278.grouppic.repository.User;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.DELETE;

/**
 * Created by Ethan Dixius on 11/16/14.
 *
 * Interface for use with the Retrofit library.
 * Provides an API for UserSvc
 */
public interface UserSvcApi {

	public static final String USER_PATH = "/user";
	
	@GET(USER_PATH)
	public Collection<User> getUserList();

	@GET(USER_PATH + "/{username}")
	public User getUserWithUsername(@Path("username") String username);
	
	@POST(USER_PATH)
	public void createUser(@Body User u);
	
	@DELETE(USER_PATH + "/{username}")
	public void deleterUser(@Path("username") String username);
	
}
