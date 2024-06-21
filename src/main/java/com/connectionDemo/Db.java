package com.connectionDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
	public static Connection connect()
    {
   	 Connection con=null;
   	 
   	 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		 
   	 System.out.println("Driver Loaded");
   	 
   	 
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
			 System.out.println("Connection Established");
   	 }
	        catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	 
		return (con);
    
   	
    }
}
