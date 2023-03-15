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

@WebServlet("/Catalog")
public class Catalog extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");
	    
	    try {
	    	var goodId = request.getParameter("good_id");
	    	if(goodId != null) {
	    		var good = BaseModel.getGood(Integer.parseInt(goodId));
		    	request.setAttribute("good",good);//1ый параметр - это название переменной, которую будем использовать в представлении
				//Передаем данные в представление и получаем сгенерированную верстку из него, которую выведем на страницу
				request.getRequestDispatcher("WEB-INF/views/card.jsp").forward(request, response);
	    	}else {
				var goods = BaseModel.getGoods();
				var genre = request.getParameter("genre");
				if(genre != null) {
					var goodsGenre = BaseModel.getGoods(genre);
					request.setAttribute("goods",goodsGenre);
				}else {
					//Подготовим данные для отправки в шаблон
					request.setAttribute("goods",goods);//1ый параметр - это название переменной, которую будем использовать в представлении
					//Передаем данные в представление и получаем сгенерированную верстку из него, которую выведем на страницу
				}
				HttpSession session = request.getSession();
		        // получаем объект name
		        String user_id = (String) session.getAttribute("user_id");
		        if(user_id!=null) {
		        	request.setAttribute("user_id",user_id);
		        }
				request.getRequestDispatcher("WEB-INF/views/catalog.jsp").forward(request, response);
	    	}
	    } catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	    request.setCharacterEncoding("UTF-8");
	    var goodId = request.getParameter("id");
	    HttpSession session = request.getSession();
	    String user_id = (String)session.getAttribute("user_id");
	    
    	if(goodId != null) {
    		try {
				if(BaseModel.addGood(Integer.parseInt(goodId),Integer.parseInt(user_id))){
					response.getWriter().print("Товар добавлен в корзину");
				}else {
					response.getWriter().print("Ошибка при добавлении товара в корзину");
				}
			} catch (NumberFormatException | ClassNotFoundException | SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
	}

}
