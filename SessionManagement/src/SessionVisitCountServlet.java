import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class SessionVisitCountServlet extends BaseServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {			
		
		HttpSession session = request.getSession();

		int visitCount;
		String body;
		HashMap<String, String> value = (HashMap<String, String>)session.getAttribute(USER_INFO);
		
		//there is no existing session
		if(value == null) {
			value = new HashMap<String, String>();
			visitCount = 1;
			body = "<p>Welcome, you have not visited this page before!</p>";
		} else {
			visitCount = Integer.parseInt(value.get(VISIT_COUNT)) + 1;
			body = "<p>This is visit number " + visitCount + "!</p>";
		}
		
		//store the updated value in the map
		value.put(VISIT_COUNT, String.valueOf(visitCount));
		//put the updated map into the session
		session.setAttribute(USER_INFO, value);
		
		PrintWriter out = prepareResponse(response);		
		out.println(header("VisitCount"));		
		out.println(body);		
		out.println(footer());
		
		
	}
	
	
}
