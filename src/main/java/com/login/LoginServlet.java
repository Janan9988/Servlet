package com.login;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


public class LoginServlet extends HttpServlet {

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//this is for setting respone type (it will set which type of respone to send to the browser or clint)
		
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		
		/*getting user given values(username,password) by the login.html input "name" like name="u_name" and name="password"
		 <input type="text" id="username" name="u_name" required>
		 <input type="password" id="password" name="password" required>*/
		
		String u_name=request.getParameter("u_name");
		String pass =request.getParameter("password");
		
		
		    //validating username and password by calling LoginDAO validate method  
			try {
				//if LoginDAO.validate returns "true" it will forword to Wellcom servlet by RequestDispatcher
				if(LoginDAO.validate(u_name, pass)) {
					out.print("Welcome Back <strong>"+u_name+"</strong>");
					RequestDispatcher rd =request.getRequestDispatcher("Wellcome");
					rd.forward(request, response);
					
				}else {
					//if LoginDAO.validate returns "false" it will includes  login.html again by RequestDispatcher
					out.print("Login faild try again........");
					RequestDispatcher rd=request.getRequestDispatcher("login.html");
					rd.include(request, response);
					
				}
			} catch (ServletException | IOException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		
	}
	
}
