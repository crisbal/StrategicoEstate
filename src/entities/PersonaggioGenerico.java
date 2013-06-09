package entities;

import mainGioco.Config;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PersonaggioGenerico {

	public float x,y;
	float xDisegno,yDisegno;
	Image Texture;
	public PersonaggioGenerico(int y,int x,String Tipo) throws SlickException
	{
		Texture=new Image(Tipo);
		this.x=x;   //la casella di appartenenza
		this.y=y;
		if(x>=Config.COLONNE/2)   //se e' dall'altra parte delle schrmo lo ribalto, possibile che venga rimosso.
			Texture=Texture.getFlippedCopy(true, false);
		xDisegno=this.x*Texture.getWidth();   //il luogo sullo schermo su cui va disegnato
		yDisegno=this.y*Texture.getHeight();
	}
	
	public void Disegna()
	{
		Texture.draw(xDisegno, yDisegno);
	}
}
