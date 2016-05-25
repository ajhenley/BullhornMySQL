

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Bhpost;
import model.Bhuser;
import customTools.DbUser;
import customTools.DbBullhorn;
import customTools.DbUser;

@WebServlet("/Newsfeed")
public class Newsfeed extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Newsfeed() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextURL = "error.jsp";
		//get user out of session
		HttpSession session = request.getSession();
		
		//get all posts out of db (not just the current user)
		List<Bhpost> posts = DbBullhorn.bhPost();
		//add posts to session
		session.setAttribute("posts", posts);
		//display posts in newsfeed.jsp
		/*
		String message = "<div class=\"container\">";
		message += "<table class=\"table table-bordered\"><thead><tr><th>Username</th><th>Post</th><th>Date</th></tr></thead><tbody>";
		for (Bhpost bpost : posts)
		{
			int userid = bpost.getBhuser().getBhuserid();
			Bhuser user = DbUser.getUser(userid);
			message += "<tr>";
			message += "<td>" + user.getUsername() + "</td>";
			message += "<td>" + bpost.getPosttext() + "</td>";
			message += "<td>" + bpost.getPostdate() + "</td>";
			message += "</tr>";
		}
		message += "</tbody></table>";
		message += "</div>";
		System.out.println(message);
		request.setAttribute("message", message);
		*/
		
		
		
		
		
		
		nextURL = "/newsfeed.jsp";
		//redirect to next page as indicated by the value of the nextURL variable
		getServletContext().getRequestDispatcher(nextURL).forward(request,response);
	}

}
