package com.registration.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import com.registration.dao.Userdao;
import com.registration.jpa.student;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Userdao userDao;

    public void init() {
        userDao = new Userdao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        register(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	
//    	PrintWriter out = response.getWriter();
//    	out.println();
//    	retrieve(request , response);
//        response.sendRedirect("register.jsp");
    	
    	
    	String clientOrigin = request.getHeader("origin");
        response.setHeader("Access-Control-Allow-Origin", clientOrigin);
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Access-Control-Allow-Origin,CopyStreamException,Access-Control-Allow-Methods,Access-Control-Max-Age");
    	
    	String action = request.getServletPath();
    	
    	try {
            switch (action) {
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    	
    	

    	
    }
    
    private void listUser(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
    	        List < student > listUser = userDao.getAllUser();
    	        request.setAttribute("listUser", listUser);
//    	        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
//    	        dispatcher.forward(request, response);
    	        PrintWriter out = response.getWriter();
    	        
    	        List<student> userdetails = listUser;
    	        
    	    	out.println(new Gson().toJson(listUser));

    	        
    	    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String city = request.getParameter("city");
        String name = request.getParameter("name");
        
        student stud = new student();
        stud.setId(id);
        stud.setCity(city);
        stud.setName(name);
        
        userDao.saveUser(stud);

        RequestDispatcher dispatcher = request.getRequestDispatcher("register-success.jsp");
        dispatcher.forward(request, response);
    }
    
    private void retrieve(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	
    	student stud = new student();
//    	stud.getId();
    	System.out.println(stud.getName());
    	PrintWriter out = response.getWriter();
    	out.println(stud.getId());
    	
    	
    	
    }
        
}