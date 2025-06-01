import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat.Style;
import java.time.format.TextStyle;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneUI.BasicHorizontalLayoutManager;
import javax.swing.text.Document;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleConstants.FontConstants;

public class Ventana extends JFrame implements ItemListener{
	JPanel monedasOrigen = new JPanel();
	ButtonGroup origen = new ButtonGroup();
	JPanel monedasDestino = new JPanel();
	ButtonGroup destino = new ButtonGroup();
	
	JLabel labelEurO = new JLabel("EUR");
	JLabel labelUsdO = new JLabel("USD");
	JLabel labelBtcO = new JLabel("BTC");
	JLabel labelEurD = new JLabel("EUR");
	JLabel labelUsdD = new JLabel("USD");
	JLabel labelBtcD = new JLabel("BTC");
	
	JRadioButton eurO = new JRadioButton();
	JRadioButton usdO = new JRadioButton();
	JRadioButton btcO = new JRadioButton();
	
	JRadioButton eurD = new JRadioButton();
	JRadioButton usdD = new JRadioButton();
	JRadioButton btcD = new JRadioButton();
	
	JTextField cantidad = new JTextField(10);
	public Ventana() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Conversor de Monedas");
		this.setSize(1000, 500);
		
		JLabel title = new JLabel("CONVERSOR DE MONEDAS", SwingConstants.CENTER);//TODO
		
		
		//Botones radio
		origen.add(eurO);
		origen.add(usdO);
		origen.add(btcO);
		
		monedasOrigen.setLayout(new GridLayout(3, 2));
		monedasOrigen.add(eurO);monedasOrigen.add(labelEurO);
		monedasOrigen.add(usdO);monedasOrigen.add(labelUsdO);
		monedasOrigen.add(btcO);monedasOrigen.add(labelBtcO);
		
		destino.add(eurD);
		destino.add(usdD);
		destino.add(btcD);
		
		monedasDestino.setLayout(new GridLayout(3, 2));
		monedasDestino.add(eurD);monedasDestino.add(labelEurD);
		monedasDestino.add(usdD);monedasDestino.add(labelUsdD);
		monedasDestino.add(btcD);monedasDestino.add(labelBtcD);
		
		//Centro
		JPanel centro = new JPanel();
		JLabel convertido = new JLabel("0.00");
		convertido.setFont(new Font("fuente1", StyleConstants.ALIGN_JUSTIFIED, 50));
		convertido.setSize(1000, 500);
		centro.add(convertido);
		centro.setBackground(Color.YELLOW);
		
		//sur
		JPanel sur = new JPanel();
		JLabel labelCantidad = new JLabel("Cantidad: ");
		
		JLabel moneda = new JLabel("______");
		JButton convertir = new JButton("CONVERTIR");
		convertir.setBackground(Color.GREEN);
		sur.add(labelCantidad);
		sur.add(cantidad);
		sur.add(moneda);
		sur.add(convertir);
		
		
		convertir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double cant = 0.00;
				double salida = 0.00;
				String valor = cantidad.getText();
				
				try {
					System.out.println(valor);
					cant = Double.parseDouble(valor.toString());
				} catch (Exception e1) {
					System.out.println("El valor introducido no es numerico");
				}
				
				if (eurO.isSelected()) {
					if(eurD.isSelected()) {
						salida = cant;
					} else if (usdD.isSelected()) {
						salida = cant * 1.08;
					} else if (btcD.isSelected()) {
						salida = cant * 0.000016;
					}
				}
				if (usdO.isSelected()) {
					if(eurD.isSelected()) {
						salida = cant * 0.92;
					} else if (usdD.isSelected()) {
						salida = cant;
					} else if (destino.getSelection() == btcD) {
						salida = cant * 0.000015;
					}
				}
				if (btcO.isSelected()) {
					if(eurD.isSelected()) {
						salida = cant * 62100;
					} else if (usdD.isSelected()) {
						salida = cant * 67500;
					} else if (btcD.isSelected()) {
						salida = cant;
					}
				}
				
				if(eurO.isSelected()) {
					moneda.setText("EURO");
				} else if (usdO.isSelected()) {
					moneda.setText("DOLAR");
				} else if (btcO.isSelected()) {
					moneda.setText("BITCOIN");
				}
				String texto = "";
				if(eurD.isSelected()) {
					texto = "EURO";
				} else if (usdD.isSelected()) {
					texto = "DOLAR";
				} else if (btcD.isSelected()) {
					texto = "BITCOIN";
				}
				
				convertido.setText(salida + texto);
				escribirLog(salida + texto);
			}
		});
		
		
		
		this.add(title, BorderLayout.NORTH);
		this.add(monedasOrigen, BorderLayout.WEST);
		this.add(monedasDestino, BorderLayout.EAST);
		this.add(centro, BorderLayout.CENTER);
		this.add(sur, BorderLayout.SOUTH);
	}
	
	public void escribirLog(String dato) {
		FileWriter writer;
		try {
			File file = new File("Converter.log");
			writer = new FileWriter("Converter.log");
			FileReader reader = new FileReader("Converter.log");
			String text = "";
			for(int i = 0; i < file.length(); i++) {
				if (reader.read() != -1) {
					text += reader.read();
				}
			}
			writer.write(text + dato);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
}
