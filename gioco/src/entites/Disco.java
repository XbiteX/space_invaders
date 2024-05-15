package entites;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import risorse.Audio;
import risorse.Cronometro;
import risorse.Costanti;

public class Disco extends Entita {

	/**** VARIABILI ****/

	public Audio suonoDisco = new Audio("/suoni/suonoPassaggioUfo.wav");
	public Audio suonoDistruzioneDisco = new Audio("/suoni/suonoDistruzioneUfo.wav");

	private int i = 0;
	
	
/**** COSTRUTTORE ****/
	
	public Disco() {
		// Inizializzazione delle variabili della superclasse
		super.xPos = Costanti.X_POS_INIT_DISCO;
		super.yPos = Costanti.Y_POS_DISCO;
		super.larghezza = Costanti.LARGHEZZA_DISCO;
		super.altezza = Costanti.ALTEZZA_DISCO;
		super.dx = Costanti.DX_DISCO;
		super.dy = 0;
		// Percorsi delle immagini del disco
		this.frame1 = "/images/UFO.png";
		this.frame2 = "/images/punteggioUFO.png";
		this.frame3 = "";
		// Caricamento dell'immagine della navicella
		super.ico = new ImageIcon(getClass().getResource(frame1));
		super.img = this.ico.getImage();
		super.viva = true;

		this.suonoDisco.play();
		this.suonoDistruzioneDisco.stop();
		this.i = 0;
	}
	
/**** METODI
 *  ****/
	
	public int spostamentoDisco() {
		//Restituisce la nuova posizione del disco dopo l'eventuale spostamento
		if(this.viva && Cronometro.contaGiri % 2 == 0) {
			if (this.xPos > 0) {
				this.xPos = this.xPos - this.dx;
			}
			else {
				this.xPos = Costanti.X_POS_INIT_DISCO;
			}
		}
		return this.xPos;
	}
		
	public void disegnoDisco(Graphics g) {
		if(!this.viva) {
			this.distruzioneDisco();
		}
		g.drawImage(this.img, this.spostamentoDisco(), this.yPos, null);
	}	
	
	public void distruzioneDisco() {
		if(i < 300) {
			super.ico = new ImageIcon(getClass().getResource(super.frame2));
			super.img = this.ico.getImage();
			i++;
		}else {
			this.xPos = Costanti.X_POS_INIT_DISCO;
		}
		
	}
}
