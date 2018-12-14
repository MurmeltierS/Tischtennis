package fachkonzept;

import java.awt.Color;
import java.awt.Graphics;

public class Netz extends AufprallElement {

	private double x1, y1, dieHoehe;

	public void setzePosition(double pX1, double pY1, double pDieHoehe) {
		x1 = pX1;
		y1 = pY1;
		dieHoehe = pDieHoehe;
	}

	@Override
	public void zeichnen(Graphics pZeichenFlaeche) {
		pZeichenFlaeche.setColor(Color.white);
		pZeichenFlaeche.drawLine((int) x1, (int) y1, (int) x1, (int) (y1 - dieHoehe));
	}

	@Override
	public boolean pruefeKollisionMitBall(Ball pBall, int pDt) {
		double x = pBall.gibX(); // lokale Variable für x-Koordinate des Balls
		double y = pBall.gibY(); // lokale Variable für y-Koordinate des Balls
		double r = pBall.gibRadius(); // lokale Variable für Radius des Balls
		boolean inYBereich = (y >= y1 - dieHoehe) && (y <= y1);
		boolean inXBereich = (x + r >= x1) && (x - r <= x1);
		if (inYBereich && inXBereich) {
			double vx = -0.4 * pBall.gibVx(); // 
			double vy = pBall.gibVy(); // 
			pBall.setzeGeschwindigkeit(vx, vy);
			return true;
		}
		return false;
	}

}
