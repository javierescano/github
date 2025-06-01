import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

// Diego Vega Silva	

public class ConversorMonedas extends JFrame {

	private JLabel lblResultado;
	private JLabel lblUnidadDestino;
	private JTextField txtCantidad;

	private JRadioButton rbOrigenEUR, rbOrigenUSD, rbOrigenBTC;
	private JRadioButton rbDestinoEUR, rbDestinoUSD, rbDestinoBTC;

	public ConversorMonedas() {
		setTitle("Conversor de Monedas");
		setSize(600, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		JLabel lblTitulo = new JLabel("CONVERSOR DE MONEDAS", JLabel.CENTER);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 30));
		add(lblTitulo, BorderLayout.NORTH);

		JPanel panelIzquierda = new JPanel(new GridLayout(4, 1));
		panelIzquierda.add(new Label("MONEDA ORIGEN:"));
		rbOrigenEUR = new JRadioButton("EUR");
		rbOrigenUSD = new JRadioButton("USD");
		rbOrigenBTC = new JRadioButton("BTC");

		ButtonGroup grupoOrigen = new ButtonGroup();
		grupoOrigen.add(rbOrigenEUR);
		grupoOrigen.add(rbOrigenUSD);
		grupoOrigen.add(rbOrigenBTC);

		panelIzquierda.add(rbOrigenEUR);
		panelIzquierda.add(rbOrigenUSD);
		panelIzquierda.add(rbOrigenBTC);

		add(panelIzquierda, BorderLayout.WEST);

		JPanel panelDerecha = new JPanel(new GridLayout(4, 1));
		panelDerecha.add(new Label("MONEDA DESTINO:"));
		rbDestinoEUR = new JRadioButton("EUR");
		rbDestinoUSD = new JRadioButton("USD");
		rbDestinoBTC = new JRadioButton("BTC");

		ButtonGroup grupoDestino = new ButtonGroup();
		grupoDestino.add(rbDestinoEUR);
		grupoDestino.add(rbDestinoUSD);
		grupoDestino.add(rbDestinoBTC);

		panelDerecha.add(rbDestinoEUR);
		panelDerecha.add(rbDestinoUSD);
		panelDerecha.add(rbDestinoBTC);

		add(panelDerecha, BorderLayout.EAST);

		JPanel panelCentro = new JPanel(new BorderLayout());
		lblResultado = new JLabel("0.00", JLabel.CENTER);
		lblResultado.setFont(new Font("Arial", Font.BOLD, 30));
		lblResultado.setOpaque(true);
		lblResultado.setBackground(Color.YELLOW);
		panelCentro.add(lblResultado, BorderLayout.CENTER);
		
		lblUnidadDestino = new JLabel("", JLabel.CENTER);
		panelCentro.add(lblUnidadDestino, BorderLayout.SOUTH);
		
		add(panelCentro, BorderLayout.CENTER);

		JPanel panelSur = new JPanel();
		panelSur.add(new JLabel("Cantidad:"));
		txtCantidad = new JTextField(10);
		panelSur.add(txtCantidad);
		
		JButton btnConvertir = new JButton("CONVERTIR");
		panelSur.add(btnConvertir);
		
		add(panelSur, BorderLayout.SOUTH);
		
		
		ActionListener actualizar = e -> {
			if (rbOrigenEUR.isSelected()) {
				lblUnidadDestino.setText("EUROS");
			}else if (rbOrigenUSD.isSelected()) {
				lblUnidadDestino.setText("USD");
			}else if (rbOrigenBTC.isSelected()) {
				lblUnidadDestino.setText("BTC"); 
			}
		}; 
		
		rbOrigenEUR.addActionListener(actualizar);
		rbOrigenUSD.addActionListener(actualizar);
		rbOrigenBTC.addActionListener(actualizar);
		
		btnConvertir.addActionListener(e -> convertir() );
		
		setVisible(true);

	}
	
	private void convertir() {
		
		String origen = null;
		String destino = null;
		String resultadoTexto = null;
		
		try {
			origen = getMonedaSeleccionada(rbOrigenEUR, rbOrigenUSD, rbOrigenBTC);
			destino = getMonedaSeleccionada(rbDestinoEUR, rbDestinoUSD, rbDestinoBTC);
			
			if (origen == null || destino == null) {
				JOptionPane.showMessageDialog(this, "Selecciona moneda origen y destino");
			}
			double cantidad = Double.parseDouble(txtCantidad.getText());
			double resultado = convertirMoneda(origen, destino, cantidad);
			
			resultadoTexto = String.format("%s %s ", resultado, destino);
			lblResultado.setText(resultado + " " + destino);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Introduce una cantidad valida");
		
		}
		guardarEnLog(origen, destino, txtCantidad.getText(), resultadoTexto);
	}
	
	private String getMonedaSeleccionada(JRadioButton eur, JRadioButton usd, JRadioButton btc) {
		if(eur.isSelected()) return "EUR";
		if(usd.isSelected()) return "USD";
		if(btc.isSelected()) return "BTC";
		return null;
	}
	
	private double convertirMoneda(String origen, String destino, double cantidad) {
		double tasa = 1.0;
		
		switch (origen + "-" + destino) {
		case "EUR-USD": tasa = 1.08;			
			break;
		case "EUR-BTC": tasa = 0.000016;			
			break;	
		case "USD-EUR": tasa = 0.92;			
			break;
		case "USD-BTC": tasa = 0.000015;			
			break;
		case "BTC-EUR": tasa = 62100;			
			break;
		case "BTC-USD": tasa = 67500;			
			break;
		}
		return cantidad * tasa;
	}
	
	private void guardarEnLog(String origen, String destino, String cantidadTexto, String resultadoTexto) {
		if (origen == null || destino == null || cantidadTexto == null || resultadoTexto == null) {
			System.out.println("no se puede guardar. datos incompletos ");
		}
		
		try (FileWriter fw = new FileWriter("Converter.log", true);
			PrintWriter pw = new PrintWriter(fw)) {
				pw.printf("Conversion: %s %s = %s%n", cantidadTexto, origen, resultadoTexto);			
		} catch (IOException e) {
			System.out.println("Error al guardar");
		}
	}

	public static void main(String[] args) {

		new ConversorMonedas();
	
	}
}
