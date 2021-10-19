package dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import controller.util.PropertiesUtil;

public class Conexion {


	public static Connection getConnection()  {
		Connection con = null;
		try {
			
			Class.forName(PropertiesUtil.getPropertyDriver());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://"+PropertiesUtil.getPropertyHost()+":"+PropertiesUtil.getPropertyPort()
														+";databaseName="+PropertiesUtil.getPropertyDataBase()  
														+";applicationName=Java App"
														,PropertiesUtil.getPropertyUsername() 
														,PropertiesUtil.getPropertyPassword());
			
		
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		return con;
	}


}
