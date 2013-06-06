package materiali;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class QuadratoGenerico {
	
	Image Texture;
	
	public QuadratoGenerico(String tipo) throws SlickException {   //costruttore, viene passato il tipo di blocco nel formato Materiali.nome
		Texture= new Image(tipo);
	}
	
	public void Disegna(float x,float y,Graphics g){   //la x, la y e il parametro g fisso per disegnare
		g.drawImage(Texture, x, y);
	}
}
