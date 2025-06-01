package conversor;

import java.awt.Font;
import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

public class AppConversor {

	public static void main(String[] args) {

		//Isela Pilar González Primo

		JFrame ventana = new JFrame("Conversor de Monedas");
		ventana.setDefaultCloseOperation(ventana.EXIT_ON_CLOSE);
		ventana.setSize(500, 250);

		JPanel panel = new JPanel(new GridLayout(5, 3));

		JLabel titulo = new JLabel("CONVERSOR DE MONEDAS", JLabel.CENTER);
		titulo.setFont(new Font("Arial", Font.BOLD, 15));


		ButtonGroup botonesMonedaOrigen = new ButtonGroup();

		JRadioButton eurBotonOrigen = new JRadioButton("EUR");
		JRadioButton usdBotonOrigen = new JRadioButton("USD");
		JRadioButton btcBotonOrigen = new JRadioButton("BTC");

		botonesMonedaOrigen.add(eurBotonOrigen);
		botonesMonedaOrigen.add(usdBotonOrigen);
		botonesMonedaOrigen.add(btcBotonOrigen);

		ButtonGroup botonesMonedaDestino = new ButtonGroup();

		JRadioButton eurBotonDestino = new JRadioButton("EUR");
		JRadioButton usdBotonDestino = new JRadioButton("USD");
		JRadioButton btcBotonDestino = new JRadioButton("BTC");

		botonesMonedaOrigen.add(eurBotonDestino);
		botonesMonedaOrigen.add(usdBotonDestino);
		botonesMonedaOrigen.add(btcBotonDestino);




		//si quitas esto, compila el programa
		JButton botonConvertir = new JButton("CONVERTIR");
		JLabel moneda = new JLabel("", Label.CENTER);
		moneda.setOpaque(true);

		JTextField cantidad = new JTextField();

		ActionListener cambiarMoneda = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String seleccion = "";

				if(seleccion.equals("EUR")) {
					moneda.setText("euros");
				} else if(seleccion.equals("USD")) {
					moneda.setText("dólares");
				} else if(seleccion.equals("BTC")) {
					moneda.setText("bitcoins");
				}
			}

		};

		panel.add(new JLabel("MONEDA ORIGEN", Label.CENTER));
		panel.add(titulo);
		panel.add(new JLabel("MONEDA DESTINO", Label.CENTER));
		panel.add(botonConvertir);

		// hasta aquí





		panel.add(eurBotonDestino);
		panel.add(usdBotonDestino);
		panel.add(btcBotonDestino);

		panel.add(eurBotonOrigen);
		panel.add(usdBotonOrigen);
		panel.add(btcBotonOrigen);

		ventana.add(panel);
		panel.setBackground(Color.YELLOW);
		ventana.setVisible(true);
	}

}
