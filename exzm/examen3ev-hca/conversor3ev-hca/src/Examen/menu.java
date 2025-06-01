//Hugo Carcedo Ayala
package Examen;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class menu {
	
	public static void main(String[] args) {
		
		new Conversor();
		
	}
}
	class Conversor extends JFrame implements ActionListener{
		
		JButton convertir = new JButton("Convertir");
		JLabel moneda = new JLabel("___________");
		JTextField dinero = new JTextField(20);
		
		JRadioButton EUR = new JRadioButton("EUR");
		JRadioButton USD = new JRadioButton("USD");
		JRadioButton BTC = new JRadioButton("BTC");
		
		ButtonGroup monedaOrigen = new ButtonGroup();
		ButtonGroup monedaDestino = new ButtonGroup();
		
		public Conversor() {
			JFrame conversor = new JFrame("Conversor de Monedas");
			conversor.setSize(1000,500);
			conversor.setLayout(new GridLayout(3,1));
		
			JLabel etiquetaTitulo = new JLabel ("CONVERSOR DE MONEDAS");
		
			JPanel panelCambio = new JPanel();
			panelCambio.setLayout(new GridLayout(1, 3));
			
			JLabel monedaOrigen = new JLabel("MONEDA ORIGEN");
			monedaOrigen.setOpaque(true);
			
			panelCambio.add(monedaOrigen);
			
			
			panelCambio.add(new JLabel("0.00"));
			
			JLabel monedaDestino = new JLabel("MONEDA DESTINO");
			monedaDestino.setOpaque(true);
			panelCambio.add(monedaDestino);
			panelCambio.setBackground(Color.YELLOW);
			
			
			JPanel panelInferior = new JPanel();
			panelInferior.setLayout(new FlowLayout());
			panelInferior.add(new JLabel("Cantidad: "));
			panelInferior.add(dinero);
			panelInferior.add(moneda);
			
			
		
			convertir.addActionListener(this);
			convertir.setBackground(Color.GREEN);
			panelInferior.add(convertir);
			
			conversor.add(etiquetaTitulo);
			conversor.add(panelCambio);
			conversor.add(panelInferior);
		
			conversor.setVisible(true);
		
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			Double dineroNumero = Double.parseDouble(dinero.getText());
			if (EUR.isSelected()) {
				double calculoDinero = (dineroNumero*1);
				String mostrarDinero = "" + calculoDinero;
				moneda.setText(mostrarDinero);
			} else if (USD.isSelected()) {
				double calculoDinero = (dineroNumero*1.08);
				String mostrarDinero = "" + calculoDinero;
				moneda.setText(mostrarDinero);
			}  else if (BTC.isSelected()) {
				double calculoDinero = (dineroNumero*0.000016);
				String mostrarDinero = "" + calculoDinero;
				moneda.setText(mostrarDinero);
			} 
			
		}
	}


