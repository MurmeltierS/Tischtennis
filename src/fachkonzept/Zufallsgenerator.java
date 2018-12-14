package fachkonzept;

public class Zufallsgenerator {
	public int gibZufallszahl(int pMin, int pMax) {
		return (int) (Math.random() * (pMax-pMin+1) + pMin);
	}
}
