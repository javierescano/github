package iag.conversor;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.Style;
import javax.swing.text.StyleContext;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileWriter;

public class Conversor {
	final static double EUR_USD = 1.08;
	final static double EUR_BTC = 0.000016;
	final static double USD_EUR = 0.92;
	final static double USD_BTC = 0.000015;
	final static double BTC_EUR = 62100;
	final static double BTC_USD = 67500;
	
	public static void main(String[] args) {
		JLabel monedaDestino = new JLabel();
		JFrame frame = new JFrame("Conversor de monedas");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,300);
		
		frame.setLayout(new BorderLayout());
		// Panel 1
		JPanel panelTitulo = new JPanel();
		JLabel titulo = new JLabel("CONVERSOR DE MONEDAS");
		
		panelTitulo.add(titulo);
		frame.add(panelTitulo, BorderLayout.NORTH);
		// Panel 2
		JPanel panelMonedas = new JPanel();
		panelMonedas.setLayout(new BorderLayout(30, 10));
	
		JPanel panelOrigen = new JPanel();
		ButtonGroup grupoOrigen = new ButtonGroup();
		
		JRadioButton radioEUROrigen = new JRadioButton("EUR");
		JRadioButton radioUSDOrigen = new JRadioButton("USD");
		JRadioButton radioBTCOrigen = new JRadioButton("BTC");
		grupoOrigen.add(radioEUROrigen);
		grupoOrigen.add(radioUSDOrigen);
		grupoOrigen.add(radioBTCOrigen);
		
		panelOrigen.setLayout(new GridLayout(3, 0, 5, 5));
		panelOrigen.add(radioEUROrigen);
		panelOrigen.add(radioUSDOrigen);
		panelOrigen.add(radioBTCOrigen);
		JPanel panelDestino = new JPanel();
		ButtonGroup grupoDestino = new ButtonGroup();
		
		JRadioButton radioEURDest = new JRadioButton("EUR");
		JRadioButton radioUSDDest = new JRadioButton("USD");
		JRadioButton radioBTCDest = new JRadioButton("BTC");
		
		grupoDestino.add(radioEURDest);
		grupoDestino.add(radioBTCDest);
		grupoDestino.add(radioUSDDest);
		
		panelDestino.setLayout(new GridLayout(3, 0, 5, 5));
		panelDestino.add(radioEURDest);
		panelDestino.add(radioUSDDest);
		panelDestino.add(radioBTCDest);
		
		JPanel panelResultado = new JPanel();
		panelResultado.setLayout(new FlowLayout(0, 200, 90));
		panelResultado.setBackground(Color.yellow);
		panelResultado.setOpaque(true);
		JLabel labelResultado = new JLabel("0.00");
		
		panelResultado.add(labelResultado);
		
		panelMonedas.add(panelOrigen, BorderLayout.LINE_START);
		panelMonedas.add(panelResultado, BorderLayout.CENTER);
		panelMonedas.add(panelDestino, BorderLayout.LINE_END);
		// Panel 3
		JPanel panelConvertir = new JPanel();
		panelConvertir.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
		
		JLabel labelCantidad = new JLabel("Cantidad:");
		
		JTextField campoCantidad = new JTextField(15);
		
		JLabel labelMoneda = new JLabel("______");
		
		JButton botonConvertir = new JButton("CONVERTIR");
		botonConvertir.setBackground(Color.green);
		panelConvertir.add(labelCantidad);
		panelConvertir.add(campoCantidad);
		panelConvertir.add(labelMoneda);
		panelConvertir.add(botonConvertir);
		
		frame.add(panelConvertir, BorderLayout.SOUTH);
		
		frame.add(panelMonedas, BorderLayout.CENTER);
		frame.setVisible(true);
		
		botonConvertir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				double resultado = 0;
				try {
				resultado = Double.parseDouble(campoCantidad.getText());
				} catch (Exception e2) {
					
				}
				if (labelMoneda.getText().equals("EUROS") && monedaDestino.getText().equals("DOLARES")) {
					resultado = resultado * EUR_USD;
				} else if (labelMoneda.getText().equals("EUROS") && monedaDestino.getText().equals("BITCOINS")) {
					resultado = resultado * EUR_BTC;
				} else if (labelMoneda.getText().equals("DOLARES") && monedaDestino.getText().equals("EUROS")) {
					resultado = resultado * USD_EUR;
				} else if (labelMoneda.getText().equals("DOLARES") && monedaDestino.getText().equals("BITCOINS")) {
					resultado = resultado * USD_BTC;
				} else if (labelMoneda.getText().equals("BITCOINS") && monedaDestino.getText().equals("EUROS")) {
					resultado = resultado * BTC_EUR;
				} else if (labelMoneda.getText().equals("BITCOINS") && monedaDestino.getText().equals("DOLARES")) {
					resultado = resultado * BTC_USD;
				} 
				labelResultado.setText(resultado + " " + monedaDestino.getText());
				campoCantidad.setText("");
				try {
					FileWriter fw = new FileWriter("C:\\Users\\izan.antgar\\Documents\\PROGRAMACIÓN\\ws-programacion\\conversor3ev-iag\\log\\ficheroLog.txt");
					fw.write(resultado + " " + monedaDestino.getText());
					fw.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				} 
			}
		});
		
		// Añadimos listeners a las monedas
		
		radioEURDest.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (radioEURDest.isSelected()) {
					monedaDestino.setText("EUROS");
				}
				
			}
		});
		radioUSDDest.addItemListener(new ItemListener() {

			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (radioUSDDest.isSelected()) {
					monedaDestino.setText("DOLARES");
				}
				
			}
		});
		radioBTCDest.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (radioBTCDest.isSelected()) {
					monedaDestino.setText("BITCOINS");
				}
				
			}
		});
		
		radioEUROrigen.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (radioEUROrigen.isSelected()) {
					labelMoneda.setText("EUROS");
				}
				
			}
		});
		radioUSDOrigen.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (radioUSDOrigen.isSelected()) {
					labelMoneda.setText("DOLARES");
					
				}
				
			}
		});
		radioBTCOrigen.addItemListener(new ItemListener() {
	
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (radioBTCOrigen.isSelected()) {
				labelMoneda.setText("BITCOINS");
			}
			
		}
	});
		}
		
}
