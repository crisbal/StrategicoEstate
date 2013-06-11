package entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PersonaggioGenerico {

	public float x,y;
	public float xDisegno,yDisegno;
	Image Texture;
	Image Selezionato = new Image("res/GUI/Selezionato.png");
	
	public boolean selezionato=false;
	public boolean inVita=true;
	public int vita=100;
	
	
	
	public PersonaggioGenerico(int y,int x,String Tipo) throws SlickException
	{
		Texture=new Image(Tipo);
		this.x=x;   //la casella di appartenenza
		this.y=y;
		xDisegno=this.x*Texture.getWidth();   //il luogo sullo schermo su cui va disegnato
		yDisegno=this.y*Texture.getHeight();
	}
	
	public void Disegna()
	{
		Texture.draw(xDisegno, yDisegno);
		if(selezionato)
			Selezionato.draw(xDisegno, yDisegno);
	}
	
	public void Sposta(int y,int x)
	{
		this.x=x;
		this.y=y;
		xDisegno=this.x*Texture.getWidth();   
		yDisegno=this.y*Texture.getHeight();
		selezionato=false;
	}
}
