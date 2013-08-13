package entities;

import gui.Testo;

import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import mainGioco.Config;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Giocatore {
	
	String nome;
	public Map<Integer, Integer> elementi = new HashMap<Integer, Integer>();
	Image base;
	Testo testo;
	public boolean piuAttaccoSoldato,piuAttaccoCarro,piuDifesaSoldato,piuDifesaCarro;
	public Giocatore(String nome, Map<Integer, Integer> elementi) throws SlickException
	{
		System.out.println(elementi.get(0));
		this.nome=nome;
		this.elementi=elementi;
		System.out.println(this.elementi.get(0));
		base = new Image("res/Giocatori/Base.png");
		testo = new Testo(Font.BOLD, 16);
	}
	
	
	public void Disegna(float f,float g,GameContainer gc) throws SlickException
	{
		base.draw(f, g, 2f);
		for (int i = 0; i < elementi.size(); i++)
			new Image("res/Giocatori/" + i + "/" + elementi.get(i) + ".png", false,
					Image.FILTER_NEAREST).draw(f, g, 2.0f);
		
		testo.disegna(nome,(int) (f*Config.Scala + 100*Config.Scala),(int) (g*Config.Scala + 80*Config.Scala), Color.white, gc);
		
	}
}
