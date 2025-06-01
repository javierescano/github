package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Ejercicio2 {

	public static void main(String[] args) {

		mostrarMenu();
		
		
		
		
		
	}

	public static void mostrarMenu() {
		Scanner sc = new Scanner(System.in);
	System.out.println("Introduzca la opcion que desea realizar:\n");
		
	System.out.println("1.AltaPelicula");
	System.out.println("2.BajaPelicula");
	System.out.println("3.ModificarPelicula");
	System.out.println("4.ListarPelicula");
	System.out.println("5.BuscarPelicula");
	System.out.println("6.MostrarPelicula");
	System.out.println("7.MostrarPeliculas+100min");
	System.out.println("8.Peliculas<2000");
	System.out.println("9.Peliculas<10.00");
	
	System.out.println("Introduzca la opcion que desea realizar:\n");
	int opcionRealizar = sc.nextInt();
	
		
		switch(opcionRealizar) {
		
			case 1:
				altaPelicula();
			
			break;	
			
	
			case 2:
				bajaPelicula();
			
			
			break;
			
			case 3:
				
				modificarPelicula();
			break;
			
			case 4:
				listarPelicula();
			break;
			case 5:
				buscarPeliculas();
				
			break;
			
			case 6:
				mostrarPeliculasDuracion();
				
				break;
				
			case 7:
				mostrarPeliculasAnteriores();
				
			case 8:
				mostrarPeliculasPrecio();

		}
		
		
		
		

		
		
	}
	
	public static void altaPelicula() {
		Scanner sc = new Scanner(System.in);
		try {
			
		Connection miConexion = DriverManager.getConnection("jdbc:sqlite:E:\\exam3ev_peliculas.db");
		Statement miStatement = miConexion.createStatement();
		
	//	System.out.println("Introduce el titulo");
		
		System.out.println("Introduce el titulo");
		String  titulo = sc.next();
		System.out.println("Introduce el director_id");
		int director_id = sc.nextInt();
		System.out.println("Introduce el anio");
		int anio = sc.nextInt();
		System.out.println("Introduce el genero_id");
		int genero_id = sc.nextInt();
		System.out.println("Introduce el valoracion");
		int valoracion = sc.nextInt();
		System.out.println("Introduce el duracion");
		int duracion = sc.nextInt();
		System.out.println("Introduce el precio");
		int precio = sc.nextInt();
	
		
		
	
		
		
		String SQl = "INSERT INTO peliculas (titulo,director_id,anio,genero_id,valoracion,duracion,precio) VALUES ("+titulo+","+director_id+","+
		anio+","+genero_id+","+valoracion+","+duracion+","+precio+");";
		
		PreparedStatement miPreparedStatament = miConexion.prepareStatement(SQl);
		
		
	

		
		
		miPreparedStatament.executeUpdate();
		
		miConexion.close();
		miStatement.close();
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	
	
	public static void bajaPelicula() {
		
		Scanner sc = new Scanner(System.in);
		try {
			
		Connection miConexion = DriverManager.getConnection("jdbc:sqlite:E:\\exam3ev_peliculas.db");
		Statement miStatement = miConexion.createStatement();
		
	
		
		System.out.println("Introduce el id");
		int id = sc.nextInt();
	
	
		
		
		String SQl = "DELETE FROM peliculas WHERE id_pelicula ="+id+";";
		
		PreparedStatement miPreparedStatament = miConexion.prepareStatement(SQl);
		
		miPreparedStatament.setInt(1, id);
		
		
		miPreparedStatament.executeUpdate();
		
		
		miConexion.close();
		miStatement.close();
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
	
		
	}
	
	
	
	public static void modificarPelicula() {
		
		Scanner sc = new Scanner(System.in);
		try {
			
		Connection miConexion = DriverManager.getConnection("jdbc:sqlite:E:\\exam3ev_peliculas.db");
		Statement miStatement = miConexion.createStatement();
		
	
		
		System.out.println("Modifica los datos apartir del id");
		int id = sc.nextInt();
	
		System.out.println("Modifica los datos apartir del año");
		int anio = sc.nextInt();
		
		
		String SQl = "UPDATE peliculas SET ="+ anio + " WHERE id_pelicula ="+id+";";
		
		PreparedStatement miPreparedStatament = miConexion.prepareStatement(SQl);
		
	
		
		
		miPreparedStatament.executeUpdate();
		
		
		miConexion.close();
		miStatement.close();
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
	
		
	}
		
	public static void listarPelicula() {
		
		try {
			
			Connection miConexion = DriverManager.getConnection("jdbc:sqlite:E:\\exam3ev_peliculas.db");
			
			Statement miStatement = miConexion.createStatement();
		


		
			String SQl = "SELECT * FROM peliculas";
			
			
			
			ResultSet rs = miStatement.executeQuery(SQl);
			
			
			
			
			miConexion.close();
			miStatement.close();
			
			}catch(Exception e) {
			e.printStackTrace();
			
			}
		
		
	
	
	}
	public static void  buscarPeliculas() {
		Scanner sc = new Scanner(System.in);
		try {
			
			Connection miConexion = DriverManager.getConnection("jdbc:sqlite:E:\\exam3ev_peliculas.db");
			
			Statement miStatement = miConexion.createStatement();
			System.out.println("Introduce la palabra para la busqueda");
			String palabra = sc.next();
			
			String SQl = "SELECT * FROM peliculas WHERE titulo LIKE '%"+palabra+"%'"+";";
			
			
			
			PreparedStatement miPreparedStatament = miConexion.prepareStatement(SQl);
			
			

			
			
			miConexion.close();
			miStatement.close();
			
			}catch(Exception e) {
			e.printStackTrace();
			
			}
		
		
	}
	
	public static void mostrarPeliculasDuracion() {
		Scanner sc = new Scanner(System.in);
		try {
			
			Connection miConexion = DriverManager.getConnection("jdbc:sqlite:E:\\exam3ev_peliculas.db");
			
			Statement miStatement = miConexion.createStatement();
			System.out.println("Introduce la palabra para la busqueda");
			String palabra = sc.next();
			
			String SQl = "SELECT * FROM peliculas WHERE duracion >100"+";";
			
			
			
			PreparedStatement miPreparedStatament = miConexion.prepareStatement(SQl);
			
			

			
			
			miConexion.close();
			miStatement.close();
			
			}catch(Exception e) {
			e.printStackTrace();
			
			}
		
		
		
		
	}
	
	public static void mostrarPeliculasAnteriores() {
		Scanner sc = new Scanner(System.in);
		try {
			
			Connection miConexion = DriverManager.getConnection("jdbc:sqlite:E:\\exam3ev_peliculas.db");
			
			Statement miStatement = miConexion.createStatement();
			System.out.println("Introduce la palabra para la busqueda");
			String palabra = sc.next();
			
			String SQl = "SELECT * FROM peliculas WHERE anio <2000"+";";
			
			
			
			PreparedStatement miPreparedStatament = miConexion.prepareStatement(SQl);
			
			

			
			
			miConexion.close();
			miStatement.close();
			
			}catch(Exception e) {
			e.printStackTrace();
			
			}
		
		
		
		
	}
	
	public static void mostrarPeliculasPrecio() {
		Scanner sc = new Scanner(System.in);
		try {
			
			Connection miConexion = DriverManager.getConnection("jdbc:sqlite:E:\\exam3ev_peliculas.db");
			
			Statement miStatement = miConexion.createStatement();
			System.out.println("Introduce la palabra para la busqueda");
			String palabra = sc.next();
			
			String SQl = "SELECT * FROM peliculas WHERE precio <10.00"+";";
			
			
			
			PreparedStatement miPreparedStatament = miConexion.prepareStatement(SQl);
			
			

			
			
			miConexion.close();
			miStatement.close();
			
			}catch(Exception e) {
			e.printStackTrace();
			
			}
		
		
		
	}
}

