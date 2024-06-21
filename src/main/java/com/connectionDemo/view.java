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

public class view extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection con = Db.connect();
        int accountNumber = Integer.parseInt(request.getParameter("accountNumber"));

        try {
            PreparedStatement pt = con.prepareStatement("SELECT balance FROM probank WHERE id = ?");
            pt.setInt(1, accountNumber);

            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                int balance = rs.getInt("balance");
                response.getWriter().println("Your balance is: " + balance);
            } else {
                response.getWriter().println("Account not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
