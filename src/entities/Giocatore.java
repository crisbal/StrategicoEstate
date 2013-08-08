package entities;

import gui.Testo;

import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Giocatore {
	
	String nome;
	Map elementi = new HashMap<Integer, Integer>();
	Image base;
	Testo testo;
	public Giocatore(String nome,Map<Integer, Integer> elementi) throws SlickException
	{
		this.nome=nome;
		this.elementi=elementi;
		base = new Image("res/Giocatori/Base.png");
		testo = new Testo(Font.BOLD, 16);
	}
	
	
	public void Disegna(int x,int y,GameContainer gc) throws SlickException
	{
		base.draw(x, y, 2f);
		for (int i = 0; i < elementi.size(); i++)
			new Image("res/Giocatori/" + i + "/" + elementi.get(i) + ".png", false,
					Image.FILTER_NEAREST).draw(x, y, 2.0f);
		
		testo.disegna(nome, x + 100, y + 80, Color.white, gc);
		
	}
}
