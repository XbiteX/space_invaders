package risorse;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gioco.Main;

public class Tastiera implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		if(Main.scena.navicella.isViva()) {
			if(e.getKeyCode() == KeyEvent.VK_RIGHT){Main.scena.navicella.setDx(Costanti.DX_VAISSEAU);}
			else if(e.getKeyCode() == KeyEvent.VK_LEFT){Main.scena.navicella.setDx(-Costanti.DX_VAISSEAU);}
			else if(e.getKeyCode() == KeyEvent.VK_SPACE){
				if(!Main.scena.sparoAstronave.isSparoNavicella()) {
					Audio.playSound("/suoni/suonoTiroNavicella.wav");
					Main.scena.sparoAstronave.setyPos(Costanti.Y_POS_NAVICELLA - Costanti.ALTEZZA_SPARO_NAVICELLA);
					Main.scena.sparoAstronave.setxPos(Main.scena.navicella.getxPos() + Costanti.LARGHEZZA_NAVICELLA /2 - 1);
					Main.scena.sparoAstronave.setSparoNavicella(true);
				}
		    }
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {Main.scena.navicella.setDx(0);}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
