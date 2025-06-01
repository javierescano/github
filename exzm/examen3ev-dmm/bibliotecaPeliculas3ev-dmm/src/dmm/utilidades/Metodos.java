package dmm.utilidades;

import java.sql.*;

import javax.swing.JOptionPane;

public class Metodos {

	public static int menu() {
		
		String textoOpciones = "1.- Dar de alta una película\n" + 
								"2.- Eliminar una película\n" +
								"3.- Modificar película\n" + 
								"4.- Listar películas\n" + 
								"5.- Buscar películas\n" +
								"6.- Mostrar películas con duración superior a los 100 minutos\n" + 
								"7.- Mostrar películas anteriores al año 2000\n" +
								"8.- Mostrar películas con un precio inferior a 10,00€";
		Integer[] opciones = {1, 2, 3, 4, 5, 6, 7, 8};
		return JOptionPane.showOptionDialog(null, textoOpciones, "MENU", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, null);

	}
	
	public static void darAlta(Statement st) {
		String titulo = JOptionPane.showInputDialog(null, "Introduzca el nombre de la película", "DAR DE ALTA", JOptionPane.PLAIN_MESSAGE);
		int director_id = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca el numero de id del director", "DAR DE ALTA", JOptionPane.PLAIN_MESSAGE));
		int anio = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca el año de la película", "DAR DE ALTA", JOptionPane.PLAIN_MESSAGE));
		int genero_id = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca el género en numero de la película", "DAR DE ALTA", JOptionPane.PLAIN_MESSAGE));
		int valoracion = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca su valoracion de la pelicula", "DAR DE ALTA", JOptionPane.PLAIN_MESSAGE));
		int duracion = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca la duracion de la pelicula", "DAR DE ALTA", JOptionPane.PLAIN_MESSAGE));
		float precio = Float.parseFloat(JOptionPane.showInputDialog(null, "Introduzca el precio de la película", "DAR DE ALTA", JOptionPane.PLAIN_MESSAGE));
		
		try {
			st.execute("INSERT INTO peliculas (titulo, director_id, anio, genero_id, valoracion, duracion, precio)"
					+ "VALUES (" + titulo + "," + director_id + "," + anio + "," + genero_id + "," + valoracion + "," + duracion + "," + precio + ")");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void darBaja(Statement st) {
		int pelicula_id = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca el número identificador de la película que desea dar de baja", "DAR DE BAJA", JOptionPane.PLAIN_MESSAGE));

		try {
			st.executeUpdate("DELETE FROM peliculas WHERE id_pelicula = " + pelicula_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void modificarPelicula(Statement st) {
		int pelicula_id = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca el número identificador de la película que desea modificar", "MODIFICAR", JOptionPane.PLAIN_MESSAGE));
		int anio = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduzca el año modificado", "MODIFICAR", JOptionPane.PLAIN_MESSAGE));

		try {
			st.executeUpdate("UPDATE peliculas SET anio = " + anio + " WHERE id_pelicula = " + pelicula_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void listarPeliculas(Statement st) {
		try {
			ResultSet rs = st.executeQuery("SELECT * FROM peliculas");
			
			for (int i = 1; i < 8; i++) {
				System.out.println(rs.getString(i));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void buscarPelicula(Statement st) {
		String titulo = JOptionPane.showInputDialog(null, "Introduzca el nombre de la película que desea buscar", "BUSCAR", JOptionPane.PLAIN_MESSAGE);
		
		try {
			ResultSet rs = st.executeQuery("SELECT * FROM peliculas WHERE titulo LIKE '" + titulo + "'");
			for (int i = 1; i < 8; i++) {
				System.out.println(rs.getString(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public static void mostrarPeliculasDuracion100(Statement st) {

		try {
			ResultSet rs = st.executeQuery("SELECT * FROM peliculas WHERE duracion > 100");
			for (int i = 1; i < 8; i++) {
				System.out.println(rs.getString(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void mostrarPeliculasAnteriores2000(Statement st) {

		try {
			ResultSet rs = st.executeQuery("SELECT * FROM peliculas WHERE anio < 2000");
			for (int i = 1; i < 8; i++) {
				System.out.println(rs.getString(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void mostrarPeliculasPrecioInferior10(Statement st) {

		try {
			ResultSet rs = st.executeQuery("SELECT * FROM peliculas WHERE precio < 10");
			for (int i = 1; i < 8; i++) {
				System.out.println(rs.getString(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
