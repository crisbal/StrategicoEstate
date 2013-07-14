package entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class QuadratoMovimento {
	public Image	Texture;
	public float	x;
	public float	y;
	int				squadra;
	
	public QuadratoMovimento(String tipo, int x, int y, int squadra) throws SlickException {
		Texture = new Image(tipo);
		this.x = Texture.getWidth() * x;
		this.y = Texture.getHeight() * y;
		this.squadra = squadra;
	}
	
	public void Disegna() { // la x, la y e il parametro g fisso per disegnare
		if (squadra == 1)
			Texture.draw(x, y, new Color(1f, 0f, 0f));
		if (squadra == 2)
			Texture.draw(x, y, new Color(0f, 0f, 1f));
		if (squadra == 3)
			Texture.draw(x, y, new Color(0f, 1f, 0f));
	}
}
