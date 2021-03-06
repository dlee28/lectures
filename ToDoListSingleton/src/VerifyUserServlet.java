import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * Servlet invoked at login.
 * Creates cookie and redirects to main ListServlet.
 */
public class VerifyUserServlet extends BaseServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//VerifyUser does not accept GET requests. Just redirect to login with error status.
		response.sendRedirect(response.encodeRedirectURL("/login?" + STATUS + "=" + ERROR));
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		String name = request.getParameter(NAME);
		
		if(name == null || name.trim().equals("")) {
			response.sendRedirect(response.encodeRedirectURL("/login?" + STATUS + "=" + ERROR));
			return;
		}
				
		HttpSession session = request.getSession();
		session.setAttribute(NAME, name);
		
		//OPTION 1: Store volatile data in a Singleton
		//Note: this approach will not work for InvertedIndex if you instantiate multiple 
		//InvertedIndex objects in your program (for example to do a batch add of words from 
		//one file). In that case, use option 2.
		
		//map id to name and userinfo
		DataSingleton data = DataSingleton.getInstance();
		//we assume no username conflicts and provide no ability to register for our service!
		data.addUser(name);  
							
		//redirect to list
		response.sendRedirect(response.encodeRedirectURL("/list"));
	}
	
}
