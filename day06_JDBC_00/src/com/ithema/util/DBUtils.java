package com.ithema.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DBUtils {
	private static String driverclass;
	private static String url;
	private static String username;
	private static String password;
	
	static {
		ResourceBundle rb = ResourceBundle.getBundle("dbinfo");
		driverclass = rb.getString("driverclass");
		url = rb.getString("url");
		username = rb.getString("username");
		password = rb.getString("password");
		try {
			Class.forName(driverclass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}	
	
	public static Connection getConnection() throws Exception {
		return DriverManager.getConnection(url, username, password);
		
	}
	
	public static void closeAll(ResultSet rs, Statement stmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			rs = null;
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			stmt = null;
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			conn = null;
		}
	}
	
}
