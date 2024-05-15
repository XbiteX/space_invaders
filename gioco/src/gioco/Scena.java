package gioco;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import risorse.*;

import javax.swing.JPanel;

import entites.*;
import entites.SparoAlieno;

public class Scena extends JPanel {
	
/**** VARIABILI ****/
	TileManager tileManager = new TileManager(this);
	public Astronave navicella = new Astronave();
	public GruppoAlieni gruppoAlieni = new GruppoAlieni();
	public SparoAstronave sparoAstronave = new SparoAstronave();
	
	public Barriera[] barriera = new Barriera[4]; // Creazione di un array contenente 4 barriere
	
	public SparoAlieno sparoAlieno1, sparoAlieno2, sparoAlieno3, sparoAlieno4, sparoAlieno5;

	public Thread cronometro = new Thread(new Cronometro());
	public Thread contatore = new Thread((new Contatore()));
	public Disco ufo;
	
	private final Font Punteggio = new Font("Arial", Font.PLAIN, 20);
	private final Font Testo = new Font("Arial", Font.PLAIN, 80);
	
	public int score = 100;
	
/**** COSTRUTTORE ****/
	
	public Scena() {
		super();
		
		// Instanziamento delle barriere
		for(int colonne=0; colonne<4; colonne++) {
			this.barriera[colonne] = new Barriera(Costanti.MARGINE_FINESTRA +
					Costanti.X_POS_INIT_BARRIERA + colonne * (Costanti.LARGHEZZA_BARRIERA + Costanti.GAP_BARRIERE));
		}

		// Instanziamento della tastiera
		this.setFocusable(true);
		// Serve all'utente per interagire con la finestra
		this.requestFocusInWindow();
		// Serve per ricevere degli eventi sulla tastiera
		this.addKeyListener(new Tastiera());
		
		// avvio del cronometro
		cronometro.start();

		// avvio del contatore
		contatore.start();
	}

		
/**** METODI ****/
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		tileManager.draw(g2);
		
		// Disegno della riga rosa in fondo allo schermo
		// g2.setColor(Color.pink);
		// g2.fillRect(0, 576, Costanti.LARGHEZZA_FINESTRA, 5);
		
		// Visualizzazione del punteggio
		g.setFont(Punteggio);
		g.setColor(new Color(0, 62, 250));
		g.drawString("SCORE : " + score, 400, 25);
		
		// Disegno della Navicella
		this.navicella.disegnoNavicella(g2);
		
		// Disegno degli alieni
		this.gruppoAlieni.disegnoAlieni(g2);
		
		// Disegno dello sparo della navicella
		this.sparoAstronave.disegnoSparoNavicella(g2);
		
		// Rilevamento se lo sparo della navicella colpisce un alieno
		this.gruppoAlieni.sparoNavicellaContattoAlieno(this.sparoAstronave);
		
		// Disegno delle barriere
		for(int colonne=0; colonne<4; colonne++) {
			this.barriera[colonne].disegnoBarriera(g2);
		}
		
		// Messaggio a inizio gioco
		if(Cronometro.contaGiri < 500) {
			g.setFont(Testo);
		    g.drawString("GL !", 95, 100);
		}
		
		// Messaggio a fine gioco
		if(!this.navicella.isViva()) {
			g.setFont(Testo);
			g.drawString("GAME OVER", 50, 100);
		}
		
		// Rilevamento se lo sparo della navicella colpisce una barriera
		this.sparoAstronave.tiroNavicellaDistruggebarriera(barriera);
		
		// Disegno dello sparo degli alieni
		if(Cronometro.contaGiri % 500 == 0) {
			sparoAlieno1 = new SparoAlieno(this.gruppoAlieni.sceltaAlienoTiratore());}
		if(this.sparoAlieno1 != null) {
			this.sparoAlieno1.disegnoSparoAlieno(g2);
			this.sparoAlieno1.tiroAlienoDistruggeBarriera(barriera);
			// Rilevamento tra lo sparo dell'alieno1 e la barriera
			if(this.sparoAlieno1.contattoNavicella(navicella)) {
				this.navicella.setViva(false);
			}
		}

		if(Cronometro.contaGiri % 600 == 0) {
			sparoAlieno4 = new SparoAlieno(this.gruppoAlieni.sceltaAlienoTiratore());}
		if(this.sparoAlieno4 != null) {
			this.sparoAlieno4.disegnoSparoAlieno(g2);
			this.sparoAlieno4.tiroAlienoDistruggeBarriera(barriera);
			// Rilevamento tra lo sparo dell'alieno1 e la barriera
			if(this.sparoAlieno4.contattoNavicella(navicella)) {
				this.navicella.setViva(false);
			}
		}

		if(Cronometro.contaGiri % 800 == 0) {
			sparoAlieno5 = new SparoAlieno(this.gruppoAlieni.sceltaAlienoTiratore());}
		if(this.sparoAlieno5 != null) {
			this.sparoAlieno5.disegnoSparoAlieno(g2);
			this.sparoAlieno5.tiroAlienoDistruggeBarriera(barriera);
			// Rilevamento tra lo sparo dell'alieno1 e la barriera
			if(this.sparoAlieno5.contattoNavicella(navicella)) {
				this.navicella.setViva(false);
			}
		}

		if(Cronometro.contaGiri % 750 == 0) {
			sparoAlieno2 = new SparoAlieno(this.gruppoAlieni.sceltaAlienoTiratore());}
		if(this.sparoAlieno2 != null) {
			this.sparoAlieno2.disegnoSparoAlieno(g2);
			this.sparoAlieno2.tiroAlienoDistruggeBarriera(barriera); //Rilevamento tra lo sparo dell'alieno2 e la barriera
			if(this.sparoAlieno2.contattoNavicella(navicella)) {
				this.navicella.setViva(false);
			}
		}

		if(Cronometro.contaGiri % 900 == 0) {
			sparoAlieno3 = new SparoAlieno(this.gruppoAlieni.sceltaAlienoTiratore());}
		if(this.sparoAlieno3 != null) {
			this.sparoAlieno3.disegnoSparoAlieno(g2);
			this.sparoAlieno3.tiroAlienoDistruggeBarriera(barriera); // Rilevamento tra lo sparo dell'alieno3 e la barriera
			if(this.sparoAlieno3.contattoNavicella(navicella)) {
				this.navicella.setViva(false);
			}
		}

		// Disegno dell'ufo
		if(Cronometro.contaGiri % 2500 == 0) {
			ufo = new Disco();}
		if(this.ufo != null) {
			if(this.ufo.getxPos()>0) {
				// Rilevamento contatto tra lo sparo della navicella e l'ufo
				if(this.sparoAstronave.distruggeUfo(this.ufo)) {
					if(this.ufo.getDx() != 0) {
						this.score = this.score + Costanti.PUNTEGGIO_UFO;
					}
					this.ufo.setDx(0);
					this.ufo.setViva(false);
					this.ufo.suonoDisco.stop();
					this.ufo.suonoDistruzioneDisco.play();
				}
				this.ufo.disegnoDisco(g2);
			}else {
				this.ufo = null;
			}
		}

		// se il punteggio arriva a 0 si perde
		if(this.score == 0){
			this.navicella.setViva(false);
		}
		
		if(this.gruppoAlieni.getNumeroAlieni() == 0) {
			gruppoAlieni = new GruppoAlieni();}
	
		if(this.gruppoAlieni.posizioneAlienoPiuBasso() > Costanti.Y_POS_NAVICELLA) {
			this.navicella.distruzioneNavicella();
		}
	}	
}
