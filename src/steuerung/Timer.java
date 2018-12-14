package steuerung;

import java.util.TimerTask;

public class Timer {
	private int dt;
	private Steuerung dieSteuerung;
	static private java.util.Timer timer = new java.util.Timer();

	public Timer(Steuerung pSteuerung, int pDt) {
		dieSteuerung = pSteuerung;
		dt = pDt;

		cancelTimer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				tickTimer();
			}
		}, dt, dt);
	}

	public void tickTimer() {
		dieSteuerung.zeitEreignis();
	}
	
	public void cancelTimer() {
		timer.cancel();	// existierenden util.Timer canceln
		timer = new java.util.Timer();	// new util.Timer
	}
}
