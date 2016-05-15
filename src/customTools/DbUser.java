package customTools;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.Bhuser;

public class DbUser {

	public static Bhuser getUser(long userID)
	{
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		Bhuser user = em.find(Bhuser.class, userID);
		return user;		
	}
	
	public static void insert(Bhuser bhUser) {
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(bhUser);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void update(Bhuser bhUser) {
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(bhUser);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(Bhuser bhUser) {
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(em.merge(bhUser));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}


	public static long isValidUser(Bhuser user)
	{
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		String qString = "Select b.userid from Bulluser b where b.username = :username and b.userpass = :userpass";
		Query q = em.createQuery(qString);
		q.setParameter("username", user.getUsername());
		q.setParameter("userpass", user.getUserpassword());
		
		try{
			long userId = (long) q.getSingleResult();
			System.out.println("userId =" + userId);
			if (userId > 0)
			{
				return userId;
			}
			else
			{
				return 0;
			}
			
		}catch (Exception e){
			
			return 0;
		}
		finally{
				em.close();		
		}	
			
	}
	
}
