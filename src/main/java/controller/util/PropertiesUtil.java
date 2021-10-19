package controller.util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil
{
	private static Properties prop;
	
	static
	{
		prop = new Properties();
		
		try
		{
			prop.load(PropertiesUtil.class.getResourceAsStream("config.properties"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static String getPropertyUrl()
	{
		return prop.getProperty("url");
						
	}
	
	public static String getPropertyHost()
	{
		return prop.getProperty("host");
	}
	
	
	public static String getPropertyPort()
	{
		return prop.getProperty("port");
	}
	
	public static String getPropertyPassword()
	{
		return prop.getProperty("password");
	}
	public static String getPropertyDriver()
	{
		return prop.getProperty("driver");
	}

	public static String getPropertyDataBase() 
	{
		return prop.getProperty("dataBase");
	}
	
	public static String getPropertyUsername() 
	{
		return prop.getProperty("username");
	}
	
	
}
