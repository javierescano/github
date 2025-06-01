//LUCAS DELGADO FERNÁNDEZ
package ejercicio1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class ConversorExamenAPP extends JFrame {

	//DECLARACION DE VARIABLES PARA LA PARTE GRAFICA DEL EJERCICIO
	private static JPanel panelTitulo;
	private static JPanel panelResultadoConversor;
	private static JPanel panelBotonesMonedasOrigen;
	private static JPanel panelBotonesMonedasDestino;
	private static JPanel panelCantidadBotonConvertir;
	private static JPanel panelCantidadCentral;
	private static JRadioButton botonMonedaOrigen;
	private static ButtonGroup grupoMonedaOrigen;
	private static JRadioButton botonMonedaDestino;
	private static ButtonGroup grupoMonedaDestino;
	private static JLabel tituloApp;
	private static JLabel resultadoConversion;
	private static JLabel cantidad;
	private static JLabel cantidadConversion;
	private static JButton botonEUR;
	private static JButton botonUSD;
	private static JButton botonBTC;
	private static JButton botonEURDestino;
	private static JButton botonUSDDestino;
	private static JButton botonBTCDestino;
	private static JButton botonConvertir;
	
	public static void main(String[] args) {
		
		ConversorExamenAPP examen = new ConversorExamenAPP();
		
	}
	
	public ConversorExamenAPP() {
		
		//VENTANA PRINCIPAL
		JFrame frame = new JFrame("Conversor de Monedas");
		frame.setSize(800, 600);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		//PANEL Y BOTONES DE LOS CAMBIOS DE MONEDA
		panelBotonesMonedasOrigen = new JPanel();
		panelBotonesMonedasDestino = new JPanel();
		
		botonMonedaOrigen = new JRadioButton();
		//grupoMonedaOrigen = new ButtonGroup();
		botonEUR = new JButton("EUR");
		botonUSD = new JButton("USD");
		botonBTC = new JButton("BTC");
		panelBotonesMonedasOrigen.add(botonEUR, BorderLayout.NORTH);
		panelBotonesMonedasOrigen.add(botonUSD, BorderLayout.CENTER);
		panelBotonesMonedasOrigen.add(botonBTC, BorderLayout.SOUTH);
		//panelBotonesMonedasOrigen.add(botonMonedaOrigen);
		panelBotonesMonedasOrigen.setVisible(true);
		
		botonMonedaDestino = new JRadioButton();
		//grupoMonedaDestino = new ButtonGroup();
		botonEURDestino = new JButton("EUR");
		botonUSDDestino = new JButton("USD");
		botonBTCDestino = new JButton("BTC");
		panelBotonesMonedasDestino.add(botonEURDestino, BorderLayout.NORTH);
		panelBotonesMonedasDestino.add(botonUSDDestino, BorderLayout.CENTER);
		panelBotonesMonedasDestino.add(botonBTCDestino, BorderLayout.SOUTH);
		//panelBotonesMonedasDestino.add(botonMonedaDestino);
		panelBotonesMonedasDestino.setVisible(true);
		
		//PANEL Y TITULO DEL CONVERSOR
		panelTitulo = new JPanel();
		panelTitulo.setSize(100, 600);
		tituloApp = new JLabel("CONVERSOR DE MONEDAS");
		tituloApp.setSize(30, 10);
		panelTitulo.add(tituloApp, BorderLayout.NORTH);
		panelTitulo.setVisible(true);
		
		//PANEL CENTRAL AMARILLO
		panelCantidadCentral = new JPanel();
		cantidadConversion= new JLabel("0.00");
		panelCantidadCentral.setBackground(Color.YELLOW);
		panelCantidadCentral.add(cantidadConversion, BorderLayout.CENTER);
		
		//RESULTADO DE LA CONVERSION DENTRO DEL PANEL CENTRAL
		panelResultadoConversor = new JPanel();
		resultadoConversion = new JLabel();
		panelResultadoConversor.setBackground(Color.YELLOW);
		panelResultadoConversor.add(resultadoConversion, BorderLayout.CENTER);
		panelResultadoConversor.add(panelCantidadCentral, BorderLayout.CENTER);
		
		panelCantidadBotonConvertir = new JPanel();
		panelCantidadBotonConvertir.setSize(200, 600);
		cantidad = new JLabel("Cantidad:");
		panelCantidadBotonConvertir.add(cantidad, BorderLayout.WEST);
		botonConvertir = new JButton("CONVERTIR");
		//ESTO ES PARA ASIGNAR EL METODO A LA ACCION QUE REALIZA EL BOTON DE "CONVERTIR"
		//botonConvertir.addActionListener(e -> );
		botonConvertir.setBackground(Color.GREEN);
		panelCantidadBotonConvertir.add(botonConvertir, BorderLayout.WEST);
		
		frame.add(panelBotonesMonedasOrigen, BorderLayout.WEST);
		frame.add(panelBotonesMonedasDestino, BorderLayout.EAST);
		frame.add(panelTitulo, BorderLayout.NORTH);
		frame.add(panelResultadoConversor, BorderLayout.CENTER);
		frame.add(panelCantidadBotonConvertir, BorderLayout.SOUTH);
		frame.setVisible(true);
		
	}
	
	//METODO QUE SIRVE PARA LA CREACION DEL ARCHIVO Converter.log Y QUE ESTE SEA RELLENADO CON TEXTO CON UN FILEWRITER
	private static void creadorAchivo () {
		
		File archivoConversiones = new File("src/Converter.log");
		
		try {
			
			if (archivoConversiones.createNewFile()) {
				
				try (FileWriter escritor = new FileWriter(archivoConversiones)) {
					
					escritor.write("Aqui se escribiran las conversiones que se han ido realizando");
					
					JOptionPane.showMessageDialog(null, "El archivo se ha creado correctamente");
					
				} catch (IOException e) {
					
					JOptionPane.showMessageDialog(null, "Error:" + e.getMessage());
					
				}	
				
			} else {
				
				JOptionPane.showMessageDialog(null, "El archivo ya existe");
				
			}
			
		} catch (HeadlessException e) {

			JOptionPane.showMessageDialog(null, "Error:" + e.getMessage());
			
		} catch (IOException e) {
			
			JOptionPane.showMessageDialog(null, "Error:" + e.getMessage());
			
		}
		
	}
	
	//METODO QUE LEE EL ARCHIVO CREADO CON ANETERIORIDAD SIN NECESIDAD DE TENER QUE IR A LA UBICACION A ABRIRLO
	private static void lecturaArchivo() {
		
		String rutaArchivo = "src/Converter.log";
		
		try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
			
			String linea;
			
			while ((linea = lector.readLine()) != null) {
				
				JOptionPane.showMessageDialog(null, linea);
				
			}
			
		} catch (FileNotFoundException e) {
			
			JOptionPane.showMessageDialog(null, "Error:" + e.getMessage());
			
		} catch (IOException e) {
			
			JOptionPane.showMessageDialog(null, "Error:" + e.getMessage());
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
