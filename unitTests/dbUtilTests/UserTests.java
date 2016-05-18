package dbUtilTests;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import model.Bhuser;

import org.junit.Test;

import customTools.DbUser;
import customTools.DbUtil;

public class UserTests {

	@Test
	public void test() {
		String useremail = "larry12345@gmail.com";
		String userpassword = "password";
		Boolean result = false;
		
		Bhuser user = new Bhuser();
		user.setUseremail(useremail);
		user.setUserpassword(userpassword);
				
		assertTrue(DbUser.isValidUser(user));
				
	}

}
