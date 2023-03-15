package controllers;

import java.io.IOException;

import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.BaseModel;


@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");
	    request.getRequestDispatcher("WEB-INF/views/registration.jsp").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");
	    
	    String login = request.getParameter("login");
	    String pass = request.getParameter("pass");
	    String phonenum= request.getParameter("tel");
	    String name = request.getParameter("name");
	    String lastname = request.getParameter("lastname");
	    	  
	    if (login.isEmpty() || pass.isEmpty() || phonenum.isEmpty() ||  name.isEmpty() || lastname.isEmpty()) {
	    	response.getWriter().print("Заполните все поля!");
			} else
			try {
				if (BaseModel.checkUser(login)) {
						response.getWriter().print("Такой логин уже существует!");
					} else
					try {
						if(BaseModel.addNewUser(login, pass, phonenum, name, lastname)){
							response.getWriter().print("Вы успешно зарегистрированы! Войдите под своим логином и паролем!");
						}else {
							response.getWriter().print("Ошибка регистрации!");
						}
					} catch (NumberFormatException | ClassNotFoundException | SQLException | IOException e) {
						e.printStackTrace();
					}
			} catch (NumberFormatException | ClassNotFoundException | SQLException | IOException e) {
				
				e.printStackTrace();
			}
	}

}
