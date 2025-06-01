package iag.peliculas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
// Izan Antón García
public class PeliculasApp {
	static final String URL = "jdbc:sqlite:E:\\exam3ev_peliculas.db";

	public static void main(String[] args) {
		int opc = -1;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("Elige una opción: ");
			System.out.println(
					"1- Alta de pelicula\n2- Baja de pelicula\n3- Modificar pelicula\n4- Listar peliculas\n5- Buscar peliculas\n6- Mostrar peliculas con una duración superior a los 100 minutos"
							+ "\n7- Mostrar películas anteriores al año 2000\n8- Mostrar películas con un precio inferior a 10€\n9- Salir");
			opc = sc.nextInt();
			if (opc == 1) {
				altaPelicula();
			} else if (opc == 2) {
				bajaPelicula();
			} else if (opc == 3) {
				modificarPelicula();
			} else if (opc == 4) {
				listarPeliculas();
			} else if (opc == 5) {
				buscarPeliculas();
			} else if (opc == 6) {
				mostrarMas100Minutos();
			} else if (opc == 7) {
				mostrarAnteriores2000();
			} else if (opc == 8) {
				mostrarPrecioInferior10();
			}

		} while (opc != 9);

	}

	public static void altaPelicula() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce el titulo");
		String titulo = sc.nextLine();
		System.out.println("Introduce el id del director");
		int directorID = sc.nextInt();
		System.out.println("Introduce el año");
		int año = sc.nextInt();
		System.out.println("Introduce el id del genero");
		int genero = sc.nextInt();
		System.out.println("Introduce la valoracion");
		int valoracion = sc.nextInt();
		System.out.println("Introduce la duración");
		int duracion = sc.nextInt();
		System.out.println("Introduce el precio");
		double precio = sc.nextDouble();

		Connection c = null;
		PreparedStatement pst = null;
		try {
			c = DriverManager.getConnection(URL);
			c.setAutoCommit(false);
			pst = c.prepareStatement(
					"INSERT INTO peliculas (titulo, director_id, anio, genero_id, valoracion, duracion, precio) VALUES (?, ?, ?, ?, ?, ?, ?)");
			pst.setString(1, titulo);
			pst.setInt(2, directorID);
			pst.setInt(3, año);
			pst.setInt(4, genero);
			pst.setInt(5, valoracion);
			pst.setInt(6, duracion);
			pst.setDouble(7, precio);
			int afectadas = pst.executeUpdate();
			c.commit();
			if (afectadas >= 1) {
				System.out.println("Pelicula añadida correctamente");
			} else {
				System.out.println("Pelicula no añadida");
			}
		} catch (Exception e) {
			try {
				c.rollback();
				e.printStackTrace();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally {
			try {
				pst.close();
				c.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static void bajaPelicula() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce el id de la pelicula a eliminar: ");
		int id = sc.nextInt();
		Connection c = null;
		Statement st = null;
		try {
			c = DriverManager.getConnection(URL);
			st = c.createStatement();
			st.executeUpdate("DELETE FROM peliculas WHERE id_pelicula = " + id + ";");
			System.out.println("Pelicula dada de baja correctamente");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				c.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static void modificarPelicula() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce el id de la pelicula a modificar: ");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.println("Introduce el nombre del dato que quieres modificar: ");
		String nombreDato = sc.nextLine();
		System.out.println("Introduce el dato que quieres modificar: ");
		String dato = sc.nextLine();
		Connection c = null;
		Statement st = null;
		try {
			c = DriverManager.getConnection(URL);
			st = c.createStatement();
			st.executeUpdate("UPDATE peliculas SET " + nombreDato + " = " + dato + " WHERE id_pelicula = " + id + ";");
			System.out.println("Pelicula modificada correctamente");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				c.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static void listarPeliculas() {

		Connection c = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			c = DriverManager.getConnection(URL);
			st = c.createStatement();
			rs = st.executeQuery("Select * FROM peliculas");

			while (rs.next()) {
				System.out.println(rs.getInt(1) + " "  + rs.getString(2) + " " + rs.getInt(3) + " "
						+ rs.getInt(4) + " " + rs.getInt(5) + " " + rs.getInt(6) + " " + rs.getInt(7) + " "
						+ rs.getDouble(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				c.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public static void buscarPeliculas() {
		Connection c = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			// Preguntamos la columna que quiere buscar y el dato que quiere buscar
			Scanner sc = new Scanner(System.in);
			System.out.println("Introduce el tipo de dato por el que quieres buscar la pelicula: ");
			String nombreDato = sc.nextLine();
			System.out.println("Introduce el dato por el que quieres buscar la pelicula: ");
			String dato = sc.nextLine();
			c = DriverManager.getConnection(URL);
			st = c.createStatement();
			rs = st.executeQuery("Select * FROM peliculas WHERE " + nombreDato + " LIKE '" + dato + "';");

			while (rs.next()) {
				System.out.println(rs.getInt(1) + " "  + rs.getString(2) + " " + rs.getInt(3) + " "
						+ rs.getInt(4) + " " + rs.getInt(5) + " " + rs.getInt(6) + " " + rs.getInt(7) + " "
						+ rs.getDouble(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				c.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public static void mostrarMas100Minutos() {
		Connection c = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			c = DriverManager.getConnection(URL);
			st = c.createStatement();
			rs = st.executeQuery("Select * FROM peliculas WHERE duracion > 100;");

			while (rs.next()) {
				System.out.println(rs.getInt(1) + " "  + rs.getString(2) + " " + rs.getInt(3) + " "
						+ rs.getInt(4) + " " + rs.getInt(5) + " " + rs.getInt(6) + " " + rs.getInt(7) + " "
						+ rs.getDouble(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				c.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public static void mostrarAnteriores2000() {
		Connection c = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			c = DriverManager.getConnection(URL);
			st = c.createStatement();
			rs = st.executeQuery("Select * FROM peliculas WHERE anio < 2000;");

			while (rs.next()) {
				System.out.println(rs.getInt(1) + " "  + rs.getString(2) + " " + rs.getInt(3) + " "
						+ rs.getInt(4) + " " + rs.getInt(5) + " " + rs.getInt(6) + " " + rs.getInt(7) + " "
						+ rs.getDouble(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				c.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public static void mostrarPrecioInferior10() {
		Connection c = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			c = DriverManager.getConnection(URL);
			st = c.createStatement();
			rs = st.executeQuery("Select * FROM peliculas WHERE precio < 10;");

			while (rs.next()) {
				System.out.println(rs.getInt(1) + " "  + rs.getString(2) + " " + rs.getInt(3) + " "
						+ rs.getInt(4) + " " + rs.getInt(5) + " " + rs.getInt(6) + " " + rs.getInt(7) + " "
						+ rs.getDouble(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				c.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
}
