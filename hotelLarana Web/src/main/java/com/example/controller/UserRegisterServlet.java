package com.example.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.UserDao;
import com.example.model.User;

@WebServlet("/register")
public class UserRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
private UserDao userDao = new UserDao();

	public UserRegisterServlet() {
		super();
	}
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("userregister.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String FirstName = request.getParameter("FirstName");
        String LastName = request.getParameter("LastName");
        String Email = request.getParameter("Email");
        String Password = request.getParameter("Password");
        String UserType = request.getParameter("UserType");
        
        User user = new User();
        user.setFirstName(FirstName);
        user.setLastName(LastName);
        user.setEmail(Email);
        user.setPassword(Password);
        user.setUserType(UserType);
        try {
			userDao.registerUser(user);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
        response.sendRedirect("login.jsp");
    }
}
