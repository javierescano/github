package dmm.ventanas;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame{

	public Ventana() {
		JFrame frame = new JFrame();
		setTitle("Conversor de Monedas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 300, 1000, 500);
		
		
		add(panelArriba());
		add(panelAbajo(), BorderLayout.SOUTH);
		add(elegirMonedaOrigen(), BorderLayout.WEST);
		add(elegirMonedaDestino(), BorderLayout.EAST);
		//add(panelMedio(""), BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	private JPanel elegirMonedaOrigen() {
		
		JPanel panel = new JPanel(new GridLayout(4, 1, 0, 0));
		JLabel tituloMoneda = new JLabel("MONEDA ORIGEN");

		ButtonGroup tiposMonedas = new ButtonGroup();
		JRadioButton eur = new JRadioButton("EUR");
		JRadioButton usd = new JRadioButton("USD");
		JRadioButton btc = new JRadioButton("BTC");
		tiposMonedas.add(eur);
		tiposMonedas.add(usd);
		tiposMonedas.add(btc);
		panel.add(tituloMoneda);
		panel.add(eur);
		panel.add(usd);
		panel.add(btc);

		
		return panel;
	}
	
private JPanel elegirMonedaDestino() {
		
		JPanel panel = new JPanel(new GridLayout(4, 1, 0, 0));
		JLabel tituloMoneda = new JLabel("MONEDA DESTINO");

		ButtonGroup tiposMonedas = new ButtonGroup();
		JRadioButton eur = new JRadioButton("EUR");
		JRadioButton usd = new JRadioButton("USD");
		JRadioButton btc = new JRadioButton("BTC");
		tiposMonedas.add(eur);
		tiposMonedas.add(usd);
		tiposMonedas.add(btc);
		panel.add(tituloMoneda);
		panel.add(eur);
		panel.add(usd);
		panel.add(btc);

		
		return panel;
	}
	
	private JPanel panelAbajo() {
		
		JPanel panel = new JPanel(new FlowLayout());
		JLabel label = new JLabel("Cantidad:");
		JTextArea introducir = new JTextArea();
		introducir.setSize(10, 10);
		
		JLabel tipoMoneda = new JLabel("___");
		
		JButton convertir = new JButton("CONVERTIR");
		convertir.addActionListener(null);
		
		panel.add(label);
		panel.add(introducir);
		panel.add(tipoMoneda);
		panel.add(convertir);
		
		return panel;
	}
	
	
	private JPanel panelArriba() {
		
		JPanel panel = new JPanel();
		JLabel label = new JLabel("CONVERSOR DE MONEDAS");
		label.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		panel.add(label);
		
		return panel;
		
	}
	
	private JPanel panelMedio(String cantidad) {
		
		cantidad = "0.00";
		
		JPanel panel = new JPanel();
		JLabel label = new JLabel(cantidad);
		
		panel.add(label);
		
		return panel;
	}
	
}
