package entites;

import java.awt.Graphics;
import java.util.Random;

import gioco.Main;
import risorse.Audio;
import risorse.Cronometro;
import risorse.Costanti;

public class GruppoAlieni {

/**** VARIABILI ****/

	// Matrice contenente gli alieni (50)
	private final Alieno[][] matriceAlieni = new Alieno[5][10];
	private boolean vaDestra, pos1;
	private int velocita;

	private final int[] posizioneAlienoAppenaMorto = {-1, -1}; // Posizione dell'alieno morto nella tabella degli alieni
	
	Random random = new Random();
	
	private int numeroAlieni = Costanti.NUMERO_ALIENI;
	
	private int contatoreSuonoAlieni = 0;
	
	
/**** COSTRUTTORE ****/
	
	public GruppoAlieni() {
		
		this.initMatriceAlieni();
		this.vaDestra = true;
		this.pos1 =true;
		this.velocita = Costanti.VELOCITA_ALIENI;
	}

	
/**** METODI ****/
		
	private void initMatriceAlieni() {
		// metodo che riempie la matrice di alieni
		for(int colonne=0; colonne<10; colonne++) {
			this.matriceAlieni[0][colonne] = new Alieno(Costanti.X_POS_INIT_ALIEN + (Costanti.LARGHEZZA_ALIENO + Costanti.GAP_COLONNE_ALIENI) * colonne, Costanti.ALT_INIT_ALIEN, "/images/alien1_animazione1.png", "/images/alien1_animazione2.png");
			for(int riga=1; riga<3; riga++) {
				this.matriceAlieni[riga][colonne] = new Alieno(Costanti.X_POS_INIT_ALIEN + (Costanti.LARGHEZZA_ALIENO + Costanti.GAP_COLONNE_ALIENI) * colonne, Costanti.ALT_INIT_ALIEN + Costanti.GAP_LINEE_ALIENI * riga, "/images/alien1_animazione1.png", "/images/alien1_animazione2.png");
			}
			for(int riga=3; riga<5; riga++) {
				this.matriceAlieni[riga][colonne] = new Alieno(Costanti.X_POS_INIT_ALIEN + (Costanti.LARGHEZZA_ALIENO + Costanti.GAP_COLONNE_ALIENI) * colonne, Costanti.ALT_INIT_ALIEN + Costanti.GAP_LINEE_ALIENI * riga, "/images/alien1_animazione1.png", "/images/alien1_animazione2.png");
			}	
		}
	}
	
	public void disegnoAlieni(Graphics g) {
		if(Cronometro.contaGiri % (100 - 10 * this.velocita) == 0) {this.spostamentoAlieni();}
		// Disegno degli alieni contenuto nella matrice
		for(int colonne=0; colonne<10; colonne++) {
			for(int riga=0; riga<5; riga++) {
				if(this.matriceAlieni[riga][colonne] != null) {
					this.matriceAlieni[riga][colonne].sceltaImmagine(pos1);
					g.drawImage(this.matriceAlieni[riga][colonne].getImg(), this.matriceAlieni[riga][colonne].getxPos(), this.matriceAlieni[riga][colonne].getyPos(), null);
				}
			}
		}		
	}
		
	private boolean contattoBordoSinistro() {
		//Metodo che rileva il bordo sinistro della finestra
		boolean reponse = false;
		for(int colonne=0; colonne<10; colonne++) {
			for(int riga=0; riga<5; riga++) {
				if(this.matriceAlieni[riga][colonne] != null) {
					if(this.matriceAlieni[riga][colonne].getxPos() < Costanti.MARGINE_FINESTRA){
						reponse = true;
						break;
					}
				}
			}
		}
		return reponse;
	}
	
	private boolean contattoBordoDestro() {
		// Metodo che rileva il contatto col bordo destro della finestra
		boolean ritorno = false;
		for(int colonne=0; colonne<10; colonne++) {
			for(int righe=0; righe<5; righe++) {
				if(this.matriceAlieni[righe][colonne] != null) {
					if(this.matriceAlieni[righe][colonne].getxPos() >
					Costanti.LARGHEZZA_FINESTRA - Costanti.LARGHEZZA_ALIENO - Costanti.DX_ALIENO - Costanti.MARGINE_FINESTRA) {
						ritorno = true;
						break;
					}
				}
			}
		}
		return ritorno;
	}
	
	public void alieniGiraScendi() {
		// Metodo che cambia la direzione degli alieni e li fa scendere
		if(this.contattoBordoDestro()) {
			for(int colonne=0; colonne<10; colonne++) {
				for(int righe=0; righe<5; righe++) {
					if(this.matriceAlieni[righe][colonne] != null) {
						this.matriceAlieni[righe][colonne].setyPos(this.matriceAlieni[righe][colonne].getyPos() + Costanti.DY_ALIENO);
					}
				}
			}
			this.vaDestra = false;
			this.velocita++;

		} else {
			if(this.contattoBordoSinistro()) {
				for(int colonne=0; colonne<10; colonne++) {
					for(int righe=0; righe<5; righe++) {
						if(this.matriceAlieni[righe][colonne] != null) {
						this.matriceAlieni[righe][colonne].setyPos(
							this.matriceAlieni[righe][colonne].getyPos() + Costanti.DY_ALIENO);
						}
					}
				}
				this.vaDestra = true;
				this.velocita++;
			}
		}
	}
	
