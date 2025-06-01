//Hugo Carcedo Ayala
package Biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class menu {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		
		try {
			//Al hacer las pruebas me sale que no existe la tabla peliculas
			Connection conexion = DriverManager.getConnection("jdbc:sqlite:E:\\examen3ev_peliculas");
			System.out.println("Bienvenido");
			//Hago el menu
			do {
				System.out.println("Introduzca la opción de lo que desea hacer: ");
				System.out.println("----------------------------------------------------");
				System.out.println("1. Insertar Peliculas");
				System.out.println("2. Dar de baja una película");
				System.out.println("3. Modificar los datos de una película");
				System.out.println("4. Listar películas");
				System.out.println("5. Buscar películas");
				System.out.println("6. Mostrar películas con duración superior a 100 minutos");
				System.out.println("7. Mostrar películas anteriores al año 2000");
				System.out.println("8. Mostrar películas con un precio inferior a 10,00€");
				System.out.println("9. Salir");
				System.out.println("----------------------------------------------------");
				//Compruebo que la opcion que elige está incluida en el menu
				do {
					opcion = sc.nextInt();
					if (opcion < 1 || opcion > 9) {
						System.out.println("\nERROR		Opción no válida");
					}
				} while (opcion < 1 || opcion > 9);
				
				switch (opcion) {
				case 1:
					insertar(conexion, sc);
					break;
				case 2:
					baja(conexion, sc);
					break;
				case 3:
					modificar(conexion, sc);
					break;
				case 4:
					listar(conexion, sc);
					break;
				case 5:
					buscar(conexion, sc);
					break;
				case 6:
					peliculasMayores100(conexion, sc);
					break;
				case 7:
					peliculasAntes2000(conexion, sc);
					break;
				case 8:
					peliculasInferior10(conexion, sc);
					break;
				}
			} while (opcion != 9);
			conexion.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//Creo el metodo insertar
	public static void insertar(Connection conexion, Scanner sc) {
		try {
			PreparedStatement insertarPelicula = conexion.prepareStatement("Insert into peliculas (titulo, director_id, anio, genero_id, valoracion, duracion, precio) values (?, ?, ?, ?, ?, ?, ?)");
			System.out.println("Introduce el titulo de la película");
			String titulo = sc.nextLine();
			System.out.println("Introduce el id del director de la película");
			int director = sc.nextInt();
			System.out.println("Introduce el año de la película");
			int anio = sc.nextInt();
			System.out.println("Introduce el id del genero de la película");
			int genero = sc.nextInt();
			System.out.println("Introduce la valoracion de la película");
			int valoracion = sc.nextInt();
			System.out.println("Introduce la duracion en minutos de la película");
			int duracion = sc.nextInt();
			System.out.println("Introduce el precio de la película");
			double precio = sc.nextDouble();
			
			insertarPelicula.setString(1, titulo);
			insertarPelicula.setInt(2, director);
			insertarPelicula.setInt(3, anio);
			insertarPelicula.setInt(4, genero);
			insertarPelicula.setInt(5, valoracion);
			insertarPelicula.setInt(6, duracion);
			insertarPelicula.setDouble(7, precio);
			
			insertarPelicula.execute();
			insertarPelicula.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Creo el metodo baja
	public static void baja(Connection conexion, Scanner sc) {
		try {
			System.out.println("Introduce el id de la película que se desea eliminar");
			int id = sc.nextInt();
			Statement eliminarPelicula = conexion.createStatement();
			
			String SQL = "Delete From peliculas Where id_pelicula = " + id;
			
			eliminarPelicula.execute(SQL);
			eliminarPelicula.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Creo el metodo modificar
	public static void modificar(Connection conexion, Scanner sc) {
		int opcionModificacion = 0;
		try {
			String SQL = "Update peliculas ";
				
			System.out.println("Introduce el id de la película que se desea modificar");
			int id = sc.nextInt();
			Statement modificarPelicula = conexion.createStatement();
			
			System.out.println("Introduzca la opción que desea modificar: ");
			System.out.println("----------------------------------------------------");
			System.out.println("1. Titulo");
			System.out.println("2. Director");
			System.out.println("3. Año");
			System.out.println("4. Genero");
			System.out.println("5. Valoracion");
			System.out.println("6. Duracion");
			System.out.println("7. Precio");
			System.out.println("----------------------------------------------------");
			switch(opcionModificacion) {
			case 1:
				System.out.println("Introduce el titulo de la película");
				sc.nextLine();
				String titulo = sc.nextLine();
				SQL += "set titulo = " + titulo;
				break;
			case 2:
				System.out.println("ntroduce el id del director de la película");
				int director = sc.nextInt();
				SQL += "set director_id = " + director;
				break;
			case 3:
				System.out.println("Introduce el año de la película");
				int anio = sc.nextInt();
				SQL += "set anio = " + anio;
				break;
			case 4:
				System.out.println("Introduce el id del genero de la película");
				int genero = sc.nextInt();
				SQL += "set genero_id = " + genero;
				break;
			case 5:
				System.out.println("Introduce la valoración de la película");
				int valoracion = sc.nextInt();
				SQL += "set valoracion = " + valoracion;
				break;
			case 6:
				System.out.println("Introduce la duracion en minutos de la película");
				int duracion = sc.nextInt();
				SQL += "set titulo = " + duracion;
				break;
			case 7:
				System.out.println("Introduce el precio de la película");
				Double precio = sc.nextDouble();
				SQL += "set titulo = " + precio;
				break;
			}
			SQL += " Where id_pelicula = " + id;
			modificarPelicula.execute(SQL);
			modificarPelicula.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//Creo el metodo listar
		public static void listar(Connection conexion, Scanner sc) {
			try {
				Statement stmt = conexion.createStatement();
				stmt.execute("Select * From peliculas");
				ResultSet rs = stmt.getResultSet();
				while (rs.next() != false) {
					System.out.println("ID_ Pelicula: " + rs.getInt("id_pelicula") + ", Titulo: " + rs.getString("titulo") + ", ID_Director: " + rs.getInt("director_id") + ", Año: " + rs.getInt("anio") + ", ID_Genero: " + rs.getInt("genero_id") + ", Valoracion: " + rs.getInt("valoracion") + ", Precio: " + rs.getDouble("precio"));
				}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//Creo el metodo buscar
		public static void buscar(Connection conexion, Scanner sc) {
			int opcionModificacion = 0;
			try {
				String SQL = "Select * from peliculas where ";
				Statement buscarPelicula = conexion.createStatement();
				
				System.out.println("Introduzca la opción que desea buscar: ");
				System.out.println("----------------------------------------------------");
				System.out.println("1. Titulo");
				System.out.println("2. Director");
				System.out.println("3. Año");
				System.out.println("4. Genero");
				System.out.println("5. Valoracion");
				System.out.println("6. Duracion");
				System.out.println("7. Precio");
				System.out.println("----------------------------------------------------");
				switch(opcionModificacion) {
				case 1:
					System.out.println("Introduce el titulo de la película");
					sc.nextLine();
					String titulo = sc.nextLine();
					SQL += " titulo = " + titulo;
					break;
				case 2:
					System.out.println("ntroduce el id del director de la película");
					int director = sc.nextInt();
					SQL += " director_id = " + director;
					break;
				case 3:
					System.out.println("Introduce el año de la película");
					int anio = sc.nextInt();
					SQL += " anio = " + anio;
					break;
				case 4:
					System.out.println("Introduce el id del genero de la película");
					int genero = sc.nextInt();
					SQL += " genero_id = " + genero;
					break;
				case 5:
					System.out.println("Introduce la valoración de la película");
					int valoracion = sc.nextInt();
					SQL += " valoracion = " + valoracion;
					break;
				case 6:
					System.out.println("Introduce la duracion en minutos de la película");
					int duracion = sc.nextInt();
					SQL += " titulo = " + duracion;
					break;
				case 7:
					System.out.println("Introduce el precio de la película");
					Double precio = sc.nextDouble();
					SQL += " titulo = " + precio;
					break;
				}
				buscarPelicula.execute(SQL);
				ResultSet rs = buscarPelicula.getResultSet();
				while (rs.next() != false) {
					System.out.println("ID_ Pelicula: " + rs.getInt("id_pelicula") + ", Titulo: " + rs.getString("titulo") + ", ID_Director: " + rs.getInt("director_id") + ", Año: " + rs.getInt("anio") + ", ID_Genero: " + rs.getInt("genero_id") + ", Valoracion: " + rs.getInt("valoracion") + ", Precio: " + rs.getDouble("precio"));
				}
				rs.close();
				buscarPelicula.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//Creo el metodo peliculasMayores100
		public static void peliculasMayores100(Connection conexion, Scanner sc) {
			try {
				Statement stmt = conexion.createStatement();
				stmt.execute("Select * From peliculas where tiempo > 100");
				ResultSet rs = stmt.getResultSet();
				while (rs.next() != false) {
					System.out.println("ID_ Pelicula: " + rs.getInt("id_pelicula") + ", Titulo: " + rs.getString("titulo") + ", ID_Director: " + rs.getInt("director_id") + ", Año: " + rs.getInt("anio") + ", ID_Genero: " + rs.getInt("genero_id") + ", Valoracion: " + rs.getInt("valoracion") + ", Precio: " + rs.getDouble("precio"));
				}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//Creo el metodo peliculasAntes2000
		public static void peliculasAntes2000(Connection conexion, Scanner sc) {
			try {
				Statement stmt = conexion.createStatement();
				stmt.execute("Select * From peliculas where anio < 2000");
				ResultSet rs = stmt.getResultSet();
				while (rs.next() != false) {
					System.out.println("ID_ Pelicula: " + rs.getInt("id_pelicula") + ", Titulo: " + rs.getString("titulo") + ", ID_Director: " + rs.getInt("director_id") + ", Año: " + rs.getInt("anio") + ", ID_Genero: " + rs.getInt("genero_id") + ", Valoracion: " + rs.getInt("valoracion") + ", Precio: " + rs.getDouble("precio"));
				}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//Creo el metodo peliculasInferior10
		public static void peliculasInferior10(Connection conexion, Scanner sc) {
			try {
				Statement stmt = conexion.createStatement();
				stmt.execute("Select * From peliculas where precio < 10");
				ResultSet rs = stmt.getResultSet();
				while (rs.next() != false) {
					System.out.println("ID_ Pelicula: " + rs.getInt("id_pelicula") + ", Titulo: " + rs.getString("titulo") + ", ID_Director: " + rs.getInt("director_id") + ", Año: " + rs.getInt("anio") + ", ID_Genero: " + rs.getInt("genero_id") + ", Valoracion: " + rs.getInt("valoracion") + ", Precio: " + rs.getDouble("precio"));
				}
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}
