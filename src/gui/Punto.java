package gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Punto {

	public float x;
	float y;
	float onda;
	float angolo;
	float incrementoAngolo;
	int ampiezza, color;
	Image imageRed, imageOrange, imageYellow;

	public Punto(float x, float y, int ampiezza, float incrementoAngolo,
			int color, Image imageRed, Image imageOrange, Image imageYellow) {
		this.x = x;
		this.y = y;
		this.ampiezza = ampiezza;
		this.incrementoAngolo = incrementoAngolo;
		this.color = color;
		this.imageRed = imageRed;
		this.imageOrange = imageOrange;
		this.imageYellow = imageYellow;
		angolo = (float) (Math.random() * 90);
	}

	public void Disegna(Graphics g) {
		angolo += incrementoAngolo;
		onda = (float) (Math.sin(angolo) * ampiezza);

		switch (color) {
		case 0:
			g.drawImage(imageYellow, x, y + onda);
			break;
		case 1:
			g.drawImage(imageOrange, x, y + onda);
			break;
		case 2:
			g.drawImage(imageRed, x, y + onda);
			break;
		}
	}
}
