package gioco;

import javax.swing.*;
import risorse.Costanti;
import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Main {
	
/**** VARIABILI ****/
	
	public static Scena scena;
	public static boolean gioco = true;
	
	
/**** METODI ****/
	public static void main(String[] args) {

		JFrame frame = new JFrame("Menu");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Imposta il layout del pannello


		JButton button1 = new JButton("NEW GAME");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				creazioneFinestraGioco(frame);
			}
		});

		menuPanel.add(button1); // Aggiungi il pulsante al pannello

		JButton button2 = new JButton("EXIT");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				System.exit(0);
			}
		});
		menuPanel.add(button2); // Aggiungi il pulsante al pannello

		frame.getContentPane().add(menuPanel, BorderLayout.WEST); // Aggiungi il pannello al frame
		frame.setSize(300, 200); // Imposta le dimensioni del frame
		int[] posizioni = posizioni();
		frame.setLocation(posizioni[0]/2-(300/2), posizioni[1]/2-(200/2)); // imposta posizione dello schermo
		frame.setVisible(true); // Rendi il frame visibile


		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); // Utilizza BoxLayout per centrare il menuPanel verticalmente
		centerPanel.add(Box.createVerticalGlue()); // Aggiunge spazio sopra
		centerPanel.add(menuPanel);
		centerPanel.add(Box.createVerticalGlue()); // Aggiunge spazio sotto

		frame.add(centerPanel, BorderLayout.CENTER);



	}

	private static int[] posizioni() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int[] posizioni = {0,0};
		posizioni[0] = screenSize.width;
		posizioni[1] = screenSize.height;
		return posizioni;
	}

	private static void creazioneFinestraGioco(JFrame frame) {
		frame.dispose();
		JFrame finestra = new JFrame("Space Invaders");
		finestra.setSize(Costanti.LARGHEZZA_FINESTRA, Costanti.ALTEZZA_FINESTRA);
		finestra.setResizable(false);
		finestra.setLocationRelativeTo(null);
		finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		finestra.setAlwaysOnTop(true);

		// creazione della finestra
		scena = new Scena();
		finestra.setContentPane(scena);

		finestra.setVisible(true);
		// Creazione della finestra per il gioco
	}

}
