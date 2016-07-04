package cn.edu.sjtu.stap.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	Connection conn;
	
	
	public Connection getConnection() {
		return conn;
	}

	public Database () {
		// TODO configurations
		conn = null;
		String host = "localhost";
		String db = "iut";
		String username = "root";
		String password = "";
		
		
		StringBuilder sb = new StringBuilder ();
		sb.append("jdbc:mysql://").append(host)
			.append("/").append(db).append("?user=")
			.append(username).append("&")
			.append("password=").append(password);
		try {
		    conn =
		       DriverManager.getConnection(sb.toString());

		    
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	public void close() throws Exception {
		conn.close();
	}
}
