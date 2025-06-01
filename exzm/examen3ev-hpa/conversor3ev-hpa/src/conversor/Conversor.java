package conversor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Conversor extends JFrame{
	
	double euroCambioDolar =  1.08, euroCambioBTC = 0.000016, USDCambioEuro = 0.92,
			USDCambioBTC = 0.000015, BTCCambioEuro = 62100, BTCCambioUSD = 67500, convertidor = 0.00, cantidadInicial;
	String euroDestinoMoneda = "EUR", usdDestinoMoneda = "USD", btcDestinoMoneda = "BTC", euroOrigenMoneda = "euros", usdOrigenMoneda = "dólares", btcOrigenMoneda = "bitcoins";
	
	
	public Conversor() {
		
		setTitle("Conversor de monedas");
		setSize(700, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setLayout(new BorderLayout());
		
		JPanel arriba = new JPanel();
		JPanel izq = new JPanel();
		JPanel centro = new JPanel();
		JPanel der = new JPanel();
		JPanel abajo = new JPanel();
		
		//Primero la parte de arriba
		JLabel titulo = new JLabel();
		titulo.setText("CONVERSOR DE MONEDAS");
		arriba.add(titulo,CENTER_ALIGNMENT);
		add(arriba,BorderLayout.NORTH);
		
		//Continuo con la parte izquierda
		JRadioButton euroOrigen = new JRadioButton("EUR");
		JRadioButton usdOrigen = new JRadioButton("USD");
		JRadioButton btcOrigen = new JRadioButton("BTC");
		JLabel origenTitulo = new JLabel("MONEDA ORIGEN");
		izq.setLayout(new BoxLayout(izq, BoxLayout.Y_AXIS));
		
		ButtonGroup monedaOrigen = new ButtonGroup();
		monedaOrigen.add(euroOrigen);
		monedaOrigen.add(usdOrigen);
		monedaOrigen.add(btcOrigen);
		izq.add(origenTitulo);
		izq.add(euroOrigen);
		izq.add(usdOrigen);
		izq.add(btcOrigen);
		add(izq,BorderLayout.WEST);
		
		//La parte derecha
		JRadioButton euroDestino = new JRadioButton("EUR");
		JRadioButton usdDestino = new JRadioButton("USD");
		JRadioButton btcDestino = new JRadioButton("BTC");
		JLabel destinoTitulo = new JLabel("MONEDA DESTINO");
		der.setLayout(new BoxLayout(der, BoxLayout.Y_AXIS));
		
		ButtonGroup monedaDestino = new ButtonGroup();
		monedaDestino.add(euroDestino);
		monedaDestino.add(usdDestino);
		monedaDestino.add(btcDestino);
		der.add(destinoTitulo);
		der.add(euroDestino);
		der.add(usdDestino);
		der.add(btcDestino);
		add(der,BorderLayout.EAST);
		
		//La parte centro
		JLabel resultado = new JLabel();
		resultado.setText(convertidor + " ");
		centro.setBackground(Color.YELLOW);
		centro.add(resultado);
		add(centro,BorderLayout.CENTER);
		
		//La parte de abajo
		JLabel textoCantidad = new JLabel();
		textoCantidad.setText("Cantidad: ");
		JTextField cantidad = new JTextField();
		cantidad.setPreferredSize(getPreferredSize()); // he tenido que usar esto porque sino el recuadro se me quedaba enano y no se podia ni escribir, lo que me hace usar el pack para que se ajuste todo bien
		JLabel monedaOrigenTexto = new JLabel();
		monedaOrigenTexto.setText("_________");
		JButton convertir = new JButton("CONVERTIR");
		convertir.setBackground(Color.GREEN);
		abajo.add(textoCantidad);
		abajo.add(cantidad);
		abajo.add(monedaOrigenTexto);
		abajo.add(convertir);
		add(abajo, BorderLayout.SOUTH);
		
		convertir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String rutaArchivo = "converter.txt";
				
				if ((euroOrigen.isSelected()) && (usdDestino.isSelected())) {
				//	cantidadInicial = Double.parseDouble(cantidad.getSelectedText()); lo he intentado asi pero me daba error
					cantidadInicial = Double.parseDouble(JOptionPane.showInputDialog("Añade la cantidad que quieres cambiar."));
					convertidor = cantidadInicial * euroCambioDolar;
					
					resultado.setText(convertidor + usdDestinoMoneda);
					monedaOrigenTexto.setText(" " + euroOrigenMoneda);
					
					try {
						
						FileWriter fl = new FileWriter(rutaArchivo);
						fl.write("Cambio de Euros (" + cantidadInicial + ") a Dolares, por un valor de (" + convertidor + ").\n");
						fl.close();
						
					} catch(Exception exception) {
						System.out.println("Error al escribir en el archivo");
					}
					
				} else if ((euroOrigen.isSelected()) && (btcDestino.isSelected())) {
					
					cantidadInicial = Double.parseDouble(JOptionPane.showInputDialog("Añade la cantidad que quieres cambiar."));
					convertidor = cantidadInicial * euroCambioBTC;
					
					resultado.setText(convertidor + usdDestinoMoneda);
					monedaOrigenTexto.setText(" " + euroOrigenMoneda);
					
					try {
						
						FileWriter fl = new FileWriter(rutaArchivo);
						fl.write("Cambio de Euros (" + cantidadInicial + ") a Bitcoins, por un valor de (" + convertidor + ").\n");
						fl.close();
						
					} catch(Exception exception) {
						System.out.println("Error al escribir en el archivo");
					}
				
				} else if ((usdOrigen.isSelected()) && (euroDestino.isSelected())) {
					
					cantidadInicial = Double.parseDouble(JOptionPane.showInputDialog("Añade la cantidad que quieres cambiar."));
					convertidor = cantidadInicial * USDCambioEuro;
					
					resultado.setText(convertidor + usdDestinoMoneda);
					monedaOrigenTexto.setText(" " + usdOrigenMoneda);
					
					try {
						
						FileWriter fl = new FileWriter(rutaArchivo);
						fl.write("Cambio de Dolares (" + cantidadInicial + ") a Euros, por un valor de (" + convertidor + ").\n");
						fl.close();
						
					} catch(Exception exception) {
						System.out.println("Error al escribir en el archivo");
					}
				
				} else if ((usdOrigen.isSelected()) && (btcDestino.isSelected())) {
					
					cantidadInicial = Double.parseDouble(JOptionPane.showInputDialog("Añade la cantidad que quieres cambiar."));
					convertidor = cantidadInicial * USDCambioBTC;
					
					resultado.setText(convertidor + btcDestinoMoneda);
					monedaOrigenTexto.setText(" " + usdOrigenMoneda);
					
					try {
						
						FileWriter fl = new FileWriter(rutaArchivo);
						fl.write("Cambio de Dolares (" + cantidadInicial + ") a Bitcoins, por un valor de (" + convertidor + ").\n");
						fl.close();
						
					} catch(Exception exception) {
						System.out.println("Error al escribir en el archivo");
					}
				
				} else if ((btcOrigen.isSelected()) && (euroDestino.isSelected())) {
					
					cantidadInicial = Double.parseDouble(JOptionPane.showInputDialog("Añade la cantidad que quieres cambiar."));
					convertidor = cantidadInicial * BTCCambioEuro;
					
					resultado.setText(convertidor + euroDestinoMoneda);
					monedaOrigenTexto.setText(" " + btcOrigenMoneda);
					
					try {
						
						FileWriter fl = new FileWriter(rutaArchivo);
						fl.write("Cambio de Bitcoins (" + cantidadInicial + ") a Euros, por un valor de (" + convertidor + ").\n");
						fl.close();
						
					} catch(Exception exception) {
						System.out.println("Error al escribir en el archivo");
					}
				
				} else if ((usdOrigen.isSelected()) && (usdDestino.isSelected())) {
					
					cantidadInicial = Double.parseDouble(JOptionPane.showInputDialog("Añade la cantidad que quieres cambiar."));
					convertidor = cantidadInicial * BTCCambioUSD;
					
					resultado.setText(convertidor + usdDestinoMoneda);
					monedaOrigenTexto.setText(" " + btcOrigenMoneda);
					
					try {
						
						FileWriter fl = new FileWriter(rutaArchivo);
						fl.write("Cambio de Bitcoins (" + cantidadInicial + ") a Dolares, por un valor de (" + convertidor + ").\n");
						fl.close();
						
					} catch(Exception exception) {
						System.out.println("Error al escribir en el archivo");
					}
				
				}
			}
			
			
		});
		
		pack();
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		
		new Conversor();

	}

}
