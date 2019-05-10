package pr.tp.web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author Charaf et Bastien 
 *
 */
/**
 * Servlet implementation class Chat
 */

@WebServlet("/chat")
public class Chat extends HttpServlet {
	private static final long serialVersionUID = 197811968639586823L;
	private StringBuffer chatContent;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public void init(ServletConfig config) throws ServletException 
	
	{     chatContent = new StringBuffer();

   		  chatContent.append("Bienvenue sur le chat").append(",");

		  chatContent.append("Soyez polis").append("\n");
              
	}
	public StringBuffer getChatContent() {
		return chatContent;
	}

	public void setChatContent(StringBuffer chatContent) {
		this.chatContent = chatContent;
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Gestion des session 
		HttpSession session = request.getSession(true);
		String username = request.getParameter("username");
		String userS = (String) session.getAttribute("username"); 

		
		if (username != null) { 
			
			//créer une nouvelle session pour username
			session.setAttribute("username", username);
			String action = request.getParameter("action");
			if (action != null && action.equals("submit")) { 
				
			// envoyer au buffer le texte introduit
               chatContent.append(request.getParameter("ligne")).append("\n");
               
            // envoyer du contenu du buffer
				request.setAttribute("contenu", chatContent.toString());
				response.sendRedirect("chat");
			} else if (action != null && action.equals("refresh")) {
				response.sendRedirect("chat");
				
			// fermer la session de username
			} else if (action != null && action.equals("logout")) { 
				session.setAttribute("username", null);
				response.sendRedirect("chat");
			}

			RequestDispatcher rd = request.getRequestDispatcher("chat.jsp");
			request.setAttribute("contenu", chatContent.toString());
			rd.include(request, response);
		
		// Connecter un nouveau user, redirigeant à login
		} else if (userS == null) { 
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.include(request, response);
		} else { 
			// maintenir la session

			session.setAttribute("username", userS);

			String action = request.getParameter("action");

			String login = (String) session.getAttribute("username");
			
			if (login.equals("")) {
				login = "Unknown user";
			}
			Date date = new Date();

			login = (date) + " " + login + "";
			
			if (action != null && action.equals("submit")) {
				chatContent.append(login + " >" + request.getParameter("ligne")).append("\n");
				request.setAttribute("contenu", chatContent.toString());
				response.sendRedirect("chat");
				
			} else if (action != null && action.equals("refresh")) {
				response.sendRedirect("chat");
				
			} else if (action != null && action.equals("logout")) {
				session.setAttribute("username", null);
				response.sendRedirect("chat");
			}

			RequestDispatcher rd = request.getRequestDispatcher("chat.jsp");
			request.setAttribute("contenu", chatContent.toString());
			rd.include(request, response);
		}

	}
		/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		doGet(request, response);
	}
	
		
}
