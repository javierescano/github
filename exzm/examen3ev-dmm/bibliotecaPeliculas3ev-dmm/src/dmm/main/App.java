//DARÍO MUNÁRRIZ MIGUEL

package dmm.main;

import java.sql.*;

import javax.swing.JOptionPane;

import dmm.utilidades.Metodos;

public class App {

	public static final String URL_BASE_DATOS = "jdbc:sqlite:F:\\exam3ev_peliculas.db";
	public static final int DAR_ALTA = 0;
	public static final int DAR_BAJA = 1;
	public static final int MODIFICAR = 2;
	public static final int LISTAR = 3;
	public static final int BUSCAR = 4;
	public static final int MOSTRAR_DURACION_MAYOR_100 = 5;
	public static final int MOSTRAR_ANTERIORES_2000 = 6;
	public static final int MOSTRAR_PRECIO_INFERIOR_10 = 7;
	
	
	public static void main(String[] args) {
		
		try {
			
			Connection con = DriverManager.getConnection(URL_BASE_DATOS);
			Statement st = con.createStatement();
			
			
			int eleccion;
			do {
			eleccion = Metodos.menu();
			if (eleccion == DAR_ALTA) {
				Metodos.darAlta(st);
			} else if (eleccion == DAR_BAJA) {
				Metodos.darBaja(st);
			} else if (eleccion == MODIFICAR) {
				Metodos.modificarPelicula(st);
			} else if (eleccion == LISTAR) {
				Metodos.listarPeliculas(st);
			} else if (eleccion == BUSCAR) {
				Metodos.buscarPelicula(st);
			} else if (eleccion == MOSTRAR_DURACION_MAYOR_100) {
				Metodos.mostrarPeliculasDuracion100(st);
			} else if (eleccion == MOSTRAR_ANTERIORES_2000) {
				Metodos.mostrarPeliculasAnteriores2000(st);
			} else if (eleccion == MOSTRAR_PRECIO_INFERIOR_10) {
				Metodos.mostrarPeliculasPrecioInferior10(st);
			}
			
			
			} while (eleccion != JOptionPane.CLOSED_OPTION);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
