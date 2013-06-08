package entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PersonaggioGenerico {

	float x,y;
	Image Texture;
	public PersonaggioGenerico(int y,int x,String Tipo) throws SlickException
	{
		Texture=new Image(Tipo);
		this.x=x*Texture.getWidth();
		this.y=y*Texture.getHeight();
	}
	
	public void Disegna()
	{
		Texture.draw(x, y);
	}
}
