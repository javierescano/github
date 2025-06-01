package bibliotecaPeliculas;

import java.sql.*;

import javax.swing.JOptionPane;

public class AppBiblioteca {

	public static void main(String[] args) {
		//Isela Pilar González Primo

		try {

			Connection conexion = DriverManager.getConnection("jdbc:sqlite:E:\\exam3ev_peliculas.db");

			mostrarOpciones(conexion);

		} catch (Exception e) {

			System.out.println("Error");
		}

	}

	public static void mostrarOpciones(Connection conexion) {

		String[] OPCIONES = {"Alta", "Baja", "Modificar", "Listar", "Buscar"};

		int opc = JOptionPane.showOptionDialog(null, "Elige qué quieres hacer con las películas:", "Biblioteca Peliculas", 0, -1, null, OPCIONES, OPCIONES);

		switch (opc) {
		case 0:
			insertarPeli(conexion);
			break;
		case 1: 
			eliminarPeli(conexion);
			break;
		case 2:
			modificarPeli(conexion);
			break;
		case 3: 
			mostrarPelis(conexion);
			break;
		case 4: 
			buscarPeli(conexion);
			break;

		default:

			return;
		}

	}

	public static void insertarPeli(Connection conexion) {

		String titulo = JOptionPane.showInputDialog("Titulo: ");
		String director_idSt = JOptionPane.showInputDialog("ID del director: ");
		String anioSt = JOptionPane.showInputDialog("Año: ");
		String genero_idSt = JOptionPane.showInputDialog("Genero ID: ");
		String valoracionSt = JOptionPane.showInputDialog("Valoracion : ");
		String duracionSt = JOptionPane.showInputDialog("Duracion: ");
		String precioSt = JOptionPane.showInputDialog("Precio: ");

		int director_id = Integer.parseInt(director_idSt);
		int anio = Integer.parseInt(anioSt);
		int genero_id = Integer.parseInt(genero_idSt);
		int valoracion = Integer.parseInt(valoracionSt);
		int duracion = Integer.parseInt(duracionSt);
		double precio = Integer.parseInt(precioSt);

		try {
			String sql = "INSERT INTO peliculas (titulo, director_id, anio, genero_id, valoracion, duracion, precio) VALUES (?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement ps = conexion.prepareStatement(sql);

			ps.setString(1, titulo);
			ps.setInt(2, director_id);
			ps.setInt(3, anio);
			ps.setInt(4, genero_id);
			ps.setInt(5, valoracion);
			ps.setInt(6, duracion);
			ps.setDouble(7, precio);
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Película ingresada.");

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Error al introducir película.");
		}
	}

	public static void eliminarPeli(Connection conexion) {
		
		String id_peliculaSt = JOptionPane.showInputDialog("ID de pelicula: ");
		int id_pelicula = Integer.parseInt(id_peliculaSt);

		try {

			String sql = "DELETE FROM peliculas WHERE id_pelicula = ?";
			PreparedStatement ps = conexion.prepareStatement(sql);
			
			ps.setInt(1, id_pelicula);
			ps.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Película eliminada.");

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Error al eliminar película.");
		}
	}
	
	public static void modificarPeli(Connection conexion) {
		
		String id_peliculaSt = JOptionPane.showInputDialog("ID de pelicula: ");
		String anioNuevoSt = JOptionPane.showInputDialog("Año a cambiar: ");
		
		int id_pelicula = Integer.parseInt(id_peliculaSt);
		int anioNuevo = Integer.parseInt(anioNuevoSt);
		
		try {
			
			String sql = "UPDATE peliculas SET anio = ? WHERE id_pelicula = ?";
			PreparedStatement ps = conexion.prepareStatement(sql);
			
			ps.setInt(1, anioNuevo);
			ps.setInt(2, id_pelicula);
			ps.executeUpdate();

			JOptionPane.showMessageDialog(null, "Película modificada.");
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al modificar película.");
			System.out.println(e);
		}
		
	}

	public static void mostrarPelis(Connection conexion) {

		try {
			
			String sql = "SELECT * FROM peliculas";
			Statement stm = conexion.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			
			while (rs.next()) {
				
				System.out.println("Pelicula: ");
				System.out.println(rs.getInt("id_pelicula"));
				System.out.println(rs.getString("titulo"));
				System.out.println(rs.getInt("director_id"));
				System.out.println(rs.getInt("anio"));
				System.out.println(rs.getInt("genero_id"));
				System.out.println(rs.getInt("valoracion"));
				System.out.println(rs.getInt("duracion"));
				System.out.println(rs.getDouble("precio"));
				
			}
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Error al listar películas.");
			System.out.println(e);
		}

	}
	
	public static void buscarPeli(Connection conexion) {

		String palabraClave = JOptionPane.showInputDialog("Escribe la palabra clave: ");

		try {

			String sql = "SELECT * FROM peliculas WHERE titulo LIKE ?";
			PreparedStatement ps = conexion.prepareStatement(sql);
			Statement stm = conexion.createStatement();
			ResultSet rs = stm.executeQuery(sql);

			ps.setString(1, palabraClave);
			ps.executeUpdate();

			System.out.println(rs.getString("titulo"));

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Error al encontrar película.");
			System.out.println(e);
		}
	}

}
