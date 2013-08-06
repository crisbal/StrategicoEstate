package materiali;

import mainGioco.Config;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class QuadratoMappa {
	
	public Image	Texture;
	float			x, y;
	public String tipo;
	public QuadratoMappa(String tipo, int x, int y) throws SlickException {
		this.tipo = tipo;
		Texture = new Image(Materiale.Path + tipo + ".png ");
		this.x = Texture.getWidth() * x * Config.Scala;
		this.y = Texture.getHeight() * y * Config.Scala;
	}
	
	public void Disegna() { // la x, la y e il parametro g fisso per disegnare
		Texture.draw(x, y, Config.Scala);
	}
	
}
