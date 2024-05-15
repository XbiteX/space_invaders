package entites;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

import gioco.Main;
import risorse.Audio;
import risorse.Cronometro;
import risorse.Costanti;

public class SparoAlieno extends Entita {

/**** VARIABILI ****/
	
	Random random = new Random();


/**** COSTRUTTORE ****/
	
	public SparoAlieno(int [] posizioneAlienoCheSpara) {
		
		// Inizializzazione delle variabili della superclasse
		super.xPos = posizioneAlienoCheSpara[0] + Costanti.LARGHEZZA_ALIENO /2 - 1;
		super.yPos = posizioneAlienoCheSpara[1] + Costanti.ALTEZZA_ALIENO;
		super.larghezza = Costanti.LARGHEZZA_TIRO_ALIENO;
		super.altezza = Costanti.ALTEZZA_TIRO_ALIENO;
		super.dx = 0;
		super.dy = Costanti.DY_TIRO_ALIENO;
		// percorsi delle immagini delgli spari
		super.frame1 = "/images/sparoAlieno1.png";
		super.frame2 = "/images/sparoAlieno2.png";
		super.frame3 = "";
		//Caricamento dell'immagine dello sparo dello alieno
		if(random.nextInt(2) == 0) {
			super.ico = new ImageIcon(getClass().getResource(super.frame1));}
		else {
			super.ico = new ImageIcon(getClass().getResource(super.frame2));}
		super.img = this.ico.getImage();
	}
	
	
/**** METODI ****/
	
	public int spostamentoSparoALieno() {
		if(Cronometro.contaGiri % 4 == 0) {
			if(this.yPos < 900) {
				this.yPos = this.yPos + Costanti.DY_TIRO_ALIENO;
			}
		}
		return yPos;
	}	
	
	public void disegnoSparoAlieno(Graphics g) {
		g.drawImage(this.img, this.xPos, this.spostamentoSparoALieno(), null);
	}		
	
	private boolean sparoAlienoALtezzaBarriere() {
		//Restituisce vero se il tiro delgli alieni è all'altezza delle barriere
		return this.yPos < Costanti.Y_POS_BARRIERE + Costanti.ALTEZZA_BARRIERA && this.yPos + this.altezza > Costanti.Y_POS_BARRIERE;
	}
	
	private int barrieraVicina() {
		// Restituisce il numero della barriera (0,1,2 o 3) nella zona di tiro del alieno
		int numeroBarriera = -1;
		int colonne = -1;
		while (numeroBarriera == -1 && colonne < 4) {
			colonne++;			
			if(this.xPos + this.larghezza - 1 > Costanti.MARGINE_FINESTRA + Costanti.X_POS_INIT_BARRIERA + colonne * (Costanti.LARGHEZZA_BARRIERA + Costanti.GAP_BARRIERE)
			   && this.xPos + 1 < Costanti.MARGINE_FINESTRA + Costanti.X_POS_INIT_BARRIERA + Costanti.LARGHEZZA_BARRIERA + colonne * (Costanti.LARGHEZZA_BARRIERA + Costanti.GAP_BARRIERE)) {
				numeroBarriera = colonne;
			}
		}
		return numeroBarriera;
	}
	
	private int ascissaContattoTiroAlienoBarriera(Barriera barriera) {
		int xPosTirAlien = -1;
		if(this.xPos + this.larghezza > barriera.getxPos() && this.xPos < barriera.getxPos() + Costanti.LARGHEZZA_BARRIERA){
			xPosTirAlien = this.xPos;
		}
		return xPosTirAlien;
	}
	
	public int[] tiroAlienoContattoBarriera() {
		//Restituisce il numero della berriera colpita e l'ascissa del tiro
		int[] ritorno = {-1,-1};
		if(this.sparoAlienoALtezzaBarriere()) {
			ritorno[0] = this.barrieraVicina(); // registra i valori in ritorno[]
			if(ritorno[0] != -1) {
				ritorno[1] = this.ascissaContattoTiroAlienoBarriera(Main.scena.barriera[ritorno[0]]);
			}
		}		
		return ritorno;
	}	
	
	public void tiroAlienoDistruggeBarriera(Barriera[] tabBarrieras) {
		int[] array = this.tiroAlienoContattoBarriera(); // Contiene il numero del castello colpito e l'ascissa del tiro
		if(array[0] != -1) { //Viene colpita una barriera
			if(tabBarrieras[array[0]].ricercaMattoneColpitaDaAlieno(tabBarrieras[array[0]].ricercaBarrieraColpitaDaNavicella(array[1])) != -1
				&& tabBarrieras[array[0]].ricercaMattoneColpitaDaAlieno(tabBarrieras[array[0]].ricercaBarrieraColpitaDaNavicella(array[1])) != 27) {
				tabBarrieras[array[0]].rotturaMattoneColpitoDaAlieno(array[1]); //Distruzione dei mattoni della barriera colpita
				this.yPos = 1000; // Eliminazione colpo alieno
			}
		}
	}
	
	public boolean contattoNavicella(Astronave astronave) {
		//Restituisce vero se un colpo alieno colpisce la navicella
		if(this.yPos < astronave.getyPos() + astronave.getAltezza() && this.yPos + this.altezza > astronave.getyPos()
			&& this.xPos + this.larghezza > astronave.getxPos() && this.xPos < astronave.getxPos() + astronave.getLarghezza()){
			    this.yPos = 700;
			    Audio.playSound("/suoni/suonoDistruzioneNavicella.wav");
				return true;
			} 
		else{return false;}
	}
}
