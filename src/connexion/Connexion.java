package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
	private static String login = "root";
	private static String password = "";
	private static String url="jdbc:mysql://localhost/Restaurant";
	private static Connection cn = null;
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ex) {
			System.out.println("Problème de chargement du Driver!");
			System.exit(1);
		}

		String url = "jdbc:mysql://localhost:3306/restaurant";
		try {
			 cn = DriverManager.getConnection(url,login,password);
		} catch (SQLException e) {
			System.err.println("Error opening SQL connection:" + e.getMessage());
		}
	}
	public static Connection getConn() {
		return cn;
	}
}
