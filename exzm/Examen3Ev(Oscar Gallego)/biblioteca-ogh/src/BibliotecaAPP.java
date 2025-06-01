import java.sql.*;
import java.util.Scanner;

import javax.xml.transform.Result;

public class BibliotecaAPP {
	
	public static String URL = "jbdc:sqlite:C:\\Users\\oscar.galher.1\\Documents\\WS-Java\\biblioteca-ogh\\exam3ev_peliculas.db";
	public static Connection connection;
	
	public static void main(String[] args) {
		try {
			connection = DriverManager.getConnection(URL);
			menuPrincipal();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void menuPrincipal() {
		int opcion = 0;
		Scanner sc = new Scanner(System.in);
		do {
			System.out.println("**MENU DE OPCIONES**" + "\n" +
		"--Opcion 8: Salir--" + "\n" +
		"Opcion 1: Alta de pelicula" + "\n" +
		"Opcion 2: Baja de pelicula" +  "\n" +
		"Opcion 3: Modificar pelicula" + "\n" +
		"Opcion 4: Listar peliculas");
			opcion = sc.nextInt();
			if(opcion == 1) {
				System.out.println("¿Que titulo tiene la pelicula?:");
				String titulo = sc.nextLine();
				System.out.println("ID del director:");
				int director = sc.nextInt();
				System.out.println("Anio pelicula:");
				int anio = sc.nextInt();
				System.out.println("ID del genero:");
				int genero_id = sc.nextInt();
				System.out.println("Valoracion pelicula:");
				int valoracion = sc.nextInt();
				System.out.println("Duracion pelicula:");
				int duracion = sc.nextInt();
				System.out.println("Precio pelicula:");
				int precio = sc.nextInt();
				altapelicula(titulo,director,anio,genero_id,valoracion,duracion,precio);
			}
			else if (opcion == 2) {
				System.out.println("Escriba el ID de la pelicula que desea eliminar:");
				int id_pelicula = sc.nextInt();
				bajaPelicula(id_pelicula);
			}
			else if(opcion == 3) {
				System.out.println("Escriba el nuevo anio de la pelicula:");
				int anio = sc.nextInt();
				System.out.println("Escriba el ID de la pelicula que desea modificar:");
				int id_pelicula = sc.nextInt();
				modificarPelicula(anio,id_pelicula);
			}
			else if(opcion == 4) {
				listarPeliculas();
			}
			else if(opcion == 8) {
				System.out.println("**Saliendo del programa...**");
			}
			
			
		}while(opcion != 8);
	}
	
	public static void altapelicula(String titulo,int director,int anio,int genero_id,int valoracion,int duracion,float precio) {
		
		String alta = "INSERT INTO peliculas (titulo, director_id, anio, genero_id, valoracion, duracion, precio) VALUES (?,?,?,?,?,?,?);";
		try (PreparedStatement ps = connection.prepareStatement(alta)){
			ps.setString(0, titulo);
			ps.setInt(1, director);
			ps.setInt(2, anio);
			ps.setInt(3, genero_id);
			ps.setInt(4, valoracion);
			ps.setInt(5, duracion);
			ps.setFloat(6, precio);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void bajaPelicula(int id_pelicula) {
		String baja = "DELETE FROM peliculas WHERE id_pelicula = ?;";
		try (PreparedStatement ps = connection.prepareStatement(baja)){
			ps.setInt(0, id_pelicula);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void modificarPelicula(int anio,int id_pelicula) {
		String modif = "UPDATE peliculas SET anio = ? WHERE id_pelicula = ?;";
		try (PreparedStatement ps = connection.prepareStatement(modif)){
			ps.setInt(0, anio);
			ps.setInt(0, id_pelicula);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void listarPeliculas() {
		String peliculas = "SELECT * FROM peliculas";
		Statement statement = connection.prepareStatement(URL);
		try(Result rs = statement.executeQuery(peliculas)){
			System.out.println(rs);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
