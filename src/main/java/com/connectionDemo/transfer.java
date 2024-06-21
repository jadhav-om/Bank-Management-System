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

public class transfer extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection con = Db.connect();
       // int senderAccount = Integer.parseInt(request.getParameter("senderAccount"));
        int id = Integer.parseInt(request.getParameter("receiverAccount"));
        int amount = Integer.parseInt(request.getParameter("amount"));
        String username = Usergetset.getusername();
        try {
            // Check sender's balance
            PreparedStatement senderPt = con.prepareStatement("SELECT balance FROM probank WHERE username = ?");
            senderPt.setString(1, username);

            ResultSet senderRs = senderPt.executeQuery();
            if (senderRs.next()) {
                int senderBalance = senderRs.getInt("balance");
                if (senderBalance >= amount) {
                    // Subtract amount from sender's balance
                    int senderNewBalance = senderBalance - amount;

                    PreparedStatement senderUpdatePt = con.prepareStatement("UPDATE probank SET balance = ? WHERE username = ?");
                    senderUpdatePt.setInt(1, senderNewBalance);
                    senderUpdatePt.setString(2, username);
                    senderUpdatePt.executeUpdate();

                    // Add amount to receiver's balance
                    PreparedStatement receiverPt = con.prepareStatement("SELECT balance FROM probank WHERE id = ?");
                    receiverPt.setInt(1, id);

                    ResultSet receiverRs = receiverPt.executeQuery();
                    if (receiverRs.next()) {
                        int receiverBalance = receiverRs.getInt("balance");
                        int receiverNewBalance = receiverBalance + amount;

                        PreparedStatement receiverUpdatePt = con.prepareStatement("UPDATE probank SET balance = ? WHERE id = ?");
                        receiverUpdatePt.setInt(1, receiverNewBalance);
                        receiverUpdatePt.setInt(2, id);
                        receiverUpdatePt.executeUpdate();

                        response.getWriter().println("Transfer successful");
                    } else {
                        response.getWriter().println("Receiver account not found");
                    }
                } else {
                    response.getWriter().println("Insufficient balance in the sender account");
                }
            } else {
                response.getWriter().println("Sender account not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

