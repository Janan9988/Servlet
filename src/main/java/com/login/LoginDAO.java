package com.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.RequestDispatcher;

public class LoginDAO {
	
	
	//details of oracle database 
	
	private final static String driver="oracle.jdbc.driver.OracleDriver";
	private final static String url="jdbc:oracle:thin:@localhost:1521/xe";//here xe is  schema name(database) change to your DB schema name
	private final static String user="janan";
	private final static String pass="123";

	public static boolean validate(String username,String password) throws ClassNotFoundException, SQLException {
		//DB connection
		boolean valid = false;
		
		Class.forName(driver);
		Connection conn=DriverManager.getConnection(url,user,pass);
		Statement st=conn.createStatement();
		String query ="select * from users where username='"+username+"' ";
		ResultSet rs=st.executeQuery(query);
		
		if(rs.next()) {
			//validating password 
			if(rs.getString("password").equals(password)) {
				valid=true;
			}
		}
		
		//closing the connection
		rs.close();
		st.close();
		conn.close();
		return valid;
		
		
	} 
	
}
