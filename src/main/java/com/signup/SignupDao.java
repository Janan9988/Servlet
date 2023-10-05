package com.signup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignupDao {
	
	//for oracle data database
		
		private final static String driver="oracle.jdbc.driver.OracleDriver";
		private final static String url="jdbc:oracle:thin:@localhost:1521/xe"; //here xe is schema name(databes) change to your schema name
		private final static String user="janan";
		private final static String pass="123";
		
		static boolean result;
		public static boolean signup(String name,String email,String username,String password,String phone,String gender,String country) {
			
			try {
				Class.forName(driver);
				
				Connection conn=DriverManager.getConnection(url,user,pass);
				String query="insert into users values (?,?,?,?,?,?,?)";
				PreparedStatement ps=conn.prepareStatement(query);
				//inserting data into users table with PreparedStatement
				ps.setString(1,name);
				ps.setString(2,email);
				ps.setString(3,username);
				ps.setString(4,password);
				ps.setString(5,phone);
				ps.setString(6,gender);
				ps.setString(7,country);
	
				int rowsAffected = ps.executeUpdate();
				if(rowsAffected>0) {
				result=true;
			}else {
				result=false;
			}
			conn.close();//closing connection
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return result;
			
			
		}
	

}
