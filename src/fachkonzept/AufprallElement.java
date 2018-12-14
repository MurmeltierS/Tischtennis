package fachkonzept;

import java.awt.Graphics;

public abstract class AufprallElement {
	protected double x, y, dieLaenge, dieHoehe;
	protected boolean getroffen;

	public abstract void zeichnen(Graphics pZeichenFlaeche);

	public abstract boolean pruefeKollisionMitBall(Ball pBall, int pDt);

	public boolean gibGetroffen() {
		return getroffen;
	}
}
