package entities;

import mainGioco.Config;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class QuadratoMovimento {
	public Image	Texture;
	public float	x;
	public float	y;
	private int		squadra;
	
	public QuadratoMovimento(String tipo, int x, int y, int squadra) throws SlickException {
		Texture = new Image(tipo);
		this.x = Texture.getWidth() * x * Config.Scala;
		this.y = Texture.getHeight() * y * Config.Scala;
		this.squadra = squadra;
	}
	
	public void Disegna() { // la x, la y e il parametro g fisso per disegnare
		if (squadra == 1)
			Texture.draw(x, y, Config.Scala, new Color(1f, 0f, 0f));
		if (squadra == 2)
			Texture.draw(x, y, Config.Scala, new Color(0f, 0f, 1f));
		if (squadra == 3)
			Texture.draw(x, y, Config.Scala, new Color(0f, 1f, 0f));
	}
}
