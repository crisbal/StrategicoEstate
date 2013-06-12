package entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PersonaggioGenerico {

	public float x,y;
	public float xDisegno,yDisegno;
	Image TextureGenerica,TextureSpecifica;
	Image Selezionato = new Image("res/GUI/Selezionato.png");
	
	public boolean selezionato=false;
	public boolean inVita=true;
	
	
	public int vita=100;
	public int squadra;
	
	
	public PersonaggioGenerico(int y,int x,String Tipo,int Squadra) throws SlickException
	{
		TextureGenerica=new Image(Tipo+"Generico.png");
		TextureSpecifica=new Image(Tipo+"Specifico.png");
		this.x=x;   //la casella di appartenenza
		this.y=y;
		xDisegno=this.x*TextureGenerica.getWidth();   //il luogo sullo schermo su cui va disegnato
		yDisegno=this.y*TextureSpecifica.getHeight();
		squadra=Squadra;
	}
	
	public void Disegna()
	{
		TextureGenerica.draw(xDisegno,yDisegno);
		
		if(squadra==1)
			TextureSpecifica.draw(xDisegno, yDisegno,new Color(1f,0f,0f));
		if(squadra==2)
			TextureSpecifica.draw(xDisegno, yDisegno,new Color(0f,0f,1f));
		if(squadra==3)
			TextureSpecifica.draw(xDisegno, yDisegno,new Color(0f,1f,0f));
		
		if(selezionato)
			Selezionato.draw(xDisegno, yDisegno);
	}
	
	public void Sposta(int y,int x)
	{
		this.x=x;
		this.y=y;
		xDisegno=this.x*TextureSpecifica.getWidth();   
		yDisegno=this.y*TextureSpecifica.getHeight();
		selezionato=false;
	}
}
