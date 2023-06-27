package DButil;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBcon {
	
	public static Connection getConnection() throws Exception {
		String url = "jdbc:mariadb://localhost:3306/green02";
		String uid = "root";
		String upw = "1234";
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, uid, upw);
		return conn;
	}
}
