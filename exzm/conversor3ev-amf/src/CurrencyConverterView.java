import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CurrencyConverterView extends JFrame{
	public JTextField amountField;
	public JButton convertButton;
	public JLabel resultLabel;
	public ButtonGroup fromGroup;
	public ButtonGroup toGroup;
	
public CurrencyConverterView() {
	setTitle("Conversor de Monedas");
	setSize(400,300);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setLayout(new GridLayout(5, 2));
	
	add(new JLabel("Monto:"));
	amountField = new JTextField();
	add(amountField);
	
	add(new JLabel("De:"));
	fromGroup = new ButtonGroup();
	JPanel fromPanel = new JPanel ();
	fromPanel.add(createRadioButton(fromGroup, "EUR"));
	fromPanel.add(createRadioButton(fromGroup, "USD"));
	fromPanel.add(createRadioButton(fromGroup, "BTC"));
	add(fromPanel);
	
	add(new JLabel("A:"));
	toGroup = new ButtonGroup();
	JPanel toPanel = new JPanel ();
	toPanel.add(createRadioButton(toGroup, "EUR"));
	toPanel.add(createRadioButton(toGroup, "USD"));
	toPanel.add(createRadioButton(toGroup, "BTC"));
	add(toPanel);
	
	convertButton = new JButton ("Convertir");
	add(convertButton);
	
	resultLabel = new JLabel ("Resultado");
	resultLabel.setOpaque(true);
	resultLabel.setBackground(Color.YELLOW);
	add(resultLabel);
	
}

private Component createRadioButton(ButtonGroup fromGroup2, String string) {
	return null;
}

}
