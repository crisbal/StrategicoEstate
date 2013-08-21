package gui;

import java.awt.Font;

import mainGioco.Config;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import entities.PersonaggioGenerico;
import entities.Squadra;

public class FinestraBase {

	

	Rectangle rettangolo;
	Testo info = new Testo("Verdana", Font.BOLD, 20,java.awt.Color.black);
	String dettagli;
	PersonaggioGenerico p;
	public float larghezza,altezza;
	public FinestraBase(PersonaggioGenerico personaggio) throws SlickException {
		p = personaggio;
		larghezza =(250 * Config.Scala);
		altezza =(400 * Config.Scala);
		if(personaggio.x>Config.COLONNE/2)
		{
			rettangolo  = new Rectangle(0,0, larghezza, altezza);
		}
		else
		{
			rettangolo  = new Rectangle(Config.LARGHEZZA-larghezza,0, larghezza, altezza);
		}
		
		dettagli = "Vita: " + personaggio.vita;
		
	}
	
	
	public void Disegna(Graphics g,GameContainer gc)
	{
		
		g.setColor((Color) Squadra.squadra.get(p.squadra));
		g.fill(rettangolo);
		g.setColor(Color.black);
		g.setLineWidth(5*Config.Scala);
		g.drawRect((int) ((p.x>Config.COLONNE/2) ? 0 :Config.LARGHEZZA-larghezza), 0, larghezza, altezza);
		info.disegna(dettagli,(int) ((p.x>Config.COLONNE/2) ? 10*Config.Scala :Config.LARGHEZZA-larghezza + 10 * Config.Scala),(int) (10*Config.Scala));
		
	}
}
