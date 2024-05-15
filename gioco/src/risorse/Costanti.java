package risorse;

public abstract class Costanti {
	
	/************************************* FINESTRA *************************************/
	// Dimensioni della finestra
	public static final int LARGHEZZA_FINESTRA = 592;
	public static final int ALTEZZA_FINESTRA = 934;
	public static final int MARGINE_FINESTRA = 50;
	
	/************************************* NAVICELLA *************************************/
	// Dimensioni della navicella
	public static final int LARGHEZZA_NAVICELLA = 40;
	public static final int ALTEZZA_NAVICELLA = 40;
	
	// Posizione inniziale della navicella
	public final static int X_POS_INIIZIALE_NAVICELLA = (LARGHEZZA_FINESTRA - LARGHEZZA_NAVICELLA)/ 2;
	public final static int Y_POS_NAVICELLA = 520;
	
	// Unità di spostamento della navicella
	public final static int DX_VAISSEAU = 1;
	
	// Limiti destro e sinistro della navicella
	public final static int LIMITE_SINISTRO_NAVICELLA = 60;
	public final static int LIMITE_DESTRO_NAVICELLA = 500;
	
	/************************************* ALIENO ***************************************/
	// Dimensioni degli alieni
	public static final int LARGHEZZA_ALIENO = 33;
	public static final int ALTEZZA_ALIENO = 25;
	
	// Parametri per la posizione degli alieni
	public final static int ALT_INIT_ALIEN = 120;
	public final static int X_POS_INIT_ALIEN = 29 + MARGINE_FINESTRA;
	public final static int GAP_LINEE_ALIENI = 40;
	public final static int GAP_COLONNE_ALIENI = 10;
	
	// Unità di spostamanto degli alieni
	public final static int DX_ALIENO = 2;
	public final static int DY_ALIENO = 20;
	public final static int VELOCITA_ALIENI = 1;
	
	// Numero totale degli alieni
	public final static int NUMERO_ALIENI = 50;
	
	/************************************ SPARO NAVICELLA **********************************/
	// Dimensions du tir
	public static final int LARGHEZZA_SPARO_NAVICELLA = 3;
	public static final int ALTEZZA_SPARO_NAVICELLA = 13;
	
	// Unité de déplacement du tir
	public final static int DY_SPARO_NAVICELLA = 2;
	
	/************************************* BARRIERA *************************************/
	// Dimensioni del mattone
	public static final int DIMENSIONI_MATTONE = 3;
	
	// Dimensione della barriera
	public static final int LARGHEZZA_BARRIERA = 75;
	public static final int ALTEZZA_BARRIERA = 54;
		
	// Posizione delle barriere
	public final static int Y_POS_BARRIERE = 400;
	public final static int X_POS_INIT_BARRIERA = 39;
	public final static int GAP_BARRIERE = 42;
	
	/************************************ TIRO ALIENO ************************************/
	// Dimensioni dello sparo
	public static final int LARGHEZZA_TIRO_ALIENO = 5;
	public static final int ALTEZZA_TIRO_ALIENO = 15;
	
	// Unità di spostamento dello sparo
	public final static int DY_TIRO_ALIENO = 3;

	/************************************* UFO *************************************/
	// Dimensioni del disco volante
	public static final int LARGHEZZA_DISCO = 42;
	public static final int ALTEZZA_DISCO = 22;

	// Posizione iniziale del disco volante
	public final static int X_POS_INIT_DISCO = LARGHEZZA_FINESTRA;
	public final static int Y_POS_DISCO = 50;

	// Spostamento del disco
	public final static int DX_DISCO = 1;
	
	/************************************* PUNTI *************************************/
	// Puntaggio da aggiungere alla distruzione degli alieni
	public static final int VALORE_ALIENO_ALTO = 50;
	public static final int VALORE_ALIENO_MEDIO = 40;
	public static final int VALORE_ALIENO_BASSO = 20;
	public static final int PUNTEGGIO_UFO = 100;
}


