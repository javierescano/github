package examen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Ejercicio1 extends JFrame {

	public static void main(String[] args) {
		
		Ejercicio1 a = new Ejercicio1();

	}

	public Ejercicio1() {
		
		setTitle("Conversor de monedas");
		setSize(400,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel titulo = new JLabel("CONVERSOR DE MONEDAS", SwingConstants.CENTER);
		
		JPanel panelCentro = new JPanel(new GridLayout(4,2));
		
		JPanel panelCambio = new JPanel();

		//cosas origen
		
		panelCentro.add(new JLabel("MONEDA ORIGEN"));
		JPanel panelOrigen = new JPanel();
		JRadioButton euros = new JRadioButton("EUROS");
		JRadioButton usd = new JRadioButton("USD");
		JRadioButton btc = new JRadioButton("BTC");
		ButtonGroup grupoOrigen = new ButtonGroup();
		grupoOrigen.add(euros);
		grupoOrigen.add(btc);
		grupoOrigen.add(usd);
		
		panelOrigen.add(euros);
		panelOrigen.add(btc);
		panelOrigen.add(usd);
		
		panelCentro.add(panelOrigen);
		
		
		
		//cosas destino
		

		panelCentro.add(new JLabel("MONEDA DESTINO"));
		JPanel panelDestino = new JPanel();
		JRadioButton eurosDestino = new JRadioButton("EUROS");
		JRadioButton usdDestino = new JRadioButton("USD");
		JRadioButton btcDestino = new JRadioButton("BTC");
		ButtonGroup grupoDestino = new ButtonGroup();
		grupoDestino.add(eurosDestino);
		grupoDestino.add(usdDestino);
		grupoDestino.add(btcDestino);
		
		panelDestino.add(eurosDestino);
		panelDestino.add(usdDestino);
		panelDestino.add(btcDestino);		
		panelCentro.add(panelDestino);
		
		//resultado
		
		panelCentro.add(new JLabel("RTesultado"));
		JLabel resultado = new JLabel("0,0");
		resultado.setHorizontalAlignment(SwingConstants.CENTER);
		resultado.setBackground(Color.yellow);		
		panelCentro.add(resultado);
		
		
		//meter dinero
		
		panelCentro.add(new JLabel("Cantidad"));
		JPanel panelCantidad = new JPanel();
		JTextField campoCantidad = new JTextField(10);
		JLabel etiquetaUnidad = new JLabel();
		panelCantidad.add(campoCantidad);
		panelCantidad.add(etiquetaUnidad);
		panelCentro.add(panelCantidad);
		
		
		//boton
		
		JButton botonConvertir = new JButton("CONVERTIR");
		add(botonConvertir, BorderLayout.SOUTH);
		
		
		
		//adds
		add(panelCentro, BorderLayout.CENTER);

		setVisible(true);
		
		
		botonConvertir.addActionListener(new ActionListener() {

			
			
			
			//NO ME FUNCIONAN CON MONEDAS DIFERENTES, SOLO CON MONDEAS IGUALES. NO ENTIENDO PORQUE
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					double cantidad = Double.parseDouble(campoCantidad.getText());
					double resultado = 0;
					String origen = "";
					String destino = "";
					
					if(euros.isSelected()) {
						origen = "EUR";

					}
					else if(usd.isSelected()) {
						origen = "USD";
					}
					else if(btc.isSelected()) {
						origen = "BTC";
					}

					if(eurosDestino.isSelected()) destino = "EUR";
					else if(usdDestino.isSelected()) destino = "USD";
					else if(btcDestino.isSelected()) destino = "BTC";
					
					if(origen.equals(destino)) {
						resultado = cantidad;
						System.out.println(resultado);
					}else if(origen.equals("EUR") && eurosDestino.equals("USD")) {
						System.out.println("JAJAJAJA");
						resultado = cantidad * 1.08;
						System.out.println(resultado);
					}else if(origen.equals("EUR") && eurosDestino.equals("BTC")) {
						resultado = cantidad * 0.000016;
						System.out.println(resultado);
					}else if(origen.equals("USD") && eurosDestino.equals("EUR")) {
						resultado = cantidad * 0.92;
						System.out.println(resultado);
					}else if(origen.equals("USD") && eurosDestino.equals("BTC")) {
						resultado = cantidad * 0.000015;
					}else if(origen.equals("BTC") && eurosDestino.equals("EUR")) {
						resultado = cantidad * 62100;
					}else if(origen.equals("BTC") && eurosDestino.equals("USD")) {
						resultado = cantidad * 67500;
						System.out.println(resultado);
					}
					
				
					
				}catch(NumberFormatException e1) {
					
				}
				
			}
			
		});
	
}
}
