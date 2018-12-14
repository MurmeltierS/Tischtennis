package fachkonzept;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	private double x, y, derRadius = 10, vX, vY;

	public void bewege(int pDt) {
		x += vX * pDt;
		vY = 0.0005 * pDt + vY;	// Gravitation berechnen
		y += (vY) * pDt;
	}

	public void setzeGeschwindigkeit(double pVx, double pVy) {
		vX = pVx;
		vY = pVy;
	}

	public void setzePosition(double pX, double pY) {
		x = pX;
		y = pY;
	}

	public double gibX() {
		return x;
	}

	public double gibY() {
		return y;
	}

	public double gibVx() {
		return vX;
	}

	public double gibVy() {
		return vY;
	}

	public double gibRadius() {
		return derRadius;
	}

	public void zeichne(Graphics pZeichenFlaeche) {
		pZeichenFlaeche.setColor(Color.white);
		pZeichenFlaeche.fillOval((int) (x - derRadius), (int) (y - derRadius), (int) (derRadius * 2),
				(int) (derRadius * 2));
	}
}
