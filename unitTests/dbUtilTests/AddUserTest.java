package dbUtilTests;

import org.junit.Test;
import static org.junit.Assert.*;
import model.Bhuser;

import customTools.DbUser;

public class AddUserTest {

	@Test
	public void AddUsertest() {
		String userName = "User1234";
		String userEmail = "user1234@domain.com";
		String userPassword = "password";
		String userMotto = "this is a test";

		Bhuser u = null;
		//check if user exists (by email)
		u = DbUser.getUserByEmail(userEmail);
		
		//add user if they don't exits
		if (u == null){
			u = new Bhuser();
			u.setUsername(userName);
			u.setUseremail(userEmail);
			u.setUserpassword(userPassword);
			u.setMotto(userMotto);
			DbUser.insert(u);
		}
		//check is user exists
		u = DbUser.getUserByEmail(userEmail);
		assertEquals(userEmail,u.getUseremail());
	}

}
