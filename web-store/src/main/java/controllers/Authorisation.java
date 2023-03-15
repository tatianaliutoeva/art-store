package controllers;

import java.io.IOException;

import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.BaseModel;


@WebServlet("/Authorisation")
public class Authorisation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");
	    request.getRequestDispatcher("WEB-INF/views/authorisation.jsp").forward(request,response);
	    //HttpSession session = request.getSession();
		//String user_id = (String) session.getAttribute("user_id");
		//if (user_id!=null) {
			//response.sendRedirect("WEB-INF/views/main.jsp");
		//}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");
	    
		var login = request.getParameter("login");
		var password = request.getParameter("password");
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		if ((login!=null) && (password!=null)){
			try {
				if (BaseModel.isUserExist(login)) {
					
					var id = BaseModel.getUserIdByLogPass(login, password);
					String ulogin=BaseModel.getUserLog(id);
					if (user_id==null) {
						String str =id;
		                session.setAttribute("user_id",str);
		                response.getWriter().print("Вы авторизовались, "+ulogin+"Ваш id = "+ str);
					} else {
						response.getWriter().print("Вы уже авторизованы, "+ulogin);
					}
				    session.setAttribute("user_id", id);
				}else {
					request.setAttribute("user_id",user_id);
					response.getWriter().print("Пользователь не зарегистрирован!");
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}
}
