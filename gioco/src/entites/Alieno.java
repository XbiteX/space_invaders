package entites;

import javax.swing.ImageIcon;

import risorse.Costanti;

public class Alieno extends Entita {

	
/**** COSTRUTTORE ****/
	
	public Alieno(int xPos, int yPos, String frame1, String frame2) {
		
		// Inizializzazione delle variabili della superclasse
		super.xPos = xPos;
		super.yPos = yPos;
		super.larghezza = Costanti.LARGHEZZA_ALIENO;
		super.altezza = Costanti.ALTEZZA_ALIENO;
		super.dx = 0;
		super.dy = 0;
		super.viva = true;
		// Percorsi delle immagini degli alieni
		super.frame1 = frame1;
		super.frame2 = frame2;
		super.frame3 = "/images/alienoMorto.png";
		// Chargement de l'image de l'alien
		super.ico = new ImageIcon(getClass().getResource(super.frame1));
		super.img = this.ico.getImage();
	}
	
	
/**** METODI ****/
	public void sceltaImmagine(boolean pos1) {
		// Metodo che carica l'immagine dell'alieno secondo il suo stato e la sua posizione (1 o 2)
		if(this.viva) {
		 if(pos1) {
			 super.ico = new ImageIcon(getClass().getResource(frame1));
		 }
		  else {
			  super.ico = new ImageIcon(getClass().getResource(frame2));
		  }
		}
		else {
			super.ico = new ImageIcon(getClass().getResource(frame3));
		}
		super.img = this.ico.getImage(); // ricarico immagine
	}

}
