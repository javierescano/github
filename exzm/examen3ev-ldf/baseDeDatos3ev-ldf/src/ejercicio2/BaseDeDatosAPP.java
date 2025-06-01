//LUCAS DELGADO FERNÁNDEZ
package ejercicio2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class BaseDeDatosAPP {

	private static final String NOMBRE_BASE_DE_DATOS = "jdbc:sqlite:C:\\Users\\lucas.delfer\\Desktop\\ECLIPSE\\SQLiteDatabaseBrowserPortable\\exam3ev_peliculas.db";
	private static int seleccionAPP;
	
	public static void main(String[] args) {
		
		BaseDeDatosAPP examen = new BaseDeDatosAPP();
		
		seleccionAPP = opcionesAplicacion();
		
		switch (seleccionAPP) {
		
			case 0: 
			
				altaDePelicula();
				
				break;
			
			case 1: 
				
				bajaDePelicula();
				
				break;
				
			case 2: 
				
				modificarPelicula();
				
				break;
				
			case 3: 
				
				listarPeliculas();
				
				break;
				
			case 4: 
				
				mostrarPeliculasConDuracionSuperior100Minutos();
				
				break;
				
			case 5: 
				
				mostrarPeliculasAnterioresAlAnio2000();
				
				break;
				
			case 6: 
				
				mostrarPeliculasConPrecioInferior10Euros();
				
				break;
				
			case 7: 
				
				buscarPeliculas();
				
				break;
			
			case 8: 
				
				//SALIR DEL PROGRAMA
				JOptionPane.showMessageDialog(null, "HAS SALIDO DEL PROGRAMA");
				
				break;
		
		}
		
	}
	
	private static void altaDePelicula() {
		
		try (Connection miConexion =  DriverManager.getConnection(NOMBRE_BASE_DE_DATOS);
				Statement miStatement = miConexion.createStatement()) {
			
			//PEDIMOS AL USUARIO TODAS LOS ATRIBUTOS DE LA TABLA peliculas QUE DESEA INTRODUCIR
			String titulo = JOptionPane.showInputDialog("Introduce el titulo");
			String id_directorString = JOptionPane.showInputDialog("Introduce el id_director");
			int id_directorINT = Integer.parseInt(id_directorString);
			String anioString = JOptionPane.showInputDialog("Introduce el año");
			int anioINT = Integer.parseInt(anioString);
			String genero_idString = JOptionPane.showInputDialog("Introduce el genero_id");
			int genero_idINT = Integer.parseInt(genero_idString);
			String valoracionString = JOptionPane.showInputDialog("Introduce el valoracion");
			int valoracionINT = Integer.parseInt(valoracionString);
			String duracionString = JOptionPane.showInputDialog("Introduce el duracion");
			int duracionINT = Integer.parseInt(duracionString);
			String precioString = JOptionPane.showInputDialog("Introduce el precio");
			int precioINT = Integer.parseInt(precioString);
			
			String sentencia = "INSERT INTO peliculas (titulo, director_id, anio, genero_id, valoracion, duracion, precio) VALUES ("
					+ "'" + titulo + "'," + id_directorINT + "," + anioINT + "," + genero_idINT + "," + valoracionINT + "," + duracionINT + "," + precioINT + ")";
			
			int filas = miStatement.executeUpdate(sentencia);
			JOptionPane.showMessageDialog(null, "Numero de Filas actualizadas: " + filas);
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			
		}	
		
	}
	
	private static void bajaDePelicula () {
		
		try (Connection miConexion =  DriverManager.getConnection(NOMBRE_BASE_DE_DATOS);
				Statement miStatement = miConexion.createStatement()) {
			
			//PREGUNTAMOS AL USUARIO QUE id_pelicula DESEA ELIMINAR DE LA BASE DE DATOS
			String id_peliculaString = JOptionPane.showInputDialog("Introduce el id_pelicula que quieres eliminar");
			int id_peliculaINT = Integer.parseInt(id_peliculaString);
			
			
			String sentencia = "DELETE FROM peliculas WHERE id_pelicula = " + id_peliculaINT;
			
			int filas = miStatement.executeUpdate(sentencia);
			JOptionPane.showMessageDialog(null, "Numero de Filas actualizadas: " + filas);
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			
		}	
		
	}
	
	private static void modificarPelicula () {
		
		try (Connection miConexion =  DriverManager.getConnection(NOMBRE_BASE_DE_DATOS);
				Statement miStatement = miConexion.createStatement()) {
			
			//PREGUNTAMOS AL USUARIO QUE id_pelicula DESEA CAMBIAR EL AÑO
			String id_peliculaString = JOptionPane.showInputDialog("Introduce el id_pelicula que quieres cambiar el año");
			int id_peliculaINT = Integer.parseInt(id_peliculaString);
			String anioString = JOptionPane.showInputDialog("Introduce el año que quieres al que quieres cambiar");
			int anioINT = Integer.parseInt(anioString);
			
			String sentencia = "UPDATE peliculas SET anio = " + anioINT + " WHERE id_pelicula = " + id_peliculaINT;
			
			int filas = miStatement.executeUpdate(sentencia);
			JOptionPane.showMessageDialog(null, "Numero de Filas actualizadas: " + filas);
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			
		}	
		
	}
	
	//ESTE METODO LO MUESTRO EL RESULTADO POR CONSOLA PARA QUE SE VEA MAS CLARO
	private static void listarPeliculas () {
		
		String sentencia = "SELECT * FROM peliculas";
		
		try (Connection miConexion =  DriverManager.getConnection(NOMBRE_BASE_DE_DATOS);
				Statement miStatement = miConexion.createStatement();
				ResultSet rs = miStatement.executeQuery(sentencia)) {
			
			//MUESTRA TODOS LOS ATRIBUTOS DE TODAS LAS peliculas HASTA QUE ACABE DE MOSTRAR TODAS ELLAS
			while (rs.next()) {
				System.out.println("ID_PELICULA: " + rs.getInt("id_pelicula"));
				System.out.println("TITULO: " + rs.getString("titulo"));
				System.out.println("DIRECTOR_ID: " + rs.getInt("director_id"));
				System.out.println("AÑO: " + rs.getInt("anio"));
				System.out.println("GENERO_ID: " + rs.getInt("genero_id"));
				System.out.println("VALORACION: " + rs.getInt("valoracion"));
				System.out.println("DURACION: " + rs.getInt("duracion"));
				System.out.println("PRECIO: " + rs.getInt("precio"));
				System.out.println("-------------------------------------");
			}
			
		
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			
		}	
		
	}
	
	//ESTE METODO LO MUESTRO EL RESULTADO POR CONSOLA PARA QUE SE VEA MAS CLARO
	private static void mostrarPeliculasConDuracionSuperior100Minutos () {
		
		String sentencia = "SELECT * FROM peliculas WHERE duracion = 100";
		
		try (Connection miConexion =  DriverManager.getConnection(NOMBRE_BASE_DE_DATOS);
				Statement miStatement = miConexion.createStatement();
				ResultSet rs = miStatement.executeQuery(sentencia)) {
			
			//MUESTRA TODOS LOS ATRIBUTOS DE TODAS LAS peliculas HASTA QUE ACABE DE MOSTRAR TODAS ELLAS TENIENDO EN CUENTA QUE LA DURACION ES MENOR DE 100 MINUTOS
			while (rs.next()) {
				System.out.println("ID_PELICULA: " + rs.getInt("id_pelicula"));
				System.out.println("TITULO: " + rs.getString("titulo"));
				System.out.println("DIRECTOR_ID: " + rs.getInt("director_id"));
				System.out.println("AÑO: " + rs.getInt("anio"));
				System.out.println("GENERO_ID: " + rs.getInt("genero_id"));
				System.out.println("VALORACION: " + rs.getInt("valoracion"));
				System.out.println("DURACION: " + rs.getInt("duracion"));
				System.out.println("PRECIO: " + rs.getInt("precio"));
				System.out.println("-------------------------------------");
			}
			
		
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			
		}	
		
	}
	
	//ESTE METODO LO MUESTRO EL RESULTADO POR CONSOLA PARA QUE SE VEA MAS CLARO
	private static void mostrarPeliculasAnterioresAlAnio2000 () {
		
		String sentencia = "SELECT * FROM peliculas WHERE anio < 2000";
		
		try (Connection miConexion =  DriverManager.getConnection(NOMBRE_BASE_DE_DATOS);
				Statement miStatement = miConexion.createStatement();
				ResultSet rs = miStatement.executeQuery(sentencia)) {
			
			//MUESTRA TODOS LOS ATRIBUTOS DE TODAS LAS peliculas HASTA QUE ACABE DE MOSTRAR TODAS ELLAS TENIENDO EN CUENTA QUE EL AÑO DEBE SER INFERIOR A 2000
			while (rs.next()) {
				System.out.println("ID_PELICULA: " + rs.getInt("id_pelicula"));
				System.out.println("TITULO: " + rs.getString("titulo"));
				System.out.println("DIRECTOR_ID: " + rs.getInt("director_id"));
				System.out.println("AÑO: " + rs.getInt("anio"));
				System.out.println("GENERO_ID: " + rs.getInt("genero_id"));
				System.out.println("VALORACION: " + rs.getInt("valoracion"));
				System.out.println("DURACION: " + rs.getInt("duracion"));
				System.out.println("PRECIO: " + rs.getInt("precio"));
				System.out.println("-------------------------------------");
			}
			
		
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			
		}	
		
	}
	
	//ESTE METODO LO MUESTRO EL RESULTADO POR CONSOLA PARA QUE SE VEA MAS CLARO
	private static void mostrarPeliculasConPrecioInferior10Euros () {
		
		String sentencia = "SELECT * FROM peliculas WHERE precio < 10";
		
		try (Connection miConexion =  DriverManager.getConnection(NOMBRE_BASE_DE_DATOS);
				Statement miStatement = miConexion.createStatement();
				ResultSet rs = miStatement.executeQuery(sentencia)) {
			
			//MUESTRA TODOS LOS ATRIBUTOS DE TODAS LAS peliculas HASTA QUE ACABE DE MOSTRAR TODAS ELLAS TENIENDO EN CUENTA QUE EL PRECIO DEB SER MENOR DE 10 EUROS
			while (rs.next()) {
				System.out.println("ID_PELICULA: " + rs.getInt("id_pelicula"));
				System.out.println("TITULO: " + rs.getString("titulo"));
				System.out.println("DIRECTOR_ID: " + rs.getInt("director_id"));
				System.out.println("AÑO: " + rs.getInt("anio"));
				System.out.println("GENERO_ID: " + rs.getInt("genero_id"));
				System.out.println("VALORACION: " + rs.getInt("valoracion"));
				System.out.println("DURACION: " + rs.getInt("duracion"));
				System.out.println("PRECIO: " + rs.getInt("precio"));
				System.out.println("-------------------------------------");
			}
			
		
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			
		}	
		
	}
	
	//ESTE METODO LO MUESTRO EL RESULTADO POR CONSOLA PARA QUE SE VEA MAS CLARO
	private static void buscarPeliculas () {
		
		String palabraClave = JOptionPane.showInputDialog("Introduce la palabra o letras clave del titulo de la pelicula");
		
		String sentencia = "SELECT * FROM peliculas WHERE titulo LIKE '%" + palabraClave + "%'";
		
		try (Connection miConexion =  DriverManager.getConnection(NOMBRE_BASE_DE_DATOS);
				Statement miStatement = miConexion.createStatement();
				ResultSet rs = miStatement.executeQuery(sentencia)) {
			
			//MUESTRA TODOS LOS ATRIBUTOS DE TODAS LAS peliculas HASTA QUE ACABE DE MOSTRAR TODAS ELLAS QUE TENGAN PARTE DE LA PLABARA CLAVE QUE EL USUARIO HA PROPORCIONADO
			while (rs.next()) {
				System.out.println("ID_PELICULA: " + rs.getInt("id_pelicula"));
				System.out.println("TITULO: " + rs.getString("titulo"));
				System.out.println("DIRECTOR_ID: " + rs.getInt("director_id"));
				System.out.println("AÑO: " + rs.getInt("anio"));
				System.out.println("GENERO_ID: " + rs.getInt("genero_id"));
				System.out.println("VALORACION: " + rs.getInt("valoracion"));
				System.out.println("DURACION: " + rs.getInt("duracion"));
				System.out.println("PRECIO: " + rs.getInt("precio"));
				System.out.println("-------------------------------------");
			}
			
		
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, e.getMessage());
			
		}	
		
	}
	
	private static int opcionesAplicacion() {
		
		String[] opcionesAplicacion = { "Dar Alta", "Dar Baja", "Modificar", "Listar", 
				"Buscar", "menor de 100 minutos",
				"año menor de 2000", "menor de 10 euros", "SALIR" };
		
		int opciones = JOptionPane.showOptionDialog(null, "MENU DEL PROGRAMA", "Selecciona que opciones deseas hacer", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcionesAplicacion, "");
		
		return opciones;
		
	}
	
	
	

}
