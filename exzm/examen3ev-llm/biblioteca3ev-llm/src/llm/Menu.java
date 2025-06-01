package llm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Menu {
	public static final String[] OPCIONES = { "Ata de película", "Baja de película", 
			"Modificar Pelicula", "Listar peículas", "Buscar películas", 
			"Mostrar peliculas +100mins", "Mostrar peliculas antes 2000", "Mostrar Peliculas -10€"};
	public static boolean iniciarMenu() {
		int opcion;
		Base base = new Base();
		opcion = JOptionPane.showOptionDialog(null, "Que quieres hacer", "Peliculas", 
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, 
				OPCIONES, OPCIONES[0]);
		if (opcion == 0 ) {
			altaPelicula(base);
		} else if (opcion == 1 ) {
			bajaPelicula(base);
		} else if (opcion == 2 ) {
			modificarPelicula(base);
		} else if (opcion == 3 ) {
			listarPeliculas(base);
		} else if (opcion == 4 ) {
			buscarPelicula(base);
		} else if (opcion == 5 ) {
			mostrarPeliculasLargas(base);
		} else if (opcion == 6 ) {
			mostrarPeliculasViejas(base);
		} else if (opcion == 7 ) {
			mostrarPeliculasBaratas(base);
		} else if (opcion == JOptionPane.CLOSED_OPTION) {
			return true;
		}
		return false;
	}
	
	public static void altaPelicula(Base base) {
		String titulo, director_id, anio, genero_id, valoracion, duracion, precio;
		titulo = JOptionPane.showInputDialog("introduce el titulo");
		director_id = JOptionPane.showInputDialog("introduce el id del director");
		anio = JOptionPane.showInputDialog("introduce el año");
		genero_id = JOptionPane.showInputDialog("introduce el id de genero");
		valoracion = JOptionPane.showInputDialog("introduce la valoracion");
		duracion = JOptionPane.showInputDialog("introduce la duracion");
		precio = JOptionPane.showInputDialog("introduce el precio");
		
		
		try {
			Statement s = base.c.createStatement();
			s.executeUpdate("INSERT INTO peliculas (titulo, director_id, anio, genero_id, valoracion, "
					+ "duracion, precio) VALUES ('"+ titulo +"', "+ director_id + ", " + anio + ", " + genero_id + 
					", " + valoracion + ", " + duracion + ", " + precio + ");");
			
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void bajaPelicula(Base base) {
		String id;
		id = JOptionPane.showInputDialog("introduce el id de la pelicula");
		try {
			Statement s = base.c.createStatement();
			s.executeUpdate("DELETE FROM  peliculas WHERE id_pelicula = " + id);
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void modificarPelicula(Base base) {
		final String[] CAMPOS = {"titulo", "director_id", "anio", "genero_id", "valoracion", "duracion", "precio"};
		String id, dato;
		int opcion;
		id = JOptionPane.showInputDialog("id de la pelicula a modificar");
		opcion = JOptionPane.showOptionDialog(null, "Que campo quieres modificar", "Peliculas", 
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, 
				CAMPOS, CAMPOS[0]);
		dato = JOptionPane.showInputDialog("introduce el nuevo dato");
		try {
			Statement s = base.c.createStatement();
			s.executeUpdate("UPDATE peliculas set "+ opcion + " = " + CAMPOS[opcion] + " WHERE id_pelicula = " + id);
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void listarPeliculas(Base base) {
		try {
			
			Statement s = base.c.createStatement();
			ResultSet rs =  s.executeQuery("SELECT * FROM peliculas");
			
			String filas = "";
			String tabla = "";
			do {
				rs.next();
				for (int i = 1; i <= 8; i++ ) {
					
					filas += rs.getString(i) + " ";
				}
				tabla += filas + "\n";
			}while (!rs.isLast());
			System.out.println(tabla);
			
			rs.close();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void buscarPelicula(Base base) {
		String titulo;
		titulo = JOptionPane.showInputDialog("introduce el titulo a buscar");
		try {
			Statement s = base.c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM peliculas WHERE titulo LIKE '" + titulo + "';" );
			String filas = "";
			String tabla = "";
			
			do {
				rs.next();
				for (int i = 1; i <= 8; i++ ) {
					
					filas += rs.getString(i) + " ";
				}
				tabla += filas + "\n";
			}while (!rs.isLast());
			System.out.println(tabla);
			
			rs.close();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void mostrarPeliculasLargas(Base base) {
		final String DURACION_LIMITE = "100";
		try {
			Statement s = base.c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM peliculas WHERE duracion > "+ DURACION_LIMITE + ";" );
			
			String filas = "";
			String tabla = "";
			do {
				rs.next();
				for (int i = 1; i <= 8; i++ ) {
					
					filas += rs.getString(i) + " ";
				}
				tabla += filas + "\n";
			}while (!rs.isLast());
			System.out.println(tabla);
			
			rs.close();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void mostrarPeliculasViejas(Base base) {
		final String ANIO_LIMITE = "2000";
		try {
			Statement s = base.c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM peliculas WHERE anio < "+ ANIO_LIMITE + ";" );
			String filas = "";
			String tabla = "";
			do {
				rs.next();
				for (int i = 1; i <= 8; i++ ) {
					
					filas += rs.getString(i) + " ";
				}
				tabla += filas + "\n";
			}while (!rs.isLast());
			System.out.println(tabla);
			
			rs.close();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void mostrarPeliculasBaratas(Base base) {
		final String PRECIO_LIMITE = "10";
		try {
			Statement s = base.c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM peliculas WHERE precio < "+ PRECIO_LIMITE +";" );
			String filas = "";
			String tabla = "";
			do {
				rs.next();
				for (int i = 1; i <= 8; i++ ) {
					
					filas += rs.getString(i) + " ";
				}
				tabla += filas + "\n";
			}while (!rs.isLast());
			System.out.println(tabla);
			
			rs.close();
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
