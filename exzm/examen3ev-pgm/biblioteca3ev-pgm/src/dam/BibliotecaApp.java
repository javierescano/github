package dam;
import java.sql.*;
import java.util.Scanner;
public class BibliotecaApp {

	// AUTOR: Petar G. Minov
	
	public static void main(String[] args) {
		
		String urlBaseDatos = "jdbc:sqlite:exam3ev_peliculas.db";
		Scanner sc = new Scanner(System.in);
		
		try {
			
			Connection conexion = DriverManager.getConnection(urlBaseDatos);
			
			mostrarMenu();
			int seleccion = sc.nextInt();
			
			switch (seleccion) {
			case 1:
				System.out.println("Elegido 1");
				break;
			case 2:
				boton2Eliminar(conexion, sc);
				break;
			case 3:
				// Sin implementar
				break;
			case 4:
				boton4Listar(conexion, sc);
				break;
			case 5:
				
				break;
			case 6:
				boton6ConsultaPersonalizar(conexion, sc);
				break;
				
			case 7:
				break;
			}
		
			// Cierro tanto el Scanner como la conexion al finalizar
			sc.close();
			conexion.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			
		}

	}
	
	
	
	public static void boton6ConsultaPersonalizar(Connection conexion, Scanner sc) {
		System.out.println("Por falta de tiempo, esta busqueda se hará de manera NO segura.");
		System.out.println("Introduce tu consulta completa a continuacion: ");
		sc.nextLine();
		String consulta = sc.nextLine();
		
		try {
			Statement st = conexion.createStatement();
			int resultado = st.executeUpdate(consulta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public static void boton2Eliminar(Connection conexion, Scanner sc) {
	
		int eleccion = mostrarSubmenu(sc);
		String sql = "";
		String consulta = "";
		
		switch (eleccion) {
		case 1:
			sql = "Introduce el ID del director a eliminar: ";
			consulta = "DELETE FROM directores WHERE id_director =";
			break;
		case 2:
			sql = "Introduce el ID del genero a eliminar: ";
			consulta = "DELETE FROM genero WHERE id_genero =";
			break;
		case 3:
			sql = "Introduce el ID de las peliculas a eliminar: ";
			consulta = "DELETE FROM peliculas WHERE id_pelicula =";
			break;
		}
		
		System.out.print(sql);
		int id = sc.nextInt();
		// Aqui uno el numero (ID) al registro que voy a eliminar
		consulta = consulta + id;
	
		try {
			conexion.setAutoCommit(false);
			Statement st = conexion.createStatement();
			int resultado = st.executeUpdate(consulta);
			if (resultado == 1) {
				System.out.println("Eliminado correctamente.");
			} else {
				System.out.println("Error en el borrado.");
			}
			conexion.setAutoCommit(true);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void boton4Listar (Connection conexion, Scanner sc) {
		
		// Este no me funciona
		int opcion = mostrarSubmenu(sc);
		String eleccion = "";
		
		switch (opcion) {
		case 1:
			eleccion = "directores";
			break;
		case 2:
			eleccion = "genero";		
			break;
		case 3:
			eleccion = "peliculas";
			break;
		}
		
		String sql = "Select * from "+ eleccion;
		try {
			Statement myStatement = conexion.createStatement();
			ResultSet rs = myStatement.executeQuery(sql);
			
			if (eleccion.equalsIgnoreCase("directores")) {
				
				while (rs.next()) {
					System.out.println("ID ||  Nombre  || Apellidos || Año de Nacimiento || Nacionalidad" );
					System.out.println(rs.getInt(1) + " ||  " + rs.getString(2) + " ||  " + rs.getString(3) + " ||  " + rs.getInt(4) + " ||  " + rs.getString(5));
					System.out.println("________" );
				}
				
				
			} else if (eleccion.equalsIgnoreCase("genero")) {
				
				while (rs.next()) {
					System.out.println("ID ||  Nombre " );
					System.out.println(rs.getInt(1) + "       " + rs.getString(2));
					
				}

				
			} else if (eleccion.equalsIgnoreCase("peliculas")){
				
				while (rs.next()) {
					System.out.println("ID ||  Titulo  || ID Director || Año || ID Genero || Valoracion || Duración || Precio" );
					System.out.println(rs.getInt(1) + " ||  " + rs.getString(2) + " ||  " + rs.getInt(3) + " ||  " + rs.getInt(4) + " ||  " + rs.getInt(5)  + " ||  " + rs.getInt(6)  + " ||  " + rs.getDouble(7));
					
				}
				
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void mostrarMenu () {
		
		System.out.println("BIENVENIDO A LA BIBLIOTECA DE PETAR G. MINOV.");
		System.out.println("__________________");
		System.out.println("1. Alta de datos");
		System.out.println("2. Baja de datos");
		System.out.println("3. Modificación de datos");
		System.out.println("4. Listar datos");
		System.out.println("5. Buscar datos");
		System.out.println("6. Mostrar datos (especificos)");
		System.out.println("7. SALIR DEL PROGRAMA");
		System.out.println("__________________");
		System.out.print("Seleccione una opción (número entero): ");
		
	}
	
	public static int mostrarSubmenu(Scanner sc) {
		
		System.out.println("Selecciona la tabla objetivo: ");
		System.out.println("1. Directores");
		System.out.println("2. Genero");
		System.out.println("3. Peliculas");
		System.out.println("__________________");
		System.out.print("Seleccione una opción (número entero): ");
		return sc.nextInt();

	}

}
