package dam;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class ConversorApp {

	// Autor: PETAR G. MINOV
	
	
	public static void main(String[] args) {
		
		
		// Creacion inicial del panel
		JFrame frame = new JFrame("Conversor de Monedas");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(800, 350));
		frame.setVisible(true);
		
		
		// Creacion de diferentes paneles realizada mediante métodos estáticos
		
		
		
		frame.add(crearPanelNorte(), BorderLayout.NORTH);
		frame.add(crearPanelOeste(), BorderLayout.WEST);
		frame.add(crearPanelEste(), BorderLayout.EAST);
		frame.add(crearPanelCentral(), BorderLayout.CENTER);
		frame.add(crearPanelSur(), BorderLayout.SOUTH);
		
		// Despues de crearlo por estático, no se como acceder a las variables con lsiteners y tal

		frame.pack();
	}

	
	public static JPanel crearPanelNorte() {
		
		JPanel panel = new JPanel();
		JLabel etiqueta = new JLabel("CONVERSOR DE MONEDAS");
		Font texto = new Font("etiqueta", Font.BOLD, 40);
		etiqueta.setFont(texto);
		etiqueta.setForeground(Color.BLUE);
		panel.add(etiqueta);
		
		
		return panel;

		
	}
	
	public static JPanel crearPanelOeste() {
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder("MONEDA ORIGEN"));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(120, 20));
		
		JRadioButton oEUR = new JRadioButton("EUR");
		JRadioButton oUSD = new JRadioButton("USD");
		JRadioButton oBTC = new JRadioButton("BTC");
		
		ButtonGroup grupoOrigen = new ButtonGroup();
		grupoOrigen.add(oEUR);
		grupoOrigen.add(oUSD);
		grupoOrigen.add(oBTC);
		
		// Añado los botones al panel
		panel.add(oEUR);
		panel.add(oUSD);
		panel.add(oBTC);
		
		return panel;
		
	}
	
	
	
	public static JPanel crearPanelEste() {
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder("MONEDA ORIGEN"));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(120, 20));
		
		JRadioButton dEUR = new JRadioButton("EUR");
		JRadioButton dUSD = new JRadioButton("USD");
		JRadioButton dBTC = new JRadioButton("BTC");
		
		ButtonGroup grupoDestino = new ButtonGroup();
		grupoDestino.add(dEUR);
		grupoDestino.add(dUSD);
		grupoDestino.add(dBTC);
		
		// Añado los botones al panel
		panel.add(dEUR);
		panel.add(dUSD);
		panel.add(dBTC);
		
		return panel;
		
	}
	
	
	
public static JPanel crearPanelCentral() {
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.YELLOW);
		JLabel resultado = new JLabel("0.00", SwingConstants.CENTER);
		resultado.setPreferredSize(new Dimension(120, 40));
		resultado.setVerticalAlignment(SwingConstants.CENTER);
		resultado.setAlignmentY(50);
		Font textoEstilo = new Font("etiqueta", Font.BOLD, 40);
		resultado.setFont(textoEstilo);
		panel.add(resultado);
		return panel;
		
	}


public static JPanel crearPanelSur() {
	
	JPanel panel = new JPanel();
	panel.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 10));
	JLabel etiqueta = new JLabel("Cantidad:");
	JTextField introducido = new JTextField(20);
	
	//el texto de la denominacion no debe ser editable
	JLabel denominacion = new JLabel("_________");
	JButton botonConvertir = new JButton("CONVERTIR");
	botonConvertir.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	});
	
	panel.add(etiqueta);
	panel.add(introducido);
	panel.add(denominacion);
	panel.add(botonConvertir);
	
	
	return panel;

	
}
	
}
