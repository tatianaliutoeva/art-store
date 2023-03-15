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

@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; character=UTF-8");	
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("user_id");
		if (userId!=null) {
			String name = "";
			try {
				name = BaseModel.getUserLog(userId);
			} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("name", name);
			response.getWriter().print("Рады снова видеть Вас, "+name);
			
			}else {
				request.setAttribute("user_id", userId);
				response.getWriter().print("Авторизуйтесь или зарегистрируйтесь, чтобы делать покупки!");
			}
			request.getRequestDispatcher("WEB-INF/views/main.jsp").forward(request, response);
			
	}
}
