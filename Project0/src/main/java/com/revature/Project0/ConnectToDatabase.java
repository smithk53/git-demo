package com.revature.Project0;
/*
 * @author Kyle Smith
 * @author Quintin Donneley
 * */
import java.io.*;
import java.*;
import java.sql.*;
import java.util.Properties;
/*import com.revature.Project0.org.apache.logging.log4j.LogManager;
import com.revature.Project0.org.apache.logging.log4j.Logger;*/
import java.util.logging.*;
public class ConnectToDatabase {
	public static Connection connect = null;
	static InputStream istream = null;
	static final Logger connectionlog = Logger.getLogger(ConnectToDatabase.class.getName());
	static Properties ps = new Properties();
	static String url = "";
	static String user = "";
	static String pass = "";
	private ConnectToDatabase() {
		
	}
	/*method to get and display the connection on the screen*/
	public static Connection acquireConnection() {
		//check if there is a connection established before applying any logic
		if(connect != null) 
		{
			//log the connection if there is one
			connectionlog.info("Connection you are linked to: "+connect);
			return ConnectToDatabase.connect;
		}
		else {
			System.err.println("There is no connection.");
		}
		try {
			istream = new FileInputStream("C:\\Users\\User\\Documents\\workspace-sts-3.9.8.RELEASE\\Project0\\src\\main\\java\\com\\revature\\Project0\\connection.properties");
			ps.load(istream);
			//get the connection object
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//The URL, user, and pass are indicated in the connection.properties file
			url = ps.getProperty("jdbc.url");
			user = ps.getProperty("jdbc.user");
			pass = ps.getProperty("jdbc.password");
			//specify the database that I want to connect to
			connect = DriverManager.getConnection(url, user, pass);
			//System.gc();//call the garbage collector
			return connect;
		}
		catch(Exception e){
			connectionlog.info("No connection is established");
		}
		finally {
			try {
				istream.close();
			}
			catch(IOException ioe) {
				ioe.printStackTrace();
			}
		}
		return null;
	}
}
