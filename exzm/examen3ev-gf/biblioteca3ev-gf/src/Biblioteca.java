import org.sqlite.*;
import org.sqlite.jdbc4.JDBC4PreparedStatement;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;
//Gaojie Fu
public class Biblioteca{
	static Scanner sc = new Scanner(System.in);
	

	public static void main(String[] args) {
		SQLiteConnection con = new SQLiteConnection("exam3ev_peliculas.db");
		Statement st = new Statement(con);
		System.out.println("1.Alta pelicula\n2.Baja pelicula\n3.Modificar pelicula\n4.Listar pelicula\n5.Buscar\n6.Mostrar >100min\n7.Mostrar <año2000\n8.Mostrar precio <10€");
		String opcion = sc.nextLine();
	}
	
	public void alta() {
		String sql = "INSTER INTO peliculas (tiutlo, director_id, anio, genero_id, valoracion, duracion, precio) VALUES (?,?,?,?,?,?,?)";
		PreparedStatement statement = prepareStatement(sql);
	}

}
