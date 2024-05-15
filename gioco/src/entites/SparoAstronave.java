package entites;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import gioco.Main;
import risorse.Audio;
import risorse.Costanti;

public class SparoAstronave extends Entita {
	
	/**** VARIABILI ****/
	
	private boolean sparoNavicella = false;
	

	/**** COSTRUTTORE ****/
	
	public SparoAstronave() {
		
		// Inizializzazione delle variabili della super classe
		super.xPos = 0;
		super.yPos = Costanti.Y_POS_NAVICELLA - Costanti.ALTEZZA_SPARO_NAVICELLA;
		super.larghezza = Costanti.LARGHEZZA_SPARO_NAVICELLA;
		super.altezza = Costanti.ALTEZZA_SPARO_NAVICELLA;
		super.dx = 0;
		super.dy = Costanti.DY_SPARO_NAVICELLA;
		// Percorsi delle immagini
		super.frame1 = "/images/tiroNavicella.png";
		super.frame2 = "";
		super.frame3 = "";
		// Caricamento delle immagini della navicella
		super.ico = new ImageIcon(getClass().getResource(super.frame1));
		super.img = this.ico.getImage();
	}
	
	/**** METODI ****/
	public boolean isSparoNavicella() {return sparoNavicella;}

	public void setSparoNavicella(boolean sparoNavicella) {this.sparoNavicella = sparoNavicella;}

	public int spostamentoSparoNavicella() {
		if(this.sparoNavicella) {
			if(this.yPos > 0) {
				this.yPos = this.yPos - Costanti.DY_SPARO_NAVICELLA;
			}
			else {this.sparoNavicella = false;}
		}		
		return yPos;
	}
	
	public void disegnoSparoNavicella(Graphics g) {
		if(this.sparoNavicella) {
			g.drawImage(this.img, this.xPos, this.spostamentoSparoNavicella(), null);}
	}
	
	public boolean uccideAlieno(Alieno alieno) {
		// lo sparo della navicella distrugge l'alieno
		if(this.yPos < alieno.getyPos() + alieno.getAltezza()
			&& this.yPos + this.altezza > alieno.getyPos()
			&& this.xPos + this.larghezza > alieno.getxPos()
			&& this.xPos < alieno.getxPos() + alieno.getLarghezza()){
			Audio.playSound("/suoni/suonoAlienoMorto.wav");
				return true;
			} 
		else{return false;}
	}
	
	private boolean sparoNavicellaAltezzaBarriera() {
		// Restituisce vero se il colpo della nave è all'altezza delle barriere
		return this.yPos < Costanti.Y_POS_BARRIERE + Costanti.ALTEZZA_BARRIERA && this.yPos + this.altezza > Costanti.Y_POS_BARRIERE;
	}
	
	private int barrieraVicina() {
		// Restituisce il numero della barriera (0,1,2 o 3) nella zona di tiro della navicella
		int numeroBarriera = -1;
		int colonne = -1;
		while (numeroBarriera == -1 && colonne < 4) {
			colonne++;			
			if(this.xPos + this.larghezza > Costanti.MARGINE_FINESTRA + Costanti.X_POS_INIT_BARRIERA + colonne *
					(Costanti.LARGHEZZA_BARRIERA + Costanti.GAP_BARRIERE)
			   && this.xPos < Costanti.MARGINE_FINESTRA + Costanti.X_POS_INIT_BARRIERA + Costanti.LARGHEZZA_BARRIERA + colonne *
			   (Costanti.LARGHEZZA_BARRIERA + Costanti.GAP_BARRIERE)) {
				numeroBarriera = colonne;
			}
		}
		return numeroBarriera;
	}
		
	private int ascissaContattoTironavicellaBarriera(Barriera barriera) {
		// Ritorna l'ascissa quando il tiro della navicella collide co
		int xPosTirVaisseau = -1;
		if(this.xPos + this.larghezza > barriera.getxPos() && this.xPos < barriera.getxPos() + Costanti.LARGHEZZA_BARRIERA){xPosTirVaisseau = this.xPos;}
		return xPosTirVaisseau;
	}
	
	public int[] tiroNavicellaContattoBarriera() {
		// Restituisce il numero della barriera e l'ascissa al contatto
		int[] ritorno = {-1, -1};
		if(this.sparoNavicellaAltezzaBarriera()) {
			ritorno[0] = this.barrieraVicina(); // registra il numero della barriera in ritorno[0]
			if(ritorno[0] != -1) {
				//registra l'ascissa del tiro della navicella durante il contatto con la barriera in ritorno[1]
				ritorno[1] = this.ascissaContattoTironavicellaBarriera(Main.scena.barriera[ritorno[0]]);
			}		 
		}		
		return ritorno;
	}
	
	public void tiroNavicellaDistruggebarriera(Barriera[] barriera) {
		int[] tab = this.tiroNavicellaContattoBarriera(); // Contiene il numero della barriera e l'ascissa del contatto con il tiro della navicella
		if(tab[0] != -1) { // Vuol dire che viene colpita una abrriera
			if(barriera[tab[0]].ricercaMattoneColpitoDaNaviella(barriera[tab[0]].ricercaBarrieraColpitaDaNavicella(tab[1])) != -1) {
				barriera[tab[0]].rotturaMattoni(tab[1]); // Distrutti i mattoni del castello colpito
				this.yPos = -1; // Eliminazione del tiro
			}
		}
	}
	
	public boolean distruggeUfo(Disco disco) {
		// Contatto tra il tiro e l'ufo
		if(this.yPos < disco.getyPos() + disco.getAltezza() && this.yPos + this.altezza > disco.getyPos() && this.xPos + this.larghezza > disco.getxPos() && this.xPos < disco.getxPos() + disco.getLarghezza()){
				this.yPos=2000;
				this.sparoNavicella = false; // Eliminazione del colpo
				return true;
			} 
		else{return false;}
	}
}
