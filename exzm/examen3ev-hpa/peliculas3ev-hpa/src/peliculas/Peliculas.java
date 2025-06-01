package peliculas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Peliculas {

	public static final String URL = "jdbc:sqlite:exam3ev_peliculas.db";
	
	public static void main(String[] args) {
		
		
		String [] opciones = {"Alta", "Baja", "Modificar", "Listar", "Buscar", "+100 minutos"," Año < 2000","Precio < 10,00€", "Salir"}; // me voy a centrar en estos porque de momento no me da tiempo a más...
		int opcion = -1;
		
		do {
			opcion = JOptionPane.showOptionDialog(null, "Selecciona una opción:", "Videoclub", JOptionPane.PLAIN_MESSAGE, JOptionPane.PLAIN_MESSAGE, null, opciones, null);
			
			if (opcion == 0) {
				altaPelicula();
			} else if (opcion == 1) {
				bajaPelicula();
			} else if (opcion == 2) {
				modificarPelicula();
			} else if (opcion == 3) {
				listarPeliculas();
			} else if (opcion == 4) {
				buscarPelicula();
			} else if (opcion == 5) {
				largaDuracion();
			} else if (opcion == 6) {
				anio2000();
			} else if (opcion == 6) {
				precio10();
			}
			
		} while (opcion != 8);
	}
	
	public static void altaPelicula(){
		
		try {
			
			Connection conexion = DriverManager.getConnection(URL);
			//Statement miSt = conexion.createStatement();
			
			String sql = "insert into peliculas (titulo, director_id, genero_id) values (?,?,?);";
			
			PreparedStatement pS = conexion.prepareStatement(sql);
			
			
			String titulo = JOptionPane.showInputDialog("Inserta el titulo de la película");
			int director = Integer.parseInt(JOptionPane.showInputDialog("Inserta el id del director de la película"));
			int genero = Integer.parseInt(JOptionPane.showInputDialog("Inserta el id del genero de la película"));
			
			pS.setString(1, titulo);
			pS.setInt(2, director);
			pS.setInt(3, genero);
			
			pS.executeUpdate();
			
			pS.close();
			conexion.close();
			
		
			
			
		} catch (Exception e) {
			System.out.println("Error al conectar en la base de datos.");
		}
		
	}
		
	public static void bajaPelicula() {
		
		try {
			
			Connection conexion = DriverManager.getConnection(URL);
			//Statement miSt = conexion.createStatement();
			
			String sql = "delete from peliculas where id_pelicula = ?;";
			
			PreparedStatement pS = conexion.prepareStatement(sql);

			int idPelicula = Integer.parseInt(JOptionPane.showInputDialog("Inserta el id de la película que quieres borrar"));

			pS.setInt(1, idPelicula);
			
			pS.executeUpdate();
			
			pS.close();
			conexion.close();
			
		
			
			
		} catch (Exception e) {
			System.out.println("Error al conectar en la base de datos.");
		}
		
	}
	
	public static void modificarPelicula(){
		
		try {
			
			Connection conexion = DriverManager.getConnection(URL);
			//Statement miSt = conexion.createStatement();
			
			String sql = "update peliculas set anio = ? where id_pelicula = ?;";
			
			PreparedStatement pS = conexion.prepareStatement(sql);
			
			int anioPelicula = Integer.parseInt(JOptionPane.showInputDialog("Inserta el nuevo año de estreno de la película a modificar"));
			int idPelicula = Integer.parseInt(JOptionPane.showInputDialog("Inserta el id de la película que quieres modificar"));

			pS.setInt(1, anioPelicula);
			pS.setInt(2, idPelicula);
			
			pS.executeUpdate();
			
			pS.close();
			conexion.close();
			
		
			
			
		} catch (Exception e) {
			System.out.println("Error al conectar en la base de datos.");
		}
		
	}
	
	
	public static void listarPeliculas() {
		
		try {
			
			Connection conexion = DriverManager.getConnection(URL);
			Statement miSt = conexion.createStatement();
			
			String sql = "select * from peliculas";
			
			ResultSet rs = miSt.executeQuery(sql);
			
			int i = 1;
			while (rs.next()) {
				JOptionPane.showMessageDialog(null, i + " " + rs.getString("titulo"));
				i++;
			}
			
			rs.close();
			miSt.close();
			conexion.close();
			
		
			
			
		} catch (Exception e) {
			System.out.println("Error al conectar en la base de datos.");
		}
		
	}
	
	public static void buscarPelicula() { // lo he intentado de varias formas tambien concatenando la instruccion sql con la palabra, y asi me saltaba el preparedStatement pero nada...
		
		String palabra = JOptionPane.showInputDialog("Introduce una/s palabra del titulo que quieras buscar");
		
		try {
			String sql = "select * from peliculas where titulo loke '%?%';";
			
			Connection conexion = DriverManager.getConnection(URL);
			
			PreparedStatement pS = conexion.prepareStatement(sql);
			
			pS.setString(1, palabra);
			pS.executeUpdate();
			
			pS.close();
			
			Statement miSt = conexion.createStatement();
			
			ResultSet rs = miSt.executeQuery(sql);
			
			int i = 1;
			while (rs.next()) {
				JOptionPane.showMessageDialog(null, i + " " + rs.getString("titulo"));
				i++;
			}
			
			rs.close();
			miSt.close();
			conexion.close();
			
		
			
			
		} catch (Exception e) {
			System.out.println("Error al conectar en la base de datos.");
		}
		
	}
	
	public static void largaDuracion() {
		
		try {
			
			Connection conexion = DriverManager.getConnection(URL);
			Statement miSt = conexion.createStatement();
			
			String sql = "select * from peliculas where duracion > 100;";
			
			ResultSet rs = miSt.executeQuery(sql);
			
			int i = 1;
			while (rs.next()) {
				JOptionPane.showMessageDialog(null, i + " " + rs.getString("titulo"));
				i++;
			}
			
			rs.close();
			miSt.close();
			conexion.close();
			
		
			
			
		} catch (Exception e) {
			System.out.println("Error al conectar en la base de datos.");
		}
		
		
	}
	
	public static void anio2000() {
		
		try {
			
			Connection conexion = DriverManager.getConnection(URL);
			Statement miSt = conexion.createStatement();
			
			String sql = "select * from peliculas where anio < 2000;";
			
			ResultSet rs = miSt.executeQuery(sql);
			
			int i = 1;
			while (rs.next()) {
				JOptionPane.showMessageDialog(null, i + " " + rs.getString("titulo"));
				i++;
			}
			
			rs.close();
			miSt.close();
			conexion.close();
			
		
			
			
		} catch (Exception e) {
			System.out.println("Error al conectar en la base de datos.");
		}
	}
	
	
	public static void precio10() { // este nose porque no me coge el 10...
		
		try {
			
			Connection conexion = DriverManager.getConnection(URL);
			Statement miSt = conexion.createStatement();
			
			String sql = "select * from peliculas where precio < 10;";
			
			ResultSet rs = miSt.executeQuery(sql);
			
			int i = 1;
			while (rs.next()) {
				JOptionPane.showMessageDialog(null, i + " " + rs.getString("titulo"));
				i++;
			}
			
			rs.close();
			miSt.close();
			conexion.close();
			
		
			
			
		} catch (Exception e) {
			System.out.println("Error al conectar en la base de datos.");
		}
		
	}
}


