package materiali;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class QuadratoMappa {
	
	public Image Texture;
	float x,y;
	public QuadratoMappa(String tipo,int x,int y) throws SlickException {   //costruttore, viene passato il tipo di blocco nel formato Materiali.nome
		Texture= new Image(tipo);
		this.x=Texture.getWidth()*x;
		this.y=Texture.getHeight()*y;
	}
	
	public void Disegna(){   //la x, la y e il parametro g fisso per disegnare
		Texture.draw(x, y);
	}

}
