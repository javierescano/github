//Luis López Martínez
package llm;

import javax.swing.JFrame;

public class Principal {

	public static void main(String[] args) {
		VentanaConversor conversor = new VentanaConversor();
		conversor.setBounds(600, 600, 600, 300);
		conversor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		conversor.setVisible(true);

	}

}
