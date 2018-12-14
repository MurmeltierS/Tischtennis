package steuerung;

import java.awt.Graphics;

import benutzerschnittstelle.GUI;
import fachkonzept.AufprallElement;
import fachkonzept.Ball;
import fachkonzept.Netz;
import fachkonzept.Platte;
import fachkonzept.Zufallsgenerator;

public class Steuerung {
	private int dt, derZustand, dieRestzeit, dieAnzahlPunkte, dieAnzahlAufschlaege;
	private GUI dieGui;
	private Timer derTimer;
	private Ball derBall;
	private Platte diePlatteV, diePlatteH, derBoden;
	private Netz dasNetz;
	private AufprallElement[] dasAufprallElement;
	private Zufallsgenerator derZufallsgenerator;

	public Steuerung(GUI pGui) {
		dieGui = pGui; // Übergabe des GUI-Objekts an die Stuerung
		derZufallsgenerator = new Zufallsgenerator(); // instanziere Zufallsgenerator
		derZustand = -1; // setzt Zustand auf -1 (= kein aktuelles Spiel)
		dt = 30; // 30ms "ticks"

	}

	public void zeitEreignis() {
		ermittleRestzeit();
		dieGui.zeigeRestzeit(dieRestzeit / 1000);
		derBall.bewege(dt);
		pruefeKollisionBallUmgebung();
		ermittleZustand();
		zeichnen();
	}

	public void neuesSpiel() {

		dieGui.deaktiviereButtonStart();
		dieGui.aktiviereButtonNeuerAufschlag();
		dieRestzeit = 30000; // Restzeit in ms (= 30sec)
		derTimer = new Timer(this, dt); // setze Timer auf dt ms
		derBall = new Ball(); // erstelle Instanz der Klasse Ball
		derBall.setzeGeschwindigkeit(0.1, 0.1);
		diePlatteV = new Platte(); // TODO: für Platte positionen implementieren
		diePlatteV.setzePosition(100, 400, 250, 5);
		diePlatteH = new Platte();
		diePlatteH.setzePosition(400, 700, 250, 5);
		derBoden = new Platte();
		derBoden.setzePosition(0, 800, 400, 0);
		dasNetz = new Netz();
		dasNetz.setzePosition(400, 250, 40);
		dasAufprallElement = new AufprallElement[] { diePlatteV, diePlatteH, derBoden, dasNetz };
	}

	public void neuerAufschlag() {
		derZustand = 0;
		dieAnzahlAufschlaege++;
		dieGui.zeigeAnzahlSchlaege(dieAnzahlAufschlaege);
		ermittleAbwurfPosition();
		derBall.setzeGeschwindigkeit(0.005 * derZufallsgenerator.gibZufallszahl(0, 100),
				0.001 * derZufallsgenerator.gibZufallszahl(0, 100));
	}

	public void mausGezogen(double pX, double pY) {

	}

	public void linkeMausLosgelassen(double pX, double pY) {

	}

	private void ermittleAbwurfPosition() {
		double xZufall = derZufallsgenerator.gibZufallszahl(1, 100);
		double yZufall = derZufallsgenerator.gibZufallszahl(1, 100);
		derBall.setzePosition(xZufall, yZufall);
	}

	private void ermittleZustand() {
		boolean getroffenV = diePlatteV.gibGetroffen();
		boolean getroffenH = diePlatteH.gibGetroffen();
		boolean getroffenB = derBoden.gibGetroffen();
		boolean getroffenN = dasNetz.gibGetroffen();
		if (derZustand == 0 && getroffenV && !getroffenH && !getroffenB && !getroffenN) {
			derZustand = 1;
		} else if ((derZustand == 2 && getroffenH && !getroffenB)
				|| (derZustand == 1 && getroffenH && !getroffenN && !getroffenB)) {
			derZustand = 3;
			// gültiger Aufschlag
			dieAnzahlPunkte++;
		} else if (derZustand == 1 && getroffenN) {
			derZustand = 2;
		}

		if (dieRestzeit <= 0) {
			derZustand = -1;
			derTimer.cancelTimer();
			dieGui.aktiviereButtonStart();
			dieGui.deaktiviereButtonNeuerAufschlag();
		}
	}

	private void ermittleRestzeit() {
		dieRestzeit -= dt;
	}

	private boolean pruefeKollisionBallUmgebung() {
		for (AufprallElement einAufprallElement : dasAufprallElement) {
			einAufprallElement.pruefeKollisionMitBall(derBall, dt);
		}
		return false;
	}

	private void zeichnen() {
		Graphics Zeichenflaeche = dieGui.gibZeichenflaeche();
		dieGui.clearCanvas();
		derBall.zeichne(Zeichenflaeche);
		for (AufprallElement einAufprallElement : dasAufprallElement) {
			einAufprallElement.zeichnen(Zeichenflaeche);
		}
		dieGui.showCanvas();
	}
}
