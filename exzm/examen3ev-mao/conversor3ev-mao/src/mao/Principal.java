// Mario Álvarez Ortega

package mao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.*;

public class Principal {

	public static void main(String[] args) {
		Ventana ventana = new Ventana();
		ventana.setVisible(true);
	}
}

class Ventana extends JFrame {
	public static final int TIPO_ORIGEN = 1;
	public static final int TIPO_DESTINO = 2;

	public Ventana() {
		setTitle("Conversor de Monedas");
		setSize(720, 480);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// TEXTO SUPERIOR
		JPanel panelTexto = new JPanel();
		JLabel texto = new JLabel("CONVERSOR DE MONEDAS");
		texto.setForeground(Color.BLUE);
		panelTexto.add(texto);

		// TEXTO INFERIOR
		JPanel panelCantidadConvertir = new JPanel();
		JLabel textoCantidad = new JLabel("Cantidad: ");
		JTextField cantidadConvertir = new JTextField(20);
		JLabel divisaOrigen = new JLabel("________");
		JButton botonConvertir = new JButton("CONVERTIR");

		panelCantidadConvertir.add(textoCantidad);
		panelCantidadConvertir.add(cantidadConvertir);
		panelCantidadConvertir.add(divisaOrigen);
		panelCantidadConvertir.add(botonConvertir);

		// TEXTO CENTRAL
		JPanel panelResultado = new JPanel();
		panelResultado.setBackground(Color.YELLOW);
		JLabel textoResultado = new JLabel("0.00");
		panelResultado.add(textoResultado);

		// TEXTO IZQUIERDA
		JPanel panelSelectorMonedaOrigen = new JPanel(new GridLayout(4, 1));
		ButtonGroup grupoOrigen = new ButtonGroup();
		JLabel textoOrigen = new JLabel("MONEDA ORIGEN");

		JRadioButton botonEurOrigen = new JRadioButton("EUR");
		JRadioButton botonUsdOrigen = new JRadioButton("USD");
		JRadioButton botonBtcOrigen = new JRadioButton("BTC");

		grupoOrigen.add(botonEurOrigen);
		grupoOrigen.add(botonUsdOrigen);
		grupoOrigen.add(botonBtcOrigen);
		panelSelectorMonedaOrigen.add(textoOrigen);
		panelSelectorMonedaOrigen.add(botonEurOrigen);
		panelSelectorMonedaOrigen.add(botonUsdOrigen);
		panelSelectorMonedaOrigen.add(botonBtcOrigen);

		botonEurOrigen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (botonEurOrigen.isSelected()) {
					divisaOrigen.setText("EUROS");
				}

			}
		});

		botonUsdOrigen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (botonUsdOrigen.isSelected()) {
					divisaOrigen.setText("DOLARES");
				}

			}
		});

		botonBtcOrigen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (botonBtcOrigen.isSelected()) {
					divisaOrigen.setText("BITCOIN");
				}

			}
		});

		// TEXTO DERECHA

		JPanel panelSelectorMonedaDestino = new JPanel(new GridLayout(4, 1));
		ButtonGroup grupoDestino = new ButtonGroup();
		JLabel textoDestino = new JLabel("MONEDA DESTINO");

		JRadioButton botonEurDestino = new JRadioButton("EUR");
		JRadioButton botonUsdDestino = new JRadioButton("USD");
		JRadioButton botonBtcDestino = new JRadioButton("BTC");

		grupoDestino.add(botonEurDestino);
		grupoDestino.add(botonUsdDestino);
		grupoDestino.add(botonBtcDestino);
		panelSelectorMonedaDestino.add(textoDestino);
		panelSelectorMonedaDestino.add(botonEurDestino);
		panelSelectorMonedaDestino.add(botonUsdDestino);
		panelSelectorMonedaDestino.add(botonBtcDestino);

		// AÑADIR TODO AL JFRAME
		add(panelTexto, BorderLayout.NORTH);
		add(panelResultado, BorderLayout.CENTER);
		add(panelCantidadConvertir, BorderLayout.SOUTH);
		add(panelSelectorMonedaOrigen, BorderLayout.WEST);
		add(panelSelectorMonedaDestino, BorderLayout.EAST);

		// LOGICA BOTON
		botonConvertir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Double dineroOrigen = null;
				Double dineroDestino = null;
				String divisaDestino = null;
				if (botonEurOrigen.isSelected() && botonUsdDestino.isSelected()) {
					dineroOrigen = Double.parseDouble(cantidadConvertir.getText());
					dineroDestino = dineroOrigen * 1.08;
					textoResultado.setText(dineroDestino + " USD");
					divisaDestino = "USD";
				} else if (botonEurOrigen.isSelected() && botonBtcDestino.isSelected()) {
					dineroOrigen = Double.parseDouble(cantidadConvertir.getText());
					dineroDestino = dineroOrigen * 0.000016;
					textoResultado.setText(dineroDestino + " BTC");
					divisaDestino = "BTC";
				} else if (botonEurOrigen.isSelected() && botonEurDestino.isSelected()) {
					dineroOrigen = Double.parseDouble(cantidadConvertir.getText());
					dineroDestino = dineroOrigen;
					textoResultado.setText(dineroDestino + " EUR");
					divisaDestino = "EUR";
				} else if (botonUsdOrigen.isSelected() && botonEurDestino.isSelected()) {
					dineroOrigen = Double.parseDouble(cantidadConvertir.getText());
					dineroDestino = dineroOrigen * 0.92;
					textoResultado.setText(dineroDestino + " EUR");
					divisaDestino = "EUR";
				} else if (botonUsdOrigen.isSelected() && botonBtcDestino.isSelected()) {
					dineroOrigen = Double.parseDouble(cantidadConvertir.getText());
					dineroDestino = dineroOrigen * 0.000015;
					textoResultado.setText(dineroDestino + " BTC");
					divisaDestino = "BTC";
				} else if (botonUsdOrigen.isSelected() && botonUsdDestino.isSelected()) {
					dineroOrigen = Double.parseDouble(cantidadConvertir.getText());
					dineroDestino = dineroOrigen;
					textoResultado.setText(dineroDestino + " USD");
					divisaDestino = "USD";
				} else if (botonBtcOrigen.isSelected() && botonEurDestino.isSelected()) {
					dineroOrigen = Double.parseDouble(cantidadConvertir.getText());
					dineroDestino = dineroOrigen * 62100;
					textoResultado.setText(dineroDestino + " EUR");
					divisaDestino = "EUR";
				} else if (botonBtcOrigen.isSelected() && botonUsdDestino.isSelected()) {
					dineroOrigen = Double.parseDouble(cantidadConvertir.getText());
					dineroDestino = dineroOrigen * 67500;
					textoResultado.setText(dineroDestino + " USD");
					divisaDestino = "USD";
				} else if (botonBtcOrigen.isSelected() && botonBtcDestino.isSelected()) {
					dineroOrigen = Double.parseDouble(cantidadConvertir.getText());
					dineroDestino = dineroOrigen;
					textoResultado.setText(dineroDestino + " BTC");
					divisaDestino = "BTC";
				}
				try {
					PrintWriter pw = new PrintWriter("archivo.txt");
					pw.write("Se han convertido " + dineroOrigen + " a " + dineroDestino + " " + divisaDestino);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

			}
		});
	}

// METODO PARA GENERAR LOS SELECTORES DE MONEDA (IZQUIERDA Y DERECHA)
//	public JPanel generarSelector(int tipo) {
//		JPanel panelSelectorMoneda = new JPanel(new GridLayout(4, 1));
//		ButtonGroup grupoOrigen = new ButtonGroup();
//		JLabel texto;
//
//		if (tipo == TIPO_ORIGEN) {
//			texto = new JLabel("MONEDA ORIGEN");
//		} else {
//			texto = new JLabel("MONEDA DESTINO");
//		}
//
//		JRadioButton botonEur = new JRadioButton("EUR");
//		JRadioButton botonUsd = new JRadioButton("USD");
//		JRadioButton botonBtc = new JRadioButton("BTC");
//
//		grupoOrigen.add(botonEur);
//		grupoOrigen.add(botonUsd);
//		grupoOrigen.add(botonBtc);
//		panelSelectorMoneda.add(texto);
//		panelSelectorMoneda.add(botonEur);
//		panelSelectorMoneda.add(botonUsd);
//		panelSelectorMoneda.add(botonBtc);
//
//		return panelSelectorMoneda;
//	}

}
