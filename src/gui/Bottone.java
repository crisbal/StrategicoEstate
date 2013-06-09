package gui;

import java.awt.Font;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

public class Bottone {

	Image Immagine;
	String Testo;
	Font font=new Font("Verdana", Font.PLAIN, 16);   //il testo e' verdana 16 normale PLAIN
	TrueTypeFont ttf = new TrueTypeFont(font, true);  //creo la ttf per stamparlo a video
	
	boolean visualizza=true,cliccabile=true;
	float x, y, larghezza, altezza;

	public Bottone(String contenuto) throws SlickException {
		Immagine = new Image("res/GUI/Bottone.png", false, Image.FILTER_NEAREST);   //uso l'ultimo parametro per rimuovere il blur dell'immagine
		Testo = contenuto;
		visualizza=true;
	}

	public void Disegna(float x, float y) {
		if(visualizza)
		{
			this.x = x;
			this.y = y;
			
			float LunghezzaTesto=ttf.getWidth(Testo);
			float AltezzaTesto=ttf.getHeight(Testo);
			float coeffLungh=(LunghezzaTesto/Immagine.getWidth());   //determina l'ingrossamento del bottone
			float coeffAltez=(AltezzaTesto/Immagine.getHeight());
			
			larghezza=Immagine.getWidth()*coeffLungh+15*coeffLungh;  
			altezza=Immagine.getHeight()*coeffAltez+100*coeffAltez;
	
			Immagine.draw(x, y, larghezza, altezza);   //disegno l'immagine
			
			ttf.drawString(x+larghezza/2-LunghezzaTesto/2, y+altezza/2-AltezzaTesto/2, Testo, Color.red);  //scrivo il testo nel centro
		}

	}

	public boolean Cliccato(GameContainer gc){
		float MouseY=gc.getHeight()-Mouse.getY()-1;  //la gestione della Y e' stupida, per avere quella vera devo comportarmi cosi'
		
		if(Mouse.isButtonDown(0)&&visualizza&&cliccabile)
		{
			
			if(Mouse.getX()>x&&Mouse.getX()<(x+larghezza))
			{

				if(MouseY>y&&MouseY<(y+altezza))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	
	public void Elimina()
	{
		visualizza=false;
	}

	
	public void Update(GameContainer gc) {
		if(visualizza)
			if(Cliccato(gc))
			{
				Elimina();
			}
	}
}
