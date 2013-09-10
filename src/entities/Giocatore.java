package entities;

import gui.Testo;

import java.awt.Font;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import mainGioco.Config;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Giocatore {
	
	String nome;
	
	public int[] elementi = new int[4];
	
	Image base;
	Testo testo;
	public int soldi;
	public int squadra;
	
	public Giocatore(String nome, int[] elementi,int Squadra) throws SlickException
	{
		
		this.nome=nome;
		System.arraycopy(elementi, 0, this.elementi, 0, elementi.length);
		base = new Image("res/Giocatori/Base.png");
		testo = new Testo("Verdana",Font.BOLD, 16, java.awt.Color.white);
		soldi = Config.soldi;
		squadra = Squadra;
	}
	
	
	public void Disegna(float f,float g,GameContainer gc) throws SlickException
	{
		base.draw(f, g, 2f*Config.Scala);
		for (int i = 0; i < elementi.length; i++)
			new Image("res/Giocatori/" + i + "/" + elementi[i] + ".png", false,
					Image.FILTER_NEAREST).draw(f, g, 2.0f*Config.Scala);
	}
}