	public void spostamentoAlieni() {
		// Metodo che sposta gli alieni
		if(this.posizioneAlienoAppenaMorto[0] != -1) { // Eliminazione dell'alieno morto se necessario
			eliminazioneAlienoMorto(posizioneAlienoAppenaMorto);
			posizioneAlienoAppenaMorto[0] = -1; // Reinizializzazione
		}
		if(this.vaDestra) { // Movimento verso destra
			for(int colonne=0; colonne<10; colonne++) {
				for(int righe=0; righe<5; righe++) {
					if(this.matriceAlieni[righe][colonne] != null) {
						this.matriceAlieni[righe][colonne].setxPos(this.matriceAlieni[righe][colonne].getxPos() + Costanti.DX_ALIENO);
					}
				}
			}
		}else{ // Movimento verso sinistra
			for(int colonne=0; colonne<10; colonne++) {
				for(int righe=0; righe<5; righe++) {
					if(this.matriceAlieni[righe][colonne] != null) {
						this.matriceAlieni[righe][colonne].setxPos(this.matriceAlieni[righe][colonne].getxPos() - Costanti.DX_ALIENO);
					}
				}
			}
		}
		// suono degli alieni
		this.suonoAlieni();
		// Incremento del contatore per il suono
		this.contatoreSuonoAlieni++;
		// Cambio dell'imagine dell'alieno
		this.pos1 = !this.pos1;
		// Aggiorna la direzione del movimento se un alieno raggiunge il bordo della finestra
		this.alieniGiraScendi();
	}
	
	public void sparoNavicellaContattoAlieno(SparoAstronave sparoAstronave) {
		// Contatto tra lo sparo della navicella e gli alieni
		for(int colonne=0; colonne<10; colonne++) {
			for(int righe=0; righe<5; righe++) {
				if(this.matriceAlieni[righe][colonne] != null) {
					if(sparoAstronave.uccideAlieno(this.matriceAlieni[righe][colonne])) {
						this.matriceAlieni[righe][colonne].viva = false; // Effettiva uccisione dell'alieno
						sparoAstronave.yPos = -1; // eliminiamo il colpo
						// Registriamo la posizione dell'alieno morto
						this.posizioneAlienoAppenaMorto[0] = righe;
						this.posizioneAlienoAppenaMorto[1] = colonne;
						if(righe == 0) {
							Main.scena.score = Main.scena.score + Costanti.VALORE_ALIENO_ALTO;}
						else if(righe<3) {
							Main.scena.score = Main.scena.score + Costanti.VALORE_ALIENO_MEDIO;}
						else {
							Main.scena.score = Main.scena.score + Costanti.VALORE_ALIENO_BASSO;}
						break;
					}
				}					
			}					
		}
	}

	private void eliminazioneAlienoMorto(int[] coordinateAlienoMorto) {
		//Metodo che rimuove l'alieno morto dalla finestra (caso nullo)
		this.matriceAlieni[coordinateAlienoMorto[0]][coordinateAlienoMorto[1]] = null;
		this.numeroAlieni--;
	}
	
	public int[] sceltaAlienoTiratore() {

		// Restituisce la posizione di un alieno scelto casualmente nella matriceAlieni in fondo alla sua colonna (riga, colonna)

		int[] posizioneAlienoScelto = new int[2];

		if(this.numeroAlieni != 0) { // Controllo se ci sono ancora alieni vivi
			do {
				int colonne = random.nextInt(10); // Estrazione casuale di una colonna
				for(int righe=4;righe>=0;righe--) { // Ricerca del primo alieno vivo partendo dal basso
					if(matriceAlieni[righe][colonne]!= null) {
						posizioneAlienoScelto[0] = this.matriceAlieni[righe][colonne].getxPos();
						posizioneAlienoScelto[1] = this.matriceAlieni[righe][colonne].getyPos();
						break;
					}
				}
			} while(posizioneAlienoScelto[0] == -1);
		}	
		return posizioneAlienoScelto;
	}
	
	private void suonoAlieni() { // Metodo che riproduce il suono dell'alieno (4 suoni possibili)
		int compteur = this.contatoreSuonoAlieni % 4;
		if(compteur==0) {Audio.playSound("/suoni/suonoAlieno1.wav");}
		else if(compteur==1) {Audio.playSound("/suoni/suonoAlieno2.wav");}
		else if(compteur==2) {Audio.playSound("/suoni/suonoAlieno3.wav");}
		else {Audio.playSound("/suoni/suonoAlieno4.wav");}
	}
	
	public int getNumeroAlieni() {return numeroAlieni;}
	
	public int posizioneAlienoPiuBasso() {
		// Restituisce la riga dell'alieno più basso
		int temp = 0, posizoneAlienoPiuBasso = 0;
		for(int colonne=1; colonne<10;colonne++) {
			for(int righe=4; righe>=0;righe--) {
				if(this.matriceAlieni[righe][colonne] != null) {
					temp = this.matriceAlieni[righe][colonne].yPos + this.matriceAlieni[righe][colonne].altezza;
					break;
				}			
			}
			if(temp > posizoneAlienoPiuBasso) {
				posizoneAlienoPiuBasso = temp;
			}
		}
		return posizoneAlienoPiuBasso;
	}
}




