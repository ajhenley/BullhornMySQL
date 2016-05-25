package customTools;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.Bhuser;

public class DbUser {

	public static Bhuser getUser(int userID)
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


	public static boolean isValidUser(Bhuser user)
	{
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		String qString = "Select count(b.bhuserid) from Bhuser b where b.useremail = :useremail and b.userpassword = :userpass";
		Query q = em.createQuery(qString);
		boolean result = false;
		q.setParameter("useremail", user.getUseremail());
		q.setParameter("userpass", user.getUserpassword());
		
		try{
			long userId = (long) q.getSingleResult();
			if (userId > 0)
			{
				result = true;
			}
		}catch (Exception e){
			
			result = false;
		}
		finally{
				em.close();		
		}	
		return result;
			
	}
	
}
