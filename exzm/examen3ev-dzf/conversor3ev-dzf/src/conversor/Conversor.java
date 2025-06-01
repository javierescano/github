package conversor;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Conversor  extends JFrame{

	
	public Conversor() {
		JFrame frame = new JFrame();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 500, 200);
		
	
		//add(textoArriba(),BorderLayout.NORTH);
		add(elegirMoneda(),BorderLayout.AFTER_LAST_LINE);
		add(panelAbajo(),BorderLayout.LINE_START);
		
		//JPanel textoArriba= new JPanel();
		
		
		setLayout(new BorderLayout());
		
		
		
	
		
		
		
		setVisible(true);
	}
	
	public JPanel elegirMoneda() {
		JPanel panel = new JPanel();
		ButtonGroup monedas = new ButtonGroup();
		
		JRadioButton eur = new JRadioButton("EUR");
		JRadioButton dolares = new JRadioButton("DOLARES");
		JRadioButton bitcoins = new JRadioButton("BITCOINS");
		
		monedas.add(eur);
		monedas.add(dolares);
		monedas.add(bitcoins);
		
		
		return panel;
		
		
	}
	public JPanel panelAbajo() {
		JPanel panel = new JPanel();
	//	JLabel 
		
		
		
		return null;
		
	
	}
	
	
	
	
}
