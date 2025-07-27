package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    private static final String URL = "jdbc:derby:E:\\CodingPlayground\\Java\\Bank Management System\\BankDB;create=true"; // 'create=true' will create DB if it doesn't exist

    static {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver"); // Derby embedded driver
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
    	try {
    		Connection con = DriverManager.getConnection(URL);
    		if(con!=null) {
    			System.out.println("Database connected successfully!");
    			return con;
    		}
		} catch (Exception e) {
		        System.err.println("❌ Failed to connect to Derby DB: " + e.getMessage());
		        e.printStackTrace();
		        return null;
		    }
        return null;
    }
}
