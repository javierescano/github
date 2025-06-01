package llm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Base {
	public Connection c;
	public Base() {
		try {
			 c = DriverManager.getConnection("jdbc:sqlite:E:/exam3ev_peliculas.db");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
