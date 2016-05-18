package dbUtilTests;

import static org.junit.Assert.*;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import model.Bhpost;
import model.Bhuser;

import org.junit.Test;

import customTools.DbBullhorn;
import customTools.DbUtil;

public class insertPostTest {

	@Test
	public void test() {
		String nextURL = "/error.jsp";
		Date postdate = new Date();
		//get  a populated bhuser object since we'll add that to the post
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		String query = "select u from Bhuser u where u.useremail=:email";
		TypedQuery<Bhuser> q = em.createQuery(query,Bhuser.class);
		//q.setParameter("email",user.getEmail());
		q.setParameter("email","larry12345@gmail.com");
		
		Bhuser bhUser = null;
		try {
			bhUser = q.getSingleResult();
			System.out.println("The user id is: " + bhUser.getBhuserid());
			nextURL = "/newsfeed.jsp";
		} catch (NoResultException e){
			System.out.println(e);
		} catch (NonUniqueResultException e){
			System.out.println(e);
		} finally {
			em.close();
		}
		System.out.println("inserting into post table");		
		Bhpost bhPost = new Bhpost();
		//bhPost.setPostid(1L);
		bhPost.setBhuser(bhUser);
		bhPost.setPostdate(postdate);
		bhPost.setPosttext("This is a unit test post");
		
		System.out.println("calling DbBullhorn.insert");
		DbBullhorn.insert(bhPost);
	}

}
