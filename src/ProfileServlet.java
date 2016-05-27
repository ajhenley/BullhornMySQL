

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DbUser;
import model.Bhuser;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ProfileServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request,response);
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String nextURL = "/error.jsp";
		
		//get the user out of the session
		Bhuser user = (Bhuser) session.getAttribute("user");
		boolean isMyProfile = false;
		//System.out.println("profileServlet: " + user.getUsername());
		if (user == null){
			//they aren't logged in. Maybe came to the site from 
			//a bookmark of the profile page. 
			nextURL = "/login.jsp";
		}else{
			//if you get here then the user is logged in
			//user the data in the user object to populate the profile page
			int imgSize = 120;
			
		      // SimpleDateFormat can be used to control the date/time display format:
		      //   E (day of week): 3E or fewer (in text xxx), >3E (in full text)
		      //   M (month): M (in number), MM (in number with leading zero)
		      //              3M: (in text xxx), >3M: (in full text full)
		      //   h (hour): h, hh (with leading zero)
		      //   m (minute)
		      //   s (second)
		      //   a (AM/PM)
		      //   H (hour in 0 to 23)
		      //   z (time zone)
			SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
            String d = sdf.format(user.getJoindate());
			
			request.setAttribute("userimage",DbUser.getGravatarURL(user.getUseremail(), imgSize));
			request.setAttribute("username", user.getUsername());
			request.setAttribute("useremail", user.getUseremail());
			request.setAttribute("usermotto", user.getMotto());
			request.setAttribute("userjoindate", d);
			
			//if the user is going to their own profile then allow edits, 
			//otherwise, make profile read-only
			
			session.setAttribute("editProfile", isMyProfile);
			nextURL = "/profile.jsp";
		}
			
		//redirect to next page as indicated by the value of the nextURL variable
		getServletContext().getRequestDispatcher(nextURL).forward(request,response);
	}

}
