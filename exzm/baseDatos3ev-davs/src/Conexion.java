import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private static final String URL = "jdbc:sqlite:exam3ev_peliculas.db";

	public static Connection conetar() {
		Connection conexion = null;
		try {
			conexion = DriverManager.getConnection(URL);
			System.out.println("Conexion establecida");
		} catch (SQLException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
		return conexion;
	}
}
