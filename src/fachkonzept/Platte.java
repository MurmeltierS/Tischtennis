package fachkonzept;

import java.awt.Color;
import java.awt.Graphics;

public class Platte extends AufprallElement {

	private double x1, x2, dieHoehe, dieBreite;

	public void setzePosition(double pX1, double pX2, double pDieHoehe, double pDieBreite) {
		x1 = pX1;
		x2 = pX2;
		dieHoehe = pDieHoehe;
		dieBreite = pDieBreite;
	}

	@Override
	public void zeichnen(Graphics pZeichenFlaeche) {
		pZeichenFlaeche.setColor(Color.white);
		pZeichenFlaeche.drawRect((int) x1, (int) dieHoehe, (int) (x2 - x1), (int) 10);
	}

	@Override
	public boolean pruefeKollisionMitBall(Ball pBall, int pDt) {
		double x = pBall.gibX(); // lokale Variable für x-Koordinate des Balls
		double y = pBall.gibY(); // lokale Variable für y-Koordinate des Balls
		double r = pBall.gibRadius(); // lokale Variable für Radius des Balls
		boolean inXBereich = (x > x1 && x < x2); // prüft ob der Ball sich im x-Bereich der Platte befindet
		boolean aufschlagOben = (y + r) >= dieHoehe && (y - r) <= dieHoehe; // prüft ob Ball auf der Platte aufschlägt
		boolean aufschlagUnten = (y - r) <= dieHoehe + dieBreite && (y + r) >= dieHoehe + dieBreite;
		if (inXBereich && (aufschlagOben || aufschlagUnten)) {
			// ein Treffer liegt vor
			getroffen = true;
			double vx = 0.9 * pBall.gibVx(); // Abbremsung der x-Geschwindigkeit
			double vy = -pBall.gibVy() * 0.7; // Invertierung der y-Geschwindigkeit durch Aufprall
			pBall.setzeGeschwindigkeit(vx, vy); // setzt neu errechnete Geschwindigkeiten für den Ball
			if (aufschlagOben) {
				pBall.setzePosition(x, dieHoehe - r); // setzt Ball wieder auf die Platte falls dieser in der
														// Platte "steckt"
			} else if (aufschlagUnten) {
				pBall.setzePosition(x, dieHoehe + 5 + r);
			}
			return true;
		}

		return false;
	}

}
