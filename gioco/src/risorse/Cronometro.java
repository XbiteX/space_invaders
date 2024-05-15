package risorse;

import gioco.Main;

public class Cronometro implements Runnable {

/**** VARIABLES ****/
	
	private final int PAUSE = 5; // temps d'attente entre 2 tours de boucle : 5 ms
	public static int contaGiri = 0;
	
	
/**** METHODES ****/
	
	@Override
	public void run() {		
		while(Main.gioco){
			contaGiri++;
			Main.scena.repaint(); // Appel de la méthode PaintComponent de l'objet scena
			try {Thread.sleep(PAUSE);} // temps de pause (5 ms)
			catch (InterruptedException e) {}
		}
	}

		
}