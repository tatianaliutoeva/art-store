package models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseModel {
	static List<Good>goods = new ArrayList<Good>();
	static List<GoodCart>cart = new ArrayList<GoodCart>();
	
	public static List<Good>getGoods() throws ClassNotFoundException, SQLException{
		var sql = "select * from goods";
		var data = Orm.select(sql);
		goods.clear();
		while(data.next()) {
			var id = data.getInt("good_id");
			var title = data.getString("title");
			var genre = data.getString("genre");
			var price = data.getInt("price");
			var info = data.getString("info");
			var img = data.getString("img");
			goods.add(new Good(id,title,genre,info,img,price));
		}
		return goods;	
	}
	
	public static List<Good>getGoods(String type) throws ClassNotFoundException, SQLException{
		var sql = "select * from goods where genre= '" + type + "'";
		var data = Orm.select(sql);
		goods.clear();
		while(data.next()) {
			var id = data.getInt("good_id");
			var title = data.getString("title");
			var genre = data.getString("genre");
			var price = data.getInt("price");
			var info = data.getString("info");
			var img = data.getString("img");
			goods.add(new Good(id,title,genre,info,img,price));
		}
		return goods;	
	}
	
	public static Good getGood(int goodId) throws ClassNotFoundException, SQLException {
		return goods.stream().filter(item -> item.getId() == goodId).findFirst().get();
	}
	
	public static boolean addGood(int id,int user_id) throws ClassNotFoundException, SQLException {
		var sql = "select id from cart where good_id = '" + id+"'";
		var data = Orm.select(sql);
		if(data.next()) {//если товар найден
			var sqlUpd = "update cart set count = count + 1 where good_id = '"+id+"'";
			if(Orm.execute(sqlUpd) > 0) {
				return true;
			}
		}else {
			var sqlInsert = "insert into cart(user_id,good_id, count) values('"+user_id+"','"+id+"', 1)";
			if(Orm.execute(sqlInsert) > 0) {
				return true;
			}
		}
		return false;
	}
	
	public static List<GoodCart> getCart() throws ClassNotFoundException, SQLException {
		var sql = "select * from goods inner join cart ON goods.good_id=cart.good_id";
		var data = Orm.select(sql);
		cart.clear();
		while(data.next()) {
			var id = data.getInt("good_id");
			var title = data.getString("title");
			var price = data.getInt("price");
			var userId = data.getInt("user_id");
			var count = data.getInt("count");
			
			cart.add(new GoodCart(id,userId,count,title,price));
		}
		return cart;
	}
	
	public static List<GoodCart> getCart(int user_id) throws ClassNotFoundException, SQLException {
		var sql = "SELECT title,price,user_id,count,goods.good_id as good_id from goods inner join cart ON goods.good_id=cart.good_id where user_id = '"+user_id+"'";
		var data = Orm.select(sql);
		cart.clear();
		while(data.next()) {
			var id = data.getInt("good_id");
			var title = data.getString("title");
			var price = data.getInt("price");
			var userId = data.getInt("user_id");
			var count = data.getInt("count");
			cart.add(new GoodCart(id,userId,count,title,price));
		}
		return cart;
	}
	
	public static boolean isUserExist(String login) throws SQLException, ClassNotFoundException {
		var sql = "select * from users where ulogin='"+login+"'";
		var data = Orm.select(sql);
		if(data.next()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean checkUser (String login) throws ClassNotFoundException, SQLException{
		var sql = "SELECT * from users where ulogin like '"+login+"'";
		var data = Orm.select(sql);
		if(data.next()) {
		  return true;
		}
		return false;	
    }
	
	public static String getUserIdByLogPass(String login, String password) throws ClassNotFoundException, SQLException {
		var sql = "select * from users where ulogin='" +login+"' and upass='"+password+"'";
		var data = Orm.select(sql);
		String id = "";
		if(data.next()) {
			 id = ""+data.getInt("uid");
			 return id;
		}
		return null;
	}
	
	public static boolean addNewUser(String login,String password,String phone, String username,String lastname) throws ClassNotFoundException, SQLException {
		try {
			var sqlInsert = "insert into users (ulogin, upass, uphone, uname,ulastname) values ('"+ login +"','"+password+"', '" +phone+"', '" +username+ "', '"+lastname+"')";
			if(Orm.execute(sqlInsert)>0) {
				return true;
			} 
		} catch(Exception e) {
			e.printStackTrace();
			
		}
		return false;
		
	}
	
	public static String getUserLog(String user_id) throws SQLException, ClassNotFoundException {
		var select = "select * from users where uid = '"+user_id+"'";
		var data = Orm.select(select);
		if (data.next()) {
			return data.getString("ulogin");
		}
		return "null";
	}
	
	public static boolean addNewOrder(int user_id) throws ClassNotFoundException, SQLException {
		int orderSum = getOrderSum(user_id);
		String name = getName(user_id);
		String phone = getPhone(user_id);
		String insert = "insert into orders(name, phone, order_sum, state) values('" + name + "', '" + phone + "', '" + orderSum + "', 'оформлен')";
		if (Orm.execute(insert) > 0) {
			return true;
		}
		return false;
	}
	
	public static String getName(int user_id) throws ClassNotFoundException, SQLException {
			String sql = "SELECT uname from users where uid = '"+ user_id+"'";
			var data = Orm.select(sql);
			if(data.next()) {
				var name = data.getString("uname");
				return name;
			}
			return "Имя не найдено!";
	   }
	
	public static String getPhone(int user_id) throws ClassNotFoundException, SQLException {
		var sql = "SELECT uphone from users where uid = '"+ user_id+"'";
		var data = Orm.select(sql);
		if(data.next()) {
			var phone = data.getString("uphone");
			return phone;
		}
		return "Телефон не найден";
   }

	
	public static boolean cleanShoppingCart(int userId) throws ClassNotFoundException, SQLException {
		var delete = "delete from cart where user_id = '" + userId + "'";
		if (Orm.execute(delete) > 0) {
			return true;
		}
		return false;
	}	

	
	public static int getOrderId(int userId) throws SQLException, ClassNotFoundException {
		var select = "select max(order_id) as order_id from orders where user_id = '" + userId + "' limit 1";
		var data = Orm.select(select);
		while (data.next()) {
			if (data.getString("order_id") != null) {
				return data.getInt("order_id");
			}
		}
		return 0;
	}
	
	public static int getOrderSum(int user_id) throws ClassNotFoundException, SQLException {
		int orderSum = 0;
		String select = "select sum(cart.count * goods.price) as order_sum from cart join goods on goods.good_id = cart.good_id where cart.user_id = '" + user_id + "'";
		var data = Orm.select(select);
		while (data.next()) {
			if (data.getInt("order_sum") != 0) {
				orderSum = data.getInt("order_sum");
			}
		}
		return orderSum;
	}
}
