package llm;

import java.awt.BorderLayout;
import java.awt.Color;


import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class VentanaConversor extends JFrame {
	public VentanaConversor() {
		this.setLayout(new BorderLayout());
		this.add(titulo(), BorderLayout.NORTH);
		this.add(panelMonedasOrigen(), BorderLayout.WEST);
		this.add(panelMonedasDestino(), BorderLayout.EAST);
		this.add(panelAbajo(), BorderLayout.SOUTH);
		this.add(panelContador(), BorderLayout.CENTER);
		
	}
	
	private JPanel panelMonedasOrigen() {
		JPanel panel = new JPanel();
		JLabel titulo = new JLabel("MONEDA ORIGEN");
		ButtonGroup monedas = new ButtonGroup();
		JRadioButton eur = new JRadioButton("EUR");
		JRadioButton usd = new JRadioButton("USD");
		JRadioButton btc = new JRadioButton("BTC");
		monedas.add(eur);
		monedas.add(usd);
		monedas.add(btc);
		panel.add(titulo);
		panel.add(eur);
		panel.add(usd);
		panel.add(btc);
		return panel;
	}
	private JPanel panelMonedasDestino() {
		JPanel panel = new JPanel();
		JLabel titulo = new JLabel("MONEDA DESTINO");
		ButtonGroup monedas = new ButtonGroup();
		JRadioButton eur = new JRadioButton("EUR");
		JRadioButton usd = new JRadioButton("USD");
		JRadioButton btc = new JRadioButton("BTC");
		monedas.add(eur);
		monedas.add(usd);
		monedas.add(btc);
		panel.add(titulo);
		panel.add(eur);
		panel.add(usd);
		panel.add(btc);
		return panel;
	}
	private JPanel titulo() {
		JPanel panel = new JPanel();
		JLabel titulo  = new JLabel("CONVERSOR DE MONEDAS");
		titulo.setHorizontalTextPosition(JLabel.CENTER);
		titulo.setSize(200, 200);
		panel.add(titulo);
		panel.setSize(200, 200);
		return panel;
	}
	private JPanel panelAbajo() {
		JPanel panel = new JPanel();
		JLabel cantidad = new JLabel("Cantidad: ");
		JTextArea input = new JTextArea(); 
		input.setSize(500, 50);
		JLabel moneda = new JLabel("____");
		JButton botonConvertir = new JButton("CONVERTIR");
		botonConvertir.setBackground(Color.GREEN);
		botonConvertir.setOpaque(true);
		panel.add(cantidad);
		panel.add(input);
		panel.add(moneda);
		panel.add(botonConvertir);
		
		return panel;
	}
	private JPanel panelContador() {
		JPanel panel = new JPanel();
		JLabel numero = new JLabel("0.00");
		numero.setHorizontalAlignment(JLabel.CENTER);
		numero.setVerticalAlignment(JLabel.CENTER);
		numero.setBackground(Color.YELLOW);
		numero.setOpaque(true);
		numero.setSize(400, 200);
		panel.add(numero);
		panel.setSize(400, 200);
		
		return panel;
	}
}
