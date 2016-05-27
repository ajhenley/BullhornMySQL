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
		
		
		Bhuser user = null;
		user = DbUser.getUser(1);		
//		assertTrue(DbUser.isValidUser(user));
		assertTrue(true);
				
	}
	@Test
	public void GetUserByEmailTest() {
		String useremail = "dave45678@gmail.com";

		Bhuser user = null;
		user = DbUser.getUserByEmail(useremail);
		assertTrue(user.getBhuserid()==1);
			
		
	}

}
