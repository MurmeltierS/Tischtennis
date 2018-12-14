package benutzerschnittstelle;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import steuerung.Steuerung;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JButton btnNeuesSpiel;
	private JButton btnNeuerAufschlag;
	private JTextField txtAufschlaege;
	private JLabel lblAufschlaege;
	private JLabel lblRestzeit;
	private JTextField txtRestzeit;
	private Steuerung dieSteuerung;
	private Canvas canvas;
	private BufferStrategy bufferStrategy;
	private Graphics Zeichenflaeche;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
					frame.initialisiereCanvas();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setTitle("Tischtennisaufschlag");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 843, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnNeuesSpiel = new JButton("Neues Spiel");
		btnNeuesSpiel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clickNeuesSpiel();
			}
		});
		btnNeuesSpiel.setBounds(12, 13, 141, 25);
		contentPane.add(btnNeuesSpiel);

		btnNeuerAufschlag = new JButton("Neuer Aufschlag");
		btnNeuerAufschlag.setEnabled(false);
		btnNeuerAufschlag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clickNeuerAufschlag();
			}
		});
		btnNeuerAufschlag.setBounds(12, 51, 141, 25);
		contentPane.add(btnNeuerAufschlag);

		txtAufschlaege = new JTextField();
		txtAufschlaege.setBounds(165, 52, 116, 22);
		contentPane.add(txtAufschlaege);
		txtAufschlaege.setColumns(10);

		lblAufschlaege = new JLabel("Aufschl\u00E4ge");
		lblAufschlaege.setBounds(165, 17, 105, 16);
		contentPane.add(lblAufschlaege);

		lblRestzeit = new JLabel("Restzeit");
		lblRestzeit.setBounds(293, 17, 105, 16);
		contentPane.add(lblRestzeit);

		txtRestzeit = new JTextField();
		txtRestzeit.setColumns(10);
		txtRestzeit.setBounds(293, 52, 116, 22);
		contentPane.add(txtRestzeit);
		
		canvas = new Canvas();
		canvas.setLocation(12, 92);
		canvas.setSize(800, 400);
		canvas.setBackground(Color.gray);
		canvas.setVisible(true);
		canvas.setFocusable(false);
		contentPane.add(canvas);
		
		
		
		
		dieSteuerung = new Steuerung(this);
		
	}
	
	public void initialisiereCanvas() {
		canvas.createBufferStrategy(3);
		bufferStrategy = canvas.getBufferStrategy();
		Zeichenflaeche = bufferStrategy.getDrawGraphics();
		clearCanvas();
	}
	
	public void clearCanvas() {
		Zeichenflaeche.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}
	
	public void showCanvas() {
		bufferStrategy.show();
		Zeichenflaeche.dispose();
		Zeichenflaeche = bufferStrategy.getDrawGraphics();
	}
	
	public Graphics gibZeichenflaeche() {
		return Zeichenflaeche;
	}

	public void clickNeuesSpiel() {
		dieSteuerung.neuesSpiel();
	}

	public void clickNeuerAufschlag() {
		dieSteuerung.neuerAufschlag();
	}

	public void zieheMaus() {
		// TODO
	}

	public void linkeMausLoslassen() {
		// TODO
		dieSteuerung.mausGezogen(40, 40);

	}

	public void aktiviereButtonNeuerAufschlag() {
		btnNeuerAufschlag.setEnabled(true);
	}

	public void aktiviereButtonStart() {
		btnNeuesSpiel.setEnabled(true);
	}

	public void deaktiviereButtonNeuerAufschlag() {
		btnNeuerAufschlag.setEnabled(false);
	}

	public void deaktiviereButtonStart() {
		btnNeuesSpiel.setEnabled(false);
	}

	public void zeigeAnzahlSchlaege(int pAnzahl) {
		txtAufschlaege.setText(Integer.toString(pAnzahl));
	}

	public void zeigeAnzahlTreffer(int pTreffer) {
		txtAufschlaege.setText(Integer.toString(pTreffer));
	}

	public void zeigeRestzeit(int pSek) {
		txtRestzeit.setText(pSek + " s");

	}

}
