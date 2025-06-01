package Conversor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;

import javax.swing.*;

public class ConversorAPP{
	
	File archivo = new File("Convert.log");
	
	public static void main(String[] args) {
		InitMenu();
		
	}
	
	public static void InitMenu() {
		JFrame frame = new JFrame();
		frame.setTitle("Conversor de Monedas");
		frame.setSize(600,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Menu Principio
		JPanel paneltext = new JPanel();
		JLabel InitText = new JLabel("CONVERSOR DE MONEDAS");
		InitText.setForeground(Color.BLUE);
		paneltext.add(InitText);
		
		
		//Menu Izq
		JPanel panelIzq = new JPanel(new BorderLayout());
		JLabel MonOr = new JLabel("Moneda Origen");
		JRadioButton botonesEURL = new JRadioButton("EUR");
		JRadioButton botonesUSDL = new JRadioButton("USD");
		JRadioButton botonesBTCL = new JRadioButton("BTC");
		panelIzq.add(MonOr,BorderLayout.NORTH);
		panelIzq.add(botonesEURL,BorderLayout.BEFORE_FIRST_LINE);
		panelIzq.add(botonesUSDL,BorderLayout.LINE_START);
		panelIzq.add(botonesBTCL,BorderLayout.AFTER_LAST_LINE);
		
		//Menu Der
		JPanel panelDer = new JPanel(new BorderLayout());
		JLabel MonDest = new JLabel("Moneda Destino");
		JRadioButton botonesEURR = new JRadioButton("EUR");
		JRadioButton botonesUSDR = new JRadioButton("USD");
		JRadioButton botonesBTCR = new JRadioButton("BTC");
		panelDer.add(MonDest,BorderLayout.NORTH);
		panelDer.add(botonesEURR,BorderLayout.BEFORE_FIRST_LINE);
		panelDer.add(botonesUSDR,BorderLayout.LINE_START);
		panelDer.add(botonesBTCR,BorderLayout.AFTER_LAST_LINE);
		
		//Menu Centro
		JPanel panelCentro = new JPanel();
		JLabel textCantidadNum = new JLabel("0.00");
		panelCentro.setBackground(Color.YELLOW);
		panelCentro.add(textCantidadNum,BorderLayout.CENTER);
		
		//Menu Final
		
		JPanel panelFinal = new JPanel();
		JLabel textoCantidad = new JLabel("Cantidad:");
		JTextField cuadroTexto = new JTextField(20);
		JLabel seleccion = new JLabel("____");
		JButton botonConvert = new JButton("CONVERTIR");
		botonConvert.setForeground(Color.WHITE);
		botonConvert.setBackground(Color.GREEN);
		panelFinal.add(textoCantidad);
		panelFinal.add(cuadroTexto);
		panelFinal.add(seleccion);
		
		
		botonConvert.addActionListener(e -> {
			String opcionOr = "";
			String opcionDest = "";
			String result = "";
			
			if(botonesEURL.isSelected()) {
				opcionOr = "EUR";
			}else if (botonesUSDL.isSelected()) {
				opcionOr = "USD";
			}
			else if(botonesBTCL.isSelected()) {
				opcionOr = "BTC";
			}
			
			if(botonesEURR.isSelected()) {
				opcionDest = "EUR";
			}else if (botonesUSDR.isSelected()) {
				opcionDest = "USD";
			}
			else if (botonesBTCR.isSelected()) {
				opcionDest = "BTC";
			}
			
			
			double num = Integer.parseInt(cuadroTexto.getText());
			if(opcionOr.equals(opcionDest)) {
				result = "" + num;
			}
			if(opcionOr.equals("EUR") && opcionDest.equals("USD")) {
				num = num * 1.08;
				result = "" + num;
			}else if(opcionOr.equals("USD") && opcionDest.equals("EUR")) {
				num = num * 0.92;
				result = "" + num;
			}
			else if(opcionOr.equals("EUR") && opcionDest.equals("BTC")) {
				num = num * 0.000016;
				result = "" + num;
			}
			else if(opcionOr.equals("USD") && opcionDest.equals("BTC")) {
				num = num * 0.000015;
				result = "" + num;
			}
			else if(opcionOr.equals("BTC") && opcionDest.equals("EUR")) {
				num = num * 62100;
				result = "" + num;
			}
			else if(opcionOr.equals("BTC") && opcionDest.equals("USD")) {
				num = num * 67500;
				result = "" + num;
			}
			textCantidadNum.setText(result);
		});
		
		panelFinal.add(botonConvert);
		
		JPanel padre = new JPanel(new BorderLayout());
		padre.add(panelIzq,BorderLayout.WEST);
		padre.add(panelCentro,BorderLayout.CENTER);
		padre.add(panelDer,BorderLayout.EAST);
		
		frame.add(paneltext,BorderLayout.PAGE_START);
		frame.add(padre,BorderLayout.CENTER);
		frame.add(panelFinal,BorderLayout.PAGE_END);
		
		frame.setVisible(true);
	}
	
	public static JLabel TextSeleccionado() {
		JLabel seleccion = new JLabel();
		return seleccion;
	}
}
