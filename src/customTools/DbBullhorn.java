package customTools;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.Bhpost;

public class DbBullhorn {

	public static void insert(Bhpost bhPost) {
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		System.out.println("DbBullhorn: begin transaction");
		try {
			trans.begin();
			em.persist(bhPost);
			trans.commit();
		} catch (Exception e) {
			System.out.println("DbBullhorn: something bad has happened....");
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void update(Bhpost bhPost) {
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(bhPost);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static void delete(Bhpost bhPost) {
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(em.merge(bhPost));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	public static List<Bhpost> bhPost (){
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		String qString = "select b from Bhpost b";
		
		List<Bhpost> posts = null;
		try{
			Query query = em.createQuery(qString);
			posts = query.getResultList();

		}catch (Exception e){
			e.printStackTrace();
		}
		finally{
				em.close();
			}
		return posts;
	}
	
	public static List<Bhpost> postsofUser(int userid)
	{
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		List<Bhpost> userposts = null;
		String qString = "select b from Bhpost b where b.userid = :userid";
		
		try{
			Query query = em.createQuery(qString);
			query.setParameter("userid", userid);
			userposts = query.getResultList();
		}catch (Exception e){
			e.printStackTrace();
		}
		finally{
				em.close();
			}
		return userposts;
		
	}
	
	public static List<Bhpost> searchPosts (String search)
	{
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		List<Bhpost> searchposts = null;
		String qString = "select b from Bhpost b where b.Bhpost like :search";
		
		try{
			Query query = em.createQuery(qString);
			query.setParameter("search", "%" + search + "%");
			searchposts = query.getResultList();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			em.close();
		}return searchposts;
	}
	
}