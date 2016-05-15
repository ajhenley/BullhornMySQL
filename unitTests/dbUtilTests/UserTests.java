package dbUtilTests;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import model.Bhuser;

import org.junit.Test;

import customTools.DbUtil;

public class UserTests {

	@Test
	public void test() {
		String nextURL="error.jsp";
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		String query = "select u from Bhuser u where u.useremail=:email";
		TypedQuery<Bhuser> q = em.createQuery(query,Bhuser.class);
		//q.setParameter("email",user.getEmail());
		q.setParameter("email","bart@fox.net");
		
		Bhuser bhUser = null;
		try {
			bhUser = q.getSingleResult();
			System.out.println("The user id is: " + bhUser.getUserid());
			nextURL = "newsfeed.jsp";
		} catch (NoResultException e){
			System.out.println(e);
		} catch (NonUniqueResultException e){
			System.out.println(e);
		} finally {
			em.close();
		}
		
		assertTrue(nextURL.equals("newsfeed.jsp"));
				
	}

}
