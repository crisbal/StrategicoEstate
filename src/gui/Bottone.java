package gui;

import java.awt.Font;

import mainGioco.Config;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

public class Bottone {

	private Image Immagine;
	private String Testo;
	private Font font=new Font("Verdana", Font.PLAIN,(int)(16*Config.Scala));   //il testo e' verdana 16 normale PLAIN
	private TrueTypeFont ttf = new TrueTypeFont(font, true);  //creo la ttf per stamparlo a video
	
	public boolean visualizza=true,cliccabile=true;
	public float x, y, larghezza, altezza;
	float LunghezzaTesto,AltezzaTesto,coeffLungh,coeffAltez;
	
	public String[] infoAggiuntive;
	private boolean giaCliccato;
	public Bottone(String contenuto) throws SlickException {
		Immagine = new Image("res/GUI/Bottone.png", false, Image.FILTER_NEAREST);   //uso l'ultimo parametro per rimuovere il blur dell'immagine
		Immagine = Immagine.getScaledCopy(Config.Scala);
		Testo = contenuto;
		visualizza=true;
		cliccabile=true;
		LunghezzaTesto=ttf.getWidth(Testo);
		AltezzaTesto=ttf.getHeight(Testo);
		coeffLungh=(LunghezzaTesto/Immagine.getWidth());   //determina l'ingrossamento del bottone
		coeffAltez=(AltezzaTesto/Immagine.getHeight());
		larghezza=Immagine.getWidth()*coeffLungh+50*Config.Scala*coeffLungh;
		altezza=Immagine.getHeight()*coeffAltez+15*Config.Scala*coeffAltez;
	}

	
	public Bottone(String contenuto, String... infoAggiuntive) throws SlickException {
		Immagine = new Image("res/GUI/Bottone.png", false, Image.FILTER_NEAREST);   //uso l'ultimo parametro per rimuovere il blur dell'immagine
		Immagine = Immagine.getScaledCopy(Config.Scala);
		Testo = contenuto;
		visualizza=true;
		cliccabile=true;
		LunghezzaTesto=ttf.getWidth(Testo);
		AltezzaTesto=ttf.getHeight(Testo);
		coeffLungh=(LunghezzaTesto/Immagine.getWidth());   //determina l'ingrossamento del bottone
		coeffAltez=(AltezzaTesto/Immagine.getHeight());
		larghezza=Immagine.getWidth()*coeffLungh+50*Config.Scala*coeffLungh;
		altezza=Immagine.getHeight()*coeffAltez+15*Config.Scala*coeffAltez;
		this.infoAggiuntive = infoAggiuntive;
	}
	
	
	
	public void Disegna(float x, float y) {
		if(Immagine!=null)
		{
			this.x = x;
			this.y = y;
			
			Immagine.draw(this.x, this.y, larghezza, altezza);   //disegno l'immagine
			
			ttf.drawString(this.x+larghezza/2-LunghezzaTesto/2, this.y+altezza/2-AltezzaTesto/2, Testo, Color.black);  //scrivo il testo nel centro
		}

	}
	
	public void Disegna(float x, float y, Color color) {
		if(Immagine!=null)
		{
			this.x = x*Config.Scala;
			this.y = y*Config.Scala;
			
			float LunghezzaTesto=ttf.getWidth(Testo);
			float AltezzaTesto=ttf.getHeight(Testo);
			float coeffLungh=(LunghezzaTesto/Immagine.getWidth());   //determina l'ingrossamento del bottone
			float coeffAltez=(AltezzaTesto/Immagine.getHeight());
			larghezza=Immagine.getWidth()*coeffLungh+50*Config.Scala*coeffLungh;
			altezza=Immagine.getHeight()*coeffAltez+15*Config.Scala*coeffAltez;
	
			Immagine.draw(this.x, this.y, larghezza, altezza,color);   //disegno l'immagine
			
			ttf.drawString(this.x+larghezza/2-LunghezzaTesto/2, this.y+altezza/2-AltezzaTesto/2, Testo, Color.black);  //scrivo il testo nel centro
		}

	}
	

	public boolean Cliccato(){
		
		float MouseY=Config.ALTEZZA-Mouse.getY()-1;  //la gestione della Y e' stupida, per avere quella vera devo comportarmi cosi'
		
		
		if(Mouse.isButtonDown(0)&&visualizza&&cliccabile&&!giaCliccato)
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
		cliccabile = false;
		giaCliccato = false;
	}

	public void Ripristina()
	{
		visualizza=true;
		cliccabile = true;
		giaCliccato = false;
	}
	
	public boolean Visibile(){
		return visualizza;
	}
}
