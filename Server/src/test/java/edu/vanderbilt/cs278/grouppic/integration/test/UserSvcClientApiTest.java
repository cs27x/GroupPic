package edu.vanderbilt.cs278.grouppic.integration.test;

import static org.junit.Assert.*;

import org.junit.Test;






import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonPrimitive;

import retrofit.RestAdapter;
import retrofit.RestAdapter.LogLevel;
import retrofit.converter.GsonConverter;
import edu.vanderbilt.cs278.grouppic.client.UserSvcApi;
import edu.vanderbilt.cs278.grouppic.repository.User;

import java.util.Date;

import org.junit.Test;

import junit.framework.TestCase;

public class UserSvcClientApiTest {

//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}
	


	private final static String TEST_URL = "http://localhost:8080";
	private static final Gson gson = new GsonBuilder()
		.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
		//.registerTypeHierarchyAdapter(byte[].class, typeAdapter)
		.create();

	private static final UserSvcApi userService = new RestAdapter.Builder()
	.setEndpoint(TEST_URL)
	.setLogLevel(LogLevel.FULL)
	.setConverter(new GsonConverter(gson))
	.build()
	.create(UserSvcApi.class);


		/**
		 * @author Ethan Dixius
		 * creates a new user and adds it to the repository
		 * 
		 * @throws Exception
		 */
	@Test
	public void TestUserCrud() throws Exception
	{
		User user1 = new User("dixiuseb", "fakefakefake");
		
		userService.createUser(user1);
		
		Collection<User> users = userService.getUserList();
		assertTrue(users.size() > 0);
			
		assertTrue(users.contains(user1));
			
		for(User u : users)
		{
			userService.deleterUser(u.getUsername());
		}
		
		assertEquals(0, users.size());	
	}
		
		
}

