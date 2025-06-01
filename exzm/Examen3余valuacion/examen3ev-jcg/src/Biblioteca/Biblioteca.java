/**
 * Javier Cereceda Gómez
 */
package Biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.*;

public class Biblioteca {
	//Creamos variables para la base de datos
	
	//Director

	public static int id_director;
	public static String nombre;
	public static String apellidos;
	public static int anio_nacimiento ;
	//Genero
	public static int id_genero;
	//Peliculas
	public static int id_pelicula;
	public static String titulo;
	public static int anio;
	public static int genero_id;
	public static int valoracion;
	public static int duracion;
	public static double precio;

	public static void main(String[] args) {
		//Definimos una url y nos intentamos conectar si fallamos se cierra el programa
		//Ruta en el disco E necesidad de cambiarla para funcionar la base de Datos
		String URL = "jdbc:sqlite:E:\\Examen\\exam3ev_peliculas.db";
		try (Connection MiConexion = DriverManager.getConnection(URL)){
			System.out.println("Conexion Exitosa \n");
			//Desactivamos el AutoCommit para evitar inyecciones erroneas y asegurarnos manualmente de que la consulta vaya a estar bien
			MiConexion.setAutoCommit(false);
				String[] opciones = {"Alta de Pelicula","Baja de pelicula","Modificar Pelicula","Listar Pelicula","Buscar Peliculas",
						"Mostrar Peliculas con una duracion superior a 100 minutos","Mostrar Peliculas anteriores al 2000",
						"Mostrar Peliculas con un precio inferior a 10,00$","Salir"};
				Object opcion = null ;
				Scanner sc = new  Scanner(System.in);
				do {
					String Consulta = "";
					System.out.println("Bienvenido a la Biblioteca");
					System.out.println("--------------------------");
					System.out.println("Que quieres hacer ? \n");
					System.out.println("1)" + opciones[0]);
					System.out.println("2)" + opciones[1]);
					System.out.println("3)" + opciones[2]);
					System.out.println("4)" + opciones[3]);
					System.out.println("5)" + opciones[4]);
					System.out.println("6)" + opciones[5]);
					System.out.println("7)" + opciones[6]);
					System.out.println("8)" + opciones[7]);
					System.out.println("9)" + opciones[8]);
					opcion = sc.nextInt();
					//Entramos a la opciones para crear/actualizar o borrar tablas
					switch ((int)opcion) {
					case 1:
						try (Statement st = MiConexion.createStatement()){
							
					
							System.out.println("Dime el Titulo de la pelicula \nUtiliza Comillas dobles para escribir el titulo ");
							titulo = sc.next();
							
							System.out.println("Dime el Director de la pelicula");
							id_director = sc.nextInt();
							System.out.println("Dime el año de la pelicula");
							anio = sc.nextInt();
							System.out.println("Dime el Genero de la pelicula");
							genero_id = sc.nextInt();
							System.out.println("Dime La valoracion de la pelicula (1-5)");
							valoracion = sc.nextInt();
							System.out.println("Dime la duracion de la pelicula");
							duracion = sc.nextInt();
							System.out.println("Dime el precio de la pelicula");
							precio = sc.nextInt();
							
							Consulta = "Insert into peliculas ( titulo,director_id,anio,genero_id,valoracion,duracion,precio) values (" + titulo +","+id_director +","+anio +","
							+ genero_id +","+ valoracion +","+duracion+","+ precio+")";
							
							st.execute(Consulta);
							
							MiConexion.commit();
							System.out.println("Pelicula insertada en la base de datos");
							st.close();
							
							
						} catch (Exception e) {
							System.out.println("Error a la hora de la inserccion, devolviendo los cambios");
							System.out.println(e.getMessage() + "\n");
						}
						break;
					case 2:	
						
						try (Statement st = MiConexion.createStatement()){
							System.out.println("Introduce el id de la pelicula que quiera eliminar");
							id_pelicula = sc.nextInt();
							Consulta = "Delete From peliculas where id_pelicula =" + id_pelicula ;
							st.execute(Consulta);
							MiConexion.commit();
							System.out.println("Pelicula borrada");

							st.close();

						} catch (Exception e) {
							System.out.println("Error al eliminar pelicula, cambios deshaciendo los cambios");
							MiConexion.rollback();
						}
						break;
					case 3:
						try(Statement st = MiConexion.createStatement()) {
							System.out.println("Que pelicula quieres modificar (ID PELICULA)");
							id_pelicula = sc.nextInt();
							System.out.println("Que fecha quieres poner");
							anio = sc.nextInt();
							Consulta = "Update peliculas set anio =" + anio + " Where id_pelicula = " + id_pelicula;
							st.execute(Consulta);
							MiConexion.commit();
							System.out.println("Fecha de pelicula modificada");
							st.close();
						} catch (Exception e) {
							System.out.println("Error al actulizar la  pelicula, cambios deshaciendo los cambios");
							MiConexion.rollback();
						}
						break;
					case 4:
						//Iteramos sobre el Resulset para obtener los datos
						try (Statement st = MiConexion.createStatement()){
							Consulta = "Select * from peliculas";
							st.execute(Consulta);
							ResultSet rs = st.getResultSet();
							while (rs.next()) {
								System.out.println("ID:" + rs.getString("id_pelicula"));
								System.out.println("Titulo:" + rs.getString("titulo"));
								System.out.println("ID_Director: " + rs.getString("director_id"));
								System.out.println("anio:" + rs.getString("anio"));
								System.out.println("Genero:" + rs.getString("genero_id"));
								System.out.println("Valoracion: " + rs.getString("valoracion"));
								System.out.println("Duracion: " + rs.getString("duracion"));
								System.out.println("Precio: " + rs.getString("precio") + "$");
								System.out.println("\n");
							}
						st.close();
						rs.close();
						} catch (Exception e) {
							System.out.println("Error al obtener el resultado");
						}
						break;
					case 5:
						//Iteramos sobre el Resulset para obtener los datos

						try(Statement st = MiConexion.createStatement()) {
							System.out.println("Introduce el titulo de la pelicula que quieres buscar");
							System.out.println("No hacen falta comillas dobles");
							titulo = sc.next();
							Consulta = "Select * from peliculas where titulo LIKE '%"+titulo+"%'";
							st.execute(Consulta);
							ResultSet rs = st.getResultSet();
							while (rs.next()) {
								System.out.println("ID:" + rs.getString("id_pelicula"));
								System.out.println("Titulo:" + rs.getString("titulo"));
								System.out.println("ID_Director: " + rs.getString("director_id"));
								System.out.println("anio:" + rs.getString("anio"));
								System.out.println("Genero:" + rs.getString("genero_id"));
								System.out.println("Valoracion: " + rs.getString("valoracion"));
								System.out.println("Duracion: " + rs.getString("duracion"));
								System.out.println("Precio: " + rs.getString("precio") + "$");
								System.out.println("\n");
							}
							st.close();
							rs.close();	
							break;
						}catch (Exception e) {
							System.out.println("Error al obtener el resultado");
						}	
						break;
					case 6:	
						//Iteramos sobre el Resulset para obtener los datos

						try(Statement st = MiConexion.createStatement()) {
							System.out.println("Peliculas con duracion mayor a 100 minutos");
							Consulta = "Select * from peliculas where duracion > 100";
							st.execute(Consulta);
							ResultSet rs = st.getResultSet();
							while (rs.next()) {
								System.out.println("ID:" + rs.getString("id_pelicula"));
								System.out.println("Titulo:" + rs.getString("titulo"));
								System.out.println("ID_Director: " + rs.getString("director_id"));
								System.out.println("anio:" + rs.getString("anio"));
								System.out.println("Genero:" + rs.getString("genero_id"));
								System.out.println("Valoracion: " + rs.getString("valoracion"));
								System.out.println("Duracion: " + rs.getString("duracion"));
								System.out.println("Precio: " + rs.getString("precio") + "$");
								System.out.println("\n");
							}
							st.close();
							rs.close();	
							break;
						}catch (Exception e) {
							System.out.println("Error al obtener el resultado");
						}
						break;
					case 7:
						//Iteramos sobre el Resulset para obtener los datos

						System.out.println("\nPeliculas anteriores al Año 2000\n");
						try(Statement st = MiConexion.createStatement()) {
							Consulta = "Select * from peliculas where anio < 2000;";
							st.execute(Consulta);
							ResultSet rs = st.getResultSet();
							while (rs.next()) {
								System.out.println("ID:" + rs.getString("id_pelicula"));
								System.out.println("Titulo:" + rs.getString("titulo"));
								System.out.println("ID_Director: " + rs.getString("director_id"));
								System.out.println("anio:" + rs.getString("anio"));
								System.out.println("Genero:" + rs.getString("genero_id"));
								System.out.println("Valoracion: " + rs.getString("valoracion"));
								System.out.println("Duracion: " + rs.getString("duracion"));
								System.out.println("Precio: " + rs.getString("precio") + "$");
								System.out.println("\n");
							}
							st.close();
							rs.close();	
							break;
						}catch (Exception e) {
							System.out.println("Error al obtener el resultado");
						}
						break;
					case 8:	
						//Iteramos sobre el Resulset para obtener los datos

						System.out.println("Peliculas con un precio inferio a 10.00$");
						try(Statement st = MiConexion.createStatement()) {
							Consulta = "Select * from peliculas where precio < 10;";
							st.execute(Consulta);
							ResultSet rs = st.getResultSet();
							while (rs.next()) {
								System.out.println("ID:" + rs.getString("id_pelicula"));
								System.out.println("Titulo:" + rs.getString("titulo"));
								System.out.println("ID_Director: " + rs.getString("director_id"));
								System.out.println("anio:" + rs.getString("anio"));
								System.out.println("Genero:" + rs.getString("genero_id"));
								System.out.println("Valoracion: " + rs.getString("valoracion"));
								System.out.println("Duracion: " + rs.getString("duracion"));
								System.out.println("Precio: " + rs.getString("precio") + "$");
								System.out.println("\n");
							}
							st.close();
							rs.close();	
							break;
						}catch (Exception e) {
							System.out.println("Error al obtener el resultado");
						}
					case 9:	
						//Menu finalizacion Programa
						System.out.println(9);
						System.out.println("Seleccionaste Salir ADIOS");
						sc.close();
						MiConexion.close();
						break;		
					default:
						System.out.println("Opcion No valida");
						break;
					}
				} while ((int)opcion != 9);
				//Atrapamos errores y analizamos el tipo de error
		} catch (Exception e) {
			System.out.println("Ha habido problemas a la hora de conectar a la base de Datos");
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
		}
		finally {
			System.out.println("Finalizacion del Programa");
		}
		
	}



}
