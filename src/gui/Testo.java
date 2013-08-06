package gui;


import java.awt.Font;

import mainGioco.Config;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.TrueTypeFont;


public class Testo {
	
	
	private String testo;
	private Font font;
	public static TrueTypeFont ttf;
	private int	x,y;
	
	public static final int CENTROORIZ = -1;
	public static final int CENTROVERT = -2;
	
	public Testo(int StileTesto,int dimensione){
		font=new Font("Verdana",StileTesto, (int) (dimensione*Config.Scala)); 
		ttf = new TrueTypeFont(font, true);
	}
	
	
	public void disegna(String testo,int x,int y,Color colore,GameContainer gc)
	{
		if(x == CENTROORIZ)
			this.x = gc.getWidth()/2-ttf.getWidth(testo)/2;
		else
			this.x = x;
		if(y == CENTROVERT)
			this.y = gc.getHeight()/2-ttf.getHeight(testo)/2;
		else
			this.y = y;
		
		//this.x*=Config.Scala;
		//this.y*=Config.Scala;
		
		ttf.drawString(this.x, this.y, testo,colore);
	}
}
