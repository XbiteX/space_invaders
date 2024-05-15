package entites;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import gioco.Main;
import risorse.Cronometro;
import risorse.Costanti;

public class Astronave extends Entita {
	
	/**** VARIABILI ****/
	private int contatore = 0;
	
	/**** COSTRUTTORE ****/
		public Astronave() {
			
			// Inizializzazione delle variabili della super classe
			super.xPos = Costanti.X_POS_INIIZIALE_NAVICELLA;
			super.yPos = Costanti.Y_POS_NAVICELLA;
			super.larghezza = Costanti.LARGHEZZA_NAVICELLA;
			super.altezza = Costanti.ALTEZZA_NAVICELLA;
			super.dx = 0;
			super.dy = 0;
			// Percorsi delle immagini della superclasse
			super.frame1 = "/images/navicella.png";
			super.frame2 = "/images/detritiNavicella1.png";
			super.frame3 = "/images/detritiNavicella2.png";
			// Caricamento delle immagini della navicella
			super.ico = new ImageIcon(getClass().getResource(super.frame1));
			super.img = this.ico.getImage();
			super.viva = true;
		}
		
		
	/**** METODI ****/
	public int spostamentoNavicella() {
		// Restituisce la nuova posizione della nave dopo lo spostamento
		if(this.dx < 0){
			if(this.xPos > Costanti.LIMITE_SINISTRO_NAVICELLA) {
				this.xPos = this.xPos + this.dx;
			}
		}else if(dx > 0) {
			if(this.xPos + this.dx < Costanti.LIMITE_DESTRO_NAVICELLA) {
				this.xPos = this.xPos + this.dx;
			}
		}
		return this.xPos;
	}
	
	public void disegnoNavicella(Graphics g) {
		if(!this.viva) {
			this.distruzioneNavicella();
		}
		g.drawImage(this.img, this.spostamentoNavicella(), this.yPos, null);
	}
	
	public void distruzioneNavicella() {
		if(contatore < 300) {
			if(Cronometro.contaGiri % 2 == 0) {
				super.ico = new ImageIcon(getClass().getResource(super.frame2));
			}
			else
				super.ico = new ImageIcon(getClass().getResource(super.frame3));
			contatore++;
		} else
			Main.gioco = false;
		super.img = this.ico.getImage();
	}
}
