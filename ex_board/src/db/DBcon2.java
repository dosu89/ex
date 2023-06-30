package db;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBcon2 {
	Connection con;
	
	public Connection getConnection() {
		try {
			InitialContext initCtx = new InitialContext();
			Context ctx = (Context)initCtx.lookup("java:comp/env");
			DataSource source = (DataSource)ctx.lookup("dbcp.mydb");
			
			con = source.getConnection();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
