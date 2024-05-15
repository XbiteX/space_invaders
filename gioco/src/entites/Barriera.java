package entites;

import java.awt.Color;
import java.awt.Graphics;

import risorse.Audio;
import risorse.Costanti;

public class Barriera extends Entita {

	/**** VARIABILI ****/
	
	private final int NUMERO_RIGHE = Costanti.ALTEZZA_BARRIERA / Costanti.DIMENSIONI_MATTONE;
	private final int NUMERO_COLONNE = Costanti.LARGHEZZA_BARRIERA / Costanti.DIMENSIONI_MATTONE;
	
	// ARRAY contenente i mattoni del castello (la casella contiene true per un mattone e false per vuoto)
	boolean[][] matriceBarriere = new boolean[NUMERO_RIGHE][NUMERO_COLONNE];
	
/**** COSTRUTTORE ****/
	
	public Barriera(int xPos) {
		super.xPos = xPos; // Ascissa del punto più a sinistra della barriera
		super.yPos = Costanti.Y_POS_BARRIERE; // Ordinata el punto più in alto della barriera
		
		this.inizializzazioneBarriere();
	}
	
/**** METODI ****/
	
	// Creazione iniziale delle barriere
	public void inizializzazioneBarriere() {
		// Riempimento delle barriere con true
		for(int righe = 0; righe < NUMERO_RIGHE; righe++) {
			for(int colonne = 0; colonne < NUMERO_COLONNE; colonne++) {
				matriceBarriere[righe][colonne]= true;
			}	
		}

		// Riempiamo tutte le caselle senza mattoni nella tabella con false


		// Smussatura della sommità della berriera
		for(int colonne=0; colonne < 6; colonne++) {
			for(int righe=0; righe < 2; righe++) {
				matriceBarriere[righe][colonne]= false;
				matriceBarriere[righe][NUMERO_COLONNE -colonne-1]= false;
			}
		}
		for(int colonne=0; colonne < 4; colonne++) {
			for(int righe=2; righe < 4; righe++) {
				matriceBarriere[righe][colonne]= false;
				matriceBarriere[righe][NUMERO_COLONNE -colonne-1]= false;
			}
		}
		for(int colonne=0; colonne < 2; colonne++) {
			for(int righe=4; righe < 6; righe++) {
				matriceBarriere[righe][colonne]= false;
				matriceBarriere[righe][NUMERO_COLONNE -colonne-1]= false;
			}
		}

		// rientranza delle barriere
		for(int righe = 10; righe < NUMERO_RIGHE; righe++) {
			for(int colonne = 8; colonne < NUMERO_COLONNE -8; colonne++) {
				matriceBarriere[righe][colonne]= false;
			}
		}
		// smussamento della rientranza
		for(int colonne = 6; colonne < NUMERO_COLONNE -6; colonne++) {
			for(int righe=12; righe < NUMERO_RIGHE; righe++) {
				matriceBarriere[righe][colonne]= false;
			}
		}
		for(int colonne = 4; colonne < NUMERO_COLONNE -4; colonne++) {
			for(int righe=14; righe < NUMERO_RIGHE; righe++) {
				matriceBarriere[righe][colonne]= false;
			}
		}
	}
	
	// Disegno delle barriere
	public void disegnoBarriera(Graphics g2) {
		for(int righe = 0; righe < NUMERO_RIGHE; righe++) {
			for(int colonne = 0; colonne < NUMERO_COLONNE; colonne++) {
				if(matriceBarriere[righe][colonne]) {
					g2.setColor(new Color(144, 3, 217));
				} else {
					g2.setColor(new Color(0, 43, 64, 0));
				}
				g2.fillRect(this.xPos + Costanti.DIMENSIONI_MATTONE *colonne, this.yPos + Costanti.DIMENSIONI_MATTONE *righe, Costanti.DIMENSIONI_MATTONE, Costanti.DIMENSIONI_MATTONE);
			}				
		}
	}
	
	public int ricercaBarrieraColpitaDaNavicella(int xMissile) {
		// ritorna la colonna della matrice della barriera colpita dallo sparo
		int colonna = -1;
		colonna = (xMissile - this.xPos) / Costanti.DIMENSIONI_MATTONE;
		return colonna;
	}
	
	public int ricercaMattoneColpitoDaNaviella(int colonne) {
		// Trova il primo mattone in fondo alla colonna della matrice della barriera o restituisci -1
		int riga = NUMERO_RIGHE -1;
		while(riga >= 0 && !matriceBarriere[riga][colonne]) {
			riga--;
		}
		return riga;
	}
	
	private void rimuoviMattone(int riga, int colonne) {
		// Eliminazione dei primi 6 mattoncini della colonna partendo dal basso se presenti
		for(int i=0; i < 6; i++) {
			if(riga - i >= 0) {
				matriceBarriere[riga - i][colonne] = false;
				if(colonne < NUMERO_COLONNE - 1) {
					matriceBarriere[riga - i][colonne + 1] = false;}
			}			
		}	
	}
	
	public void rotturaMattoni(int xTir) {
		Audio.playSound("/suoni/distruzioneMattone.wav");
		int colonne = this.ricercaBarrieraColpitaDaNavicella(xTir);
		this.rimuoviMattone(ricercaMattoneColpitoDaNaviella(colonne), colonne);
	}	
	
	public int ricercaMattoneColpitaDaAlieno(int colonne) {
		// Trova il primo mattone partendo dalla parte superiore della colonna della matrice della barriera o restituisce -1
		int riga = 0;
		if(colonne != -1) {
		  while(riga < NUMERO_RIGHE && !matriceBarriere[riga][colonne]) {
			  riga++;
		  }
		}
		return riga;
	}
	
	private void rimuoviMattoneColpitoDaAlino(int riga, int colonne) {
		// Eliminazione dei primi 6 mattoni della colonna partendo dall'alto se presenti
		for(int i=0; i < 6; i++) {
			if(riga + i < NUMERO_RIGHE && colonne != -1) {
				matriceBarriere[riga + i][colonne] = false;
				if(colonne < NUMERO_COLONNE - 1) {
					matriceBarriere[riga + i][colonne + 1] = false;
				}
			}			
		}	
	}
	
	public void rotturaMattoneColpitoDaAlieno(int xTir) {
		Audio.playSound("/suoni/distruzioneMattone.wav");
		int colonne = this.ricercaBarrieraColpitaDaNavicella(xTir);
		this.rimuoviMattoneColpitoDaAlino(ricercaMattoneColpitaDaAlieno(colonne), colonne);
	}	
}
