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

@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");
	    try {
	    	HttpSession session = request.getSession();
	        String user_id = (String) session.getAttribute("user_id");
	        var goods = BaseModel.getCart(Integer.parseInt(user_id));
	        request.setAttribute("goods",goods);
	        request.setAttribute("isOrderAdded",false);
	        request.setAttribute("user_id",user_id);
	        request.getRequestDispatcher("WEB-INF/views/cart.jsp").forward(request, response);
	    } catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");
	    var user_id =request.getParameter("user_id");
	    var str = Integer.parseInt(user_id);
	    try {
			var isOrderAdded = BaseModel.addNewOrder(str);
			if(isOrderAdded) {
				BaseModel.cleanShoppingCart(str);
				request.setAttribute("isOrderAdded",isOrderAdded);
				response.getWriter().print("Заказ оформлен!");
			}else{
				response.getWriter().print("Корзина пуста!");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    
	}
}
