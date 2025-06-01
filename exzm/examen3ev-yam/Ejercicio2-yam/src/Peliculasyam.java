//Yago Arribas Miguel

import java.sql.*;
import java.util.Scanner;

public class Peliculasyam {

	public static void main(String[] args) {
		
		int opcion = 0;
		do {
			do {
			opcion = menu();
			} while (opcion >= 10 || opcion <= 0);
		
			try {
				String sql ;
				Scanner sc1 = new Scanner (System.in);
				
				Connection miConexion = DriverManager.getConnection("jdbc:sqlite:E:\\exam3ev_peliculas.db");
				
				
				// Inserta peliculas y los datos que no se introducen entre los parametros establecidos se ponen con unos datos estandars
				if (opcion == 1) {
					String titulo;
					int director_id, anio, genero_id, valoracion, duracion, precio;
					System.out.println("\nInserta el titulo de la pelicula");
					titulo = sc1.next();
					System.out.println("\nInserta el id de director");
					director_id = sc1.nextInt();
					System.out.println("\nInserta el año (antes de 2025)");
					anio = sc1.nextInt();
					if (anio >= 2025) {
						anio = 2024;
					}
					System.out.println("\nInserta el id del genero");
					genero_id = sc1.nextInt();
					System.out.println("\nInserta la valoracion (entre 1 y 5)");
					valoracion = sc1.nextInt();
					if (valoracion > 5 || valoracion < 1) {
						valoracion = 3;
					}
					System.out.println("\nInserta la duracion (en minutos, min 1 minuto)");
					duracion = sc1.nextInt();
					if (duracion < 1) {
						duracion = 1;
					}
					System.out.println("\nInserta el precio (min 1 euro)");
					precio = sc1.nextInt();
					if (precio < 1) {
						precio = 1;
					}
					sql = "INSERT INTO peliculas (titulo, director_id, anio, genero_id, valoracion, duracion, precio) VALUES (?,?,?,?,?,?,?)";
					PreparedStatement p = miConexion.prepareStatement(sql);
					
					p.setString(1, titulo);
					p.setInt(2, director_id);
					p.setInt(3, anio);
					p.setInt(4, genero_id);
					p.setInt(5, valoracion);
					p.setInt(6, duracion);
					p.setInt(7, precio);
					int c = p.executeUpdate();
					System.out.println("\nLinea Añadida: " + c);
					p.close();
					
					
				// elimina la pelicula pasada por id	
				} else if (opcion == 2) {
					int id_pelicula;
					System.out.println("\nInserta el id de la pelicula");
					id_pelicula = sc1.nextInt();
					sql = "DELETE FROM peliculas WHERE id_pelicula = " + id_pelicula ;
					Statement s = miConexion.createStatement();
					int c = s.executeUpdate(sql);
					System.out.println("\nLineas Afectadas: " + c);
					s.close();
					
					
				// Ver todas las Peliculas	
				} else if (opcion == 4) {
					sql = "SELECT * FROM peliculas";
					Statement s = miConexion.createStatement();
					ResultSet r = s.executeQuery(sql);
					while (r.next()) {
					System.out.println("\nTitulo: " +r.getString(2) + " id Director: " + r.getInt(3) + " año: " + r.getInt(4) + " id Genero: " + r.getInt(5) + " Valoración: " + r.getInt(6) + " Duración: " + r.getInt(7) + " Precio: " + r.getInt(8));
					}
					r.close();
					s.close();
					
					
				// Ver todas las peliculas que contenga la palabra Clave
				} else if (opcion == 5) {
					String palabraClave;
					System.out.println("\nInserta la palabra Clave");
					palabraClave = sc1.next();
					sql = "SELECT * FROM peliculas WHERE titulo LIKE '%" + palabraClave + "%'";
					Statement s = miConexion.createStatement();
					ResultSet r = s.executeQuery(sql);
					while (r.next()) {
					System.out.println("\nTitulo: " +r.getString(2) + " id Director: " + r.getInt(3) + " año: " + r.getInt(4) + " id Genero: " + r.getInt(5) + " Valoración: " + r.getInt(6) + " Duración: " + r.getInt(7) + " Precio: " + r.getInt(8));
					}
					r.close();
					s.close();
					
					
				// Ver todas las peliculas con una duracion mayor a 100
				} else if (opcion == 6) {
					sql = "SELECT * FROM peliculas WHERE duracion > 100";
					Statement s = miConexion.createStatement();
					ResultSet r = s.executeQuery(sql);
					while (r.next()) {
					System.out.println("\nTitulo: " +r.getString(2) + " id Director: " + r.getInt(3) + " año: " + r.getInt(4) + " id Genero: " + r.getInt(5) + " Valoración: " + r.getInt(6) + " Duración: " + r.getInt(7) + " Precio: " + r.getInt(8));
					}
					r.close();
					s.close();
					
					
				// Ver todas las peliculas anteriores a los 2000	
				} else if (opcion == 7) {
					sql = "SELECT * FROM peliculas WHERE anio < 2000";
					Statement s = miConexion.createStatement();
					ResultSet r = s.executeQuery(sql);
					while (r.next()) {
					System.out.println("\nTitulo: " +r.getString(2) + " id Director: " + r.getInt(3) + " año: " + r.getInt(4) + " id Genero: " + r.getInt(5) + " Valoración: " + r.getInt(6) + " Duración: " + r.getInt(7) + " Precio: " + r.getInt(8));
					}
					r.close();
					s.close();
					
					
				// Ver todas las peliculas anteriores a los 2000	
				} else if (opcion == 8) {
					sql = "SELECT * FROM peliculas WHERE precio < 10";
					Statement s = miConexion.createStatement();
					ResultSet r = s.executeQuery(sql);
					while (r.next()) {
					System.out.println("\nTitulo: " +r.getString(2) + " id Director: " + r.getInt(3) + " año: " + r.getInt(4) + " id Genero: " + r.getInt(5) + " Valoración: " + r.getInt(6) + " Duración: " + r.getInt(7) + " Precio: " + r.getInt(8));
					}
					r.close();
					s.close();
					
					
				// Cambiar los campos que quieres de la pelicula que quieres. Teniendo en cuenta todos los limites. Como la Opción 1.	
				} else if (opcion == 3) {
					int id;
					System.out.println("\nINTRODUCE EL ID DE LA PELICULA A MODIFICAR:");
					id = sc1.nextInt();	
					sql = "SELECT * FROM peliculas WHERE id_pelicula = " + id;
					Statement s = miConexion.createStatement();
					ResultSet r = s.executeQuery(sql);
					while (r.next()) {
					System.out.println("\nDATOS ACTUALES DE LA PELICULA");
					System.out.println("\nTitulo: " +r.getString(2) + " id Director: " + r.getInt(3) + " año: " + r.getInt(4) + " id Genero: " + r.getInt(5) + " Valoración: " + r.getInt(6) + " Duración: " + r.getInt(7) + " Precio: " + r.getInt(8));
					}
					int opciones = 0;
					do {
						System.out.println("\nELIGE UNA CARACTERISTICA A MODIFICAR:");
						System.out.println("\n1. Titulo \n2. id Director \n3. anio \n4. id genero \n5. valoraciones \n6. duracion \n7. precio");
						opciones = sc1.nextInt();
					} while (opciones >= 8 || opciones <= 0);
					if (opciones == 1) {
						String titulo;
						System.out.println("\nINTRODUCE El NUEVO TITULO:");
						titulo = sc1.next();
						sql = "UPDATE peliculas SET titulo = '" + titulo+ "' WHERE id_pelicula = " + id ;
						int c = s.executeUpdate(sql);
						System.out.println("\nLineas Afectadas: " + c);
						s.close();
					} if (opciones == 2) {
						int director;
						System.out.println("\nINTRODUCE El NUEVO ID DE DIRECTOR:");
						director = sc1.nextInt();
						sql = "UPDATE peliculas SET director_id = " + director + " WHERE id_pelicula = " + id ;
						int c = s.executeUpdate(sql);
						System.out.println("\nLineas Afectadas: " + c);
						s.close();
					} if (opciones == 3) {
						int anio;
						System.out.println("\nINTRODUCE El NUEVO AÑO (ANTES DE 2025):");
						anio = sc1.nextInt();
						if (anio >= 2025) {
							anio = 2024;
						}
						sql = "UPDATE peliculas SET anio = " + anio + " WHERE id_pelicula = " + id ;
						int c = s.executeUpdate(sql);
						System.out.println("\nLineas Afectadas: " + c);
						s.close();
					} if (opciones == 4) {
						int genero;
						System.out.println("\nINTRODUCE El NUEVO ID DE GENERO:");
						genero = sc1.nextInt();
						sql = "UPDATE peliculas SET genero_id = " + genero + " WHERE id_pelicula = " + id ;
						int c = s.executeUpdate(sql);
						System.out.println("\nLineas Afectadas: " + c);
						s.close();
					} if (opciones == 5) {
						int valoracion;
						System.out.println("\nINTRODUCE LA NUEVA VALORACIÓN (ENTRE 1 Y 5):");
						valoracion = sc1.nextInt();
						if (valoracion > 5 || valoracion < 1) {
							valoracion = 3;
						}
						sql = "UPDATE peliculas SET valoracion = " + valoracion + " WHERE id_pelicula = " + id ;
						int c = s.executeUpdate(sql);
						System.out.println("\nLineas Afectadas: " + c);
						s.close();
					} if (opciones == 6) {
						int duracion;
						System.out.println("\nINTRODUCE LA NUEVA DURACIÓN (MINIMO UN MINUTO):");
						duracion = sc1.nextInt();
						if (duracion < 1) {
							duracion = 1;
						}
						sql = "UPDATE peliculas SET duracion = " + duracion + " WHERE id_pelicula = " + id ;
						int c = s.executeUpdate(sql);
						System.out.println("\nLineas Afectadas: " + c);
						s.close();
					} if (opciones == 7) {
						int precio;
						System.out.println("\nINTRODUCE EN NUEVO PRECIO (MINIMO 1 EURO):");
						precio = sc1.nextInt();
						if (precio < 1) {
							precio = 1;
						}
						sql = "UPDATE peliculas SET precio = " + precio + " WHERE id_pelicula = " + id ;
						int c = s.executeUpdate(sql);
						System.out.println("\nLineas Afectadas: " + c);
						s.close();
					}
				} else if (opcion == 9) {
					for (int i = 1000000; i > 0; i--) {
						System.out.println("");
					}
				}
				miConexion.close();
				
			}catch (Exception e) {
				System.out.println("\nError de Conexion");
				e.printStackTrace();
			}
		} while (opcion != 9);

	}


	
	public static int menu () {
		//menu con las diferentes opciones
		int opcion = 0;
		try {
			Scanner sc = new Scanner (System.in);
			System.out.println("\nELIGE UNA OPCIÓN:");
			System.out.println("\n1. Alta de Película \n2. Baja de Película \n3. Modificar Pelicula \n4. Listar Películas \n5. Buscar Películas \n6. Mostrar Peliculas con una duración superior a los 100 minutos \n7. Mostrar Películas anteriores al año 2000 \n8. Mostrar Películas con un precio inferior a los 10,00€ \n9. Cerrar");
			opcion = sc.nextInt();
		} catch (Exception e) {
			System.out.println("\nError no es un Número");
			e.printStackTrace();
		}
		return opcion;
	}
	
}