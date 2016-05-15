

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String remember = request.getParameter("remember");
		String nextURL = "/error.jsp";
		
		//get an instance of the session so we can set attributes to it
		//the JSP and NavBar will read from the session 
		//The session is one of the primary ways we maintain state
		//in an otherwise stateless web application
		HttpSession session = request.getSession();
		
		//create an instance of the user and put it in the session
		//only add the user to the session if the user if valid
		//because the presence of the user is used to determine who 
		//owns the site and will be used to connect to the database
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		if (user.isValidUser()){
			session.setAttribute("user", user);
			nextURL = "/home.jsp";
		}
		
		//redirect to next page
		getServletContext().getRequestDispatcher(nextURL).forward(request,response);
	}

}
