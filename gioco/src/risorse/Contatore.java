package risorse;

import gioco.Main;
import gioco.Scena;


public class Contatore implements Runnable{
    @Override
    public void run() {
        while(Main.gioco) {
            try {
                Thread.sleep(1000);
                if(Main.scena.score>0){
                    Main.scena.score -= 5;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
