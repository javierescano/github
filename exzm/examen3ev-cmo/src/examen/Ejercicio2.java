//Carlos Martín Otero

//La verdad me hubiese gustado poder ir ejecutandolo para ir probandolo... pero con los errores esos que no sabiamos mucho la envio un poco sin saber si la piscina esta llena o vacia...


package examen;


import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Ejercicio2 {
	
	static final int INSERTAR_PELICULA = 1;
	static final int BORRAR_PELICULA = 2;
	static final int MODIFICAR_PELICULA = 3;
	static final int LISTAR_PELICULA = 4;
	static final int BUSCAR_PELICULA = 5;
	static final int MOSTRAR_PELICULA = 6;
	static final int MOSTRAR_ANTES_2000 = 7;
	static final int MOSTRAR_PRECIO_MENOR_10 = 8;
	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
	int resultado;
		String ruta = "jdbc:sqlite:C:\\Users\\carlos.marote\\Desktop\\exam3ev_peliculas.db";
		int ronda = 0;
		try {
			
			//conectar la base de datos
			Connection conex = DriverManager.getConnection(ruta);
			System.out.println("Conectado");
			
		//	Statement st = (Statement) conex.createStatement();
			
			ResultSet rs;
			
			do{
				System.out.println("¿Que quieres hacer?");
				System.out.println("-----------------------");
				System.out.println("1) INSERTAR PELICULA");
				System.out.println("2) DAR BAJA PELICULA");
				System.out.println("3) MODIFICAR PELICULA");
				System.out.println("4) LISTAR PELICULAS");
				System.out.println("5) BUSCAR PELICULA");
				System.out.println("6) MOSTRAR PELICULAS");
				System.out.println("7) MOSTRAR PELICULAS ANTERIORES AL 2000");
				System.out.println("8) MOSTRAR PELICULAS PRECIO INFERIOR A 10€");
				System.out.println("0) SALIR");
				
				 resultado = scanner.nextInt();
				
				
				if(resultado == INSERTAR_PELICULA) {
					
					System.out.println("titulo");
					String titulo = scanner.nextLine();
					
					System.out.println("anho");					
					int anho = scanner.nextInt();
					
					System.out.println("valoracion");				
					double valoracion = scanner.nextDouble();
					
					System.out.println("duracion");					
					double duracion = scanner.nextDouble();
					
					System.out.println("precio");
					double precio = scanner.nextDouble();

					st.execute("INSERT INTO PELICULAS (titulo, anio, valoracion, duracion, precio "
							+ "values ('"+ titulo +"', '"+ anho +"', '" + valoracion + "', '" + valoracion + "', '" + precio + "';)");
					
					
					
				}else if(resultado == BORRAR_PELICULA) {
					
						System.out.println("¿Qué pelicula quieres borrar?");
						int borrar = scanner.nextInt();
						
						st.execute("delete from peliculas where id_pelicula = " + borrar  + ";");
						
				}else if(resultado == MODIFICAR_PELICULA) {
					
					System.out.println("¿Qué pelicula quieres modificar su año? Introduzca ID");
					int id_pelicula = scanner.nextInt();
					
					System.out.println("¿Qué pelicula quieres modificar su año? Introduzca ID");
					int ano_pelicula = scanner.nextInt();
					
					st.execute("update peliculas set anio = " + ano_pelicula  + " WHERE id_pelicula = " + id_pelicula + ";");
					
							
					
				}else if(resultado == LISTAR_PELICULA) {
					
					rs.executeQuery("select * from peliculas");
					while(rs.next()) {
						rs.getInt("id_pelicula");
						rs.getString("titulo");
						rs.getInt("director_id");
						rs.getInt("anio");
						rs.getInt("genero_id");
						rs.getInt("valoracion");
						rs.getInt("duracion");
						rs.getInt("precio");
						
					}
					
					System.out.println();
				}else if(resultado == BUSCAR_PELICULA) {
					
					
					System.out.println("Escoge una parte del titulo");
					String titulo = scanner.nextLine();
					
					rs.executeQuery("select * from peliculas where titulo like '% " + titulo +"%';");
					
					while(rs.next()) {
						rs.getString("titulo");
					}
					
				}else if(resultado == MOSTRAR_PELICULA) {
					rs.executeQuery("select * from peliculas where duracion > 100 ;");
					while(rs.next()) {
						rs.getString("titulo");
						rs.getInt("duracion");
					}
					
				}else if(resultado == MOSTRAR_ANTES_2000) {
					rs.executeQuery("select * from peliculas where anio > 2000 ;");
					while(rs.next()) {
						rs.getString("titulo");
						rs.getInt("anio");
					}
					
				}else if(resultado == MOSTRAR_PRECIO_MENOR_10) {
					rs.executeQuery("select * from peliculas where precio > 10 ;");
					while(rs.next()) {
						rs.getString("titulo");
						rs.getInt("precio");
					}
				}
				
			}while(resultado != 0);
			
			System.err.println("Cerrando sesion....");
			

			
		}catch(SQLException e) {
			System.out.println("ERROR");
		}

	}

}
