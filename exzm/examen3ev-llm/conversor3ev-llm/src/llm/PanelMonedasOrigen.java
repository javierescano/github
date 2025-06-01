package llm;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PanelMonedasOrigen extends JPanel {
	public PanelMonedasOrigen() {
		
		JLabel titulo = new JLabel("MONEDA ORIGEN");
		ButtonGroup monedas = new ButtonGroup();
		JRadioButton eur = new JRadioButton("EUR");
		JRadioButton usd = new JRadioButton("USD");
		JRadioButton btc = new JRadioButton("BTC");
		monedas.add(eur);
		monedas.add(usd);
		monedas.add(btc);
		this.add(titulo);
		this.add(eur);
		this.add(usd);
		this.add(btc);
	}
}
