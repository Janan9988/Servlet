 package com.signup;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


public class SignupServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		//getting user details 
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String phone=request.getParameter("phone");
		String c_pass=request.getParameter("c_pass");
		String gender =request.getParameter("gender");
		String country =request.getParameter("country");
		
		//validating password and conform password is same
		if(password.equals(c_pass)) {
			//if both passwords matched below code will start inserting by LoginDao.signup method
			if(SignupDao.signup(name, email, username, password, phone, gender, country)) {
				out.print("Registretion successfull......."+name);
				//if Registretion is succesfull below code will automatically includes login.html
				RequestDispatcher rd=request.getRequestDispatcher("login.html");
				rd.include(request, response);
			}else {
				out.print("Registretion faild try again.......");
				RequestDispatcher rd=request.getRequestDispatcher("Registretion.html");
				rd.include(request, response);
			}
			
		}else {//if both passwords not matched this code will includes  Registretion.html page again
			out.print("Password not matched  try again.......");
			RequestDispatcher rd=request.getRequestDispatcher("Registretion.html");
			rd.include(request, response);
		}
	}

}
