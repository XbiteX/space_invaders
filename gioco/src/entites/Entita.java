package entites;

import java.awt.Image;
import javax.swing.ImageIcon;

public abstract class Entita {

/**** VARIABILI ****/

	protected int larghezza, altezza, xPos, yPos, dx, dy;
	protected boolean viva;
	protected String frame1, frame2, frame3;
	protected ImageIcon ico;
	protected Image img;
	
/**** METODI ****/

	public int getLarghezza() {return larghezza;}
	public void setLarghezza(int larghezza) {
		this.larghezza = larghezza;
	}
	public int getAltezza() {
		return altezza;
	}
	public void setAltezza(int altezza) {
		this.altezza = altezza;
	}
	public int getxPos() {
		return xPos;
	}
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	public int getyPos() {
		return yPos;
	}
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	public int getDx() {
		return dx;
	}
	public void setDx(int dx) {
		this.dx = dx;
	}
	public int getDy() {
		return dy;
	}
	public void setDy(int dy) {
		this.dy = dy;
	}
	public boolean isViva() {
		return viva;
	}
	public void setViva(boolean viva) {
		this.viva = viva;
	}
	public String getFrame1() {
		return frame1;
	}
	public void setFrame1(String frame1) {
		this.frame1 = frame1;
	}
	public String getFrame2() {
		return frame2;
	}
	public void setFrame2(String frame2) {
		this.frame2 = frame2;
	}
	public String getFrame3() {
		return frame3;
	}
	public void setFrame3(String frame3) {
		this.frame3 = frame3;
	}
	public ImageIcon getIco() {
		return ico;
	}
	public void setIco(ImageIcon ico) {
		this.ico = ico;
	}
	public Image getImg() {
		return img;
	}
	public void setImg(Image img) {
		this.img = img;
	}
}
