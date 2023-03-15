package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.config.Config;

public class Orm {

    private static Orm object;
   
    
    private Orm() {}
    
    public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(Config.DRIVER);
		var url = "jdbc:mysql://localhost/" + Config.DB;
		return DriverManager.getConnection(url, Config.LOGIN, Config.PASS);
	}
  
    
    public static Orm getInstance() throws ClassNotFoundException, SQLException {
        if (object == null) {
            object = new Orm();
        }
        return object;
    }
    
    public static ResultSet select(String sql) throws SQLException, ClassNotFoundException {
    	PreparedStatement statement = getInstance().getConnection().prepareStatement(sql);
        return statement.executeQuery();
    }

    
    public static int execute(String sql) throws SQLException, ClassNotFoundException {
    	PreparedStatement statement = getInstance().getConnection().prepareStatement(sql);
        return statement.executeUpdate();
    }

}
