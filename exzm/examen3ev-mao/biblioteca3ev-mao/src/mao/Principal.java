// Mario Álvarez Ortega

package mao;

import java.sql.*;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		do {
			opcion = seleccionarOpcion();
			try {
				Connection c = DriverManager.getConnection("jdbc:sqlite:exam3ev_peliculas.db");
				Statement stmt = c.createStatement();
				PreparedStatement ps = null;
				ResultSet rs = null;

				switch (opcion) {
				case 1:
					ps = c.prepareStatement(introducirDatosPelicula());
					ps.execute();
					ps.close();
					break;
				case 2:
					System.out.print("Introduce el id de la pelicula que quieres eliminar: ");
					int idEliminar = sc.nextInt();
					ps = c.prepareStatement("DELETE FROM peliculas WHERE id_pelicula = " + idEliminar);
					ps.execute();
					ps.close();
					break;
				case 3:
					System.out.print("Introduce el id de la pelicula que quieres modificar: ");

					break;
				case 4:
					rs = stmt.executeQuery("SELECT * FROM peliculas");
					while (rs.next()) {
						System.out.println("id_pelicula: " + rs.getInt("id_pelicula") + " titulo: "
								+ rs.getString("titulo") + " director_id: " + rs.getInt("director_id") + " anio: "
								+ rs.getInt("anio") + " genero_id: " + rs.getString("genero_id") + " valoracion: "
								+ rs.getInt("valoracion") + " duracion: " + rs.getInt("duracion") + " precio: "
								+ rs.getInt("precio"));
					}
					rs.close();
					break;
				case 5:
					System.out.print("Introduce una palabra clave para buscar una pelicula: ");
					String palabraClave = sc.next();
					rs = stmt.executeQuery("SELECT * FROM peliculas WHERE titulo LIKE '%" + palabraClave + "%'");
					while (rs.next()) {
						System.out.println(rs.getString("titulo"));
					}
					rs.close();
					break;
				case 6:
					rs = stmt.executeQuery("SELECT * FROM peliculas WHERE duracion > 100");
					while (rs.next()) {
						System.out.println(rs.getString("titulo"));
					}
					rs.close();
					break;
				case 7:
					rs = stmt.executeQuery("SELECT * FROM peliculas WHERE anio < 2000");
					while (rs.next()) {
						System.out.println(rs.getString("titulo"));
					}
					rs.close();
					break;
				case 8:
					rs = stmt.executeQuery("SELECT * FROM peliculas WHERE precio < 10");
					while (rs.next()) {
						System.out.println(rs.getString("titulo"));
					}
					rs.close();
					break;
				case 9:
					System.exit(0);
					break;
				default:
					System.out.println("Debes seleccionar una opcion del 1 al 9");
				}
				System.out.println();
				stmt.close();
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} while (opcion != 9);

	}

	public static String introducirDatosPelicula() {
		Scanner sc = new Scanner(System.in);

		System.out.print("Introduce el titulo: ");
		String titulo = sc.next();
		System.out.print("Introduce el director_id: ");
		int director_id = sc.nextInt();
		System.out.print("Introduce el anio: ");
		int anio = sc.nextInt();
		System.out.print("Introduce el genero_id: ");
		int genero_id = sc.nextInt();
		System.out.print("Introduce la valoracion (1-5): ");
		int valoracion = sc.nextInt();
		System.out.print("Introduce la duracion (minutos): ");
		int duracion = sc.nextInt();
		System.out.print("Introduce el precio: ");
		int precio = sc.nextInt();

		String consulta = "INSERT INTO peliculas (titulo, director_id, anio, genero_id, valoracion, duracion, precio) VALUES ("
				+ titulo + ", " + director_id + ", " + anio + ", " + genero_id + ", " + valoracion + ", " + duracion
				+ ", " + precio + ")";
		sc.close();
		return consulta;
	}

	public static int seleccionarOpcion() {
		Scanner sc = new Scanner(System.in);
		System.out.println("1. Alta de película");
		System.out.println("2. Baja de película");
		System.out.println("3. Modificar película");
		System.out.println("4. Listar películas");
		System.out.println("5. Buscar películas");
		System.out.println("6. Mostrar películas con duracion superior a 100 minutos");
		System.out.println("7. Mostrar películas anteriores al año 2000");
		System.out.println("8. Mostrar películas con un precio inferior a 10€");
		System.out.println("9. Salir");
		System.out.print("Selecciona la opcion que quieres del 1 al 9: ");
		int opcion = sc.nextInt();
		System.out.println();
		return opcion;
	}

}
