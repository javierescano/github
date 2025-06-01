//Younes El Badadi

package conversor3ev_YEB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConvertirMoneda extends JFrame implements ActionListener, ItemListener {
	private JRadioButton eurOrigen, usdOrigen, btcOrigen;
	private JRadioButton eurDestino, usdDestino, btcDestino;
	private ButtonGroup grupoOrigen, grupoDestino;
	private JTextField campoCantidad;
	private JLabel etiquetaResultado, etiquetaUnidad;
	
	public ConvertirMoneda() {
		setTitle("Conversor de Monedas");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setLayout(null);
		
		//Titulo
		JLabel titulo = new JLabel("CONVERSOR DE MONEDAS");
		titulo.setFont(new Font("Arial", Font.BOLD, 24));
		setLayout(new GridLayout(1, 1));
		titulo.setBounds(20, 80, 300, 30);
		add(titulo);
		
		//Grupo Origen
		JPanel panelOrigen = new JPanel(new GridLayout(3, 1));
		panelOrigen.setBorder(BorderFactory.createTitledBorder("Origen"));
		 
		eurOrigen = new JRadioButton("Euro");
		usdOrigen = new JRadioButton("Dólar");
		btcOrigen = new JRadioButton("Bitcoin");
		
		grupoOrigen = new ButtonGroup();
		grupoOrigen.add(eurOrigen);
		grupoOrigen.add(usdOrigen);
		grupoOrigen.add(btcOrigen);
		panelOrigen.add(eurOrigen);
		panelOrigen.add(usdOrigen);
		panelOrigen.add(btcOrigen);
		panelOrigen.setBounds(400, 80, 80, 100);
		setLayout(new GridLayout(2, 3));
		add(panelOrigen);
		
		//Grupo Destino
		JPanel panelDestino = new JPanel(new GridLayout(3, 1));
		panelDestino.setBorder(BorderFactory.createTitledBorder("Destino"));
		 
		eurDestino = new JRadioButton("EUR");
		usdDestino = new JRadioButton("USD");
		btcDestino = new JRadioButton("BTC");
		
		grupoDestino = new ButtonGroup();
		grupoDestino.add(eurDestino);
		grupoDestino.add(usdDestino);
		grupoDestino.add(btcDestino);
		panelDestino.add(eurDestino);
		panelDestino.add(usdDestino);
		panelDestino.add(btcDestino);
		panelDestino.setBounds(490, 80, 80, 100);
		setLayout(new GridLayout(2, 1));
		add(panelDestino);
		
		// Campos y botones
		campoCantidad = new JTextField();
		campoCantidad.setFont(new Font("Arial", Font.BOLD, 14));
		campoCantidad.setBounds(150, 10, 200, 300);
		setLayout(new GridLayout(3, 1));
		add(campoCantidad);
		
		JButton btnConvertir = new JButton ("CONVERTIR");
		btnConvertir.setBackground(Color.GREEN);
		btnConvertir.addActionListener(this);
		setLayout(new GridLayout(3, 3));
		add(btnConvertir);
		
		etiquetaUnidad = new JLabel("");
		etiquetaUnidad.setFont(new Font("Arial", Font.BOLD, 16));
		etiquetaUnidad.setBounds(40, 200, 10, 30);
		setLayout(new GridLayout(3, 2));
		add(etiquetaUnidad);
		
		etiquetaResultado = new JLabel("0.00", JLabel.CENTER);
		etiquetaResultado.setOpaque(true);
		etiquetaResultado.setBackground(Color.YELLOW);
		etiquetaResultado.setFont(new Font("Arial", Font.BOLD, 18));
		etiquetaResultado.setBounds(300, 200, 400, 40);
		setLayout(new GridLayout(2, 2));
		add(etiquetaResultado);
		
		eurOrigen.addItemListener(this);
		usdOrigen.addItemListener(this);
		btcOrigen.addItemListener(this);
		
		setVisible(true);
	}
	
	// Logica
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(eurOrigen.isSelected()) etiquetaUnidad.setText("EUR");
		else if (usdOrigen.isSelected()) etiquetaUnidad.setText("USD");
		else if (btcOrigen.isSelected()) etiquetaUnidad.setText("BTC");
		else etiquetaUnidad.setText("");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (grupoOrigen.getSelection() == null || grupoDestino.getSelection() == null) {
			etiquetaResultado.setText("Selecciona Origen y destino");
			return;
		}
		
		try {
			double cantidad = Double.parseDouble(campoCantidad.getText());
			double tasa = obtenerTasa();
			
			if (tasa == -1) {
				etiquetaResultado.setText("Conversión no válida");
			} else {
				double convertido = cantidad * tasa;
				etiquetaResultado.setText(String.format("%.4f", convertido));
			}
		} catch (NumberFormatException ex){
			etiquetaResultado.setText("Cantidad Inválida");
		}
	}
	
	private double obtenerTasa() {
		if (eurOrigen.isSelected() && usdDestino.isSelected()) return 1.08;
		if (eurOrigen.isSelected() && btcDestino.isSelected()) return 0.000016;
		if (eurOrigen.isSelected() && eurDestino.isSelected()) return 1;
		
		if (usdOrigen.isSelected() && usdDestino.isSelected()) return 1;
		if (usdOrigen.isSelected() && btcDestino.isSelected()) return 0.000015;
		if (usdOrigen.isSelected() && eurDestino.isSelected()) return 0.92;
		
		if (btcOrigen.isSelected() && usdDestino.isSelected()) return 67500;
		if (btcOrigen.isSelected() && btcDestino.isSelected()) return 1;
		if (btcOrigen.isSelected() && eurDestino.isSelected()) return 62100;
		
		return -1;
	}
}
