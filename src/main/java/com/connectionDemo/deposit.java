package com.connectionDemo;



import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connectionDemo.Db;

public class deposit extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection con = Db.connect();
      //  int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));
        int amount = Integer.parseInt(request.getParameter("amount"));
          String username = Usergetset.getusername();
        try {
            PreparedStatement pt = con.prepareStatement("SELECT balance FROM probank WHERE username = ?");
            pt.setString(1, username);

            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                int balance = rs.getInt("balance");
                int newBalance = balance + amount;

                PreparedStatement updatePt = con.prepareStatement("UPDATE probank SET balance = ? WHERE username = ?");
                updatePt.setInt(1, newBalance);
                updatePt.setString(2, username);

                int rowsUpdated = updatePt.executeUpdate();
                if (rowsUpdated > 0) {
                    response.getWriter().println("Amount deposited successfully. New balance: " + newBalance);
                } else {
                    response.getWriter().println("Failed to deposit amount. Please try again.");
                }
            } else {
                response.getWriter().println("Account not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
