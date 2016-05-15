import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Bhuser;
import model.Bhpost;
import customTools.DbBullhorn;
import customTools.DbUtil;

@WebServlet("/PostServ")
public class PostServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PostServ() {
        super();
    }

    
    protected void doPost(HttpServletRequest request, 
    							HttpServletResponse response) 
    									throws ServletException, IOException {
		Date postdate = new Date();
		String posttext = request.getParameter("posttext");
		String nextURL = "/error.jsp";
		//need a reference to the session
		HttpSession session = request.getSession();
		
		//get user information from session so we can connect to the db
		User user = (User)session.getAttribute("user");
		
		
		//get  a populated bhuser object since we'll add that to the post
		EntityManager em = DbUtil.getEmFactory().createEntityManager();
		String query = "select u from Bhuser u where u.useremail=:email";
		TypedQuery<Bhuser> q = em.createQuery(query,Bhuser.class);
		//q.setParameter("email",user.getEmail());
		q.setParameter("email","bart@fox.net");
		
		Bhuser bhUser = null;
		try {
			bhUser = q.getSingleResult();
			System.out.println("The user id is: " + bhUser.getUserid());
			nextURL = "/newsfeed.jsp";
		} catch (NoResultException e){
			System.out.println(e);
		} catch (NonUniqueResultException e){
			System.out.println(e);
		} finally {
			em.close();
		}
				
		Bhpost bhPost = new Bhpost();
		bhPost.setPostid(1L);
		bhPost.setBhuser(bhUser);
		bhPost.setPostdate(postdate);
		bhPost.setPosttext(posttext);
		
		DbBullhorn.insert(bhPost);
		
		getServletContext().getRequestDispatcher(nextURL).forward(request, response);
		
		/*
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try{
			out.println(posttext + "<br />");
		}catch (Exception ex){
			//go to error page
			//getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
		}finally{
			out.close();
		}
		*/
	}

}
