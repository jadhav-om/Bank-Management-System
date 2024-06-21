package com.connectionDemo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.connectionDemo.Db;

public class create extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection con = Db.connect();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String city = request.getParameter("city");
        int balance = Integer.parseInt(request.getParameter("initialBalance"));
        
        try {
            PreparedStatement pt = con.prepareStatement("INSERT INTO probank VALUES (?, ?, ?, ?, ?, ?)");
            pt.setInt(1, generateAccountNumber());
            pt.setString(2, name);
            pt.setString(3, city);
            pt.setInt(4, balance);
            pt.setString(5, username);
            pt.setString(6, password);
            
            int rowsAffected = pt.executeUpdate();
            if (rowsAffected > 0) {
                response.getWriter().println("Account Created Successfully....");
                response.getWriter().println("Your Account Number is: " + generateAccountNumber());
            } else {
                response.getWriter().println("Failed to create account. Please contact the admin.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private int generateAccountNumber() {
        int min = 11111111;  
        int max = 99999999; 
        return (int) (Math.random() * (max - min + 1) + min); 
    }
}