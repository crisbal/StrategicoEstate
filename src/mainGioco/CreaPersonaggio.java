package mainGioco;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entities.Giocatore;
import entities.Personaggi;
import entities.Squadra;

public class CreaPersonaggio extends BasicGameState {
	
	Map<Integer, Integer>	ElementiPersonaggio	= new HashMap<Integer, Integer>();
	Map<Integer, String>	NomeElementi		= new HashMap<Integer, String>();
	
	Image					Base;
	Image[]					ImmagineElementi	= new Image[4];
	int						puntatore			= 0, nAdd = 0;
	TextField				nome;
	Giocatore				gDaAggiungere;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < 4; i++)
		{
			ElementiPersonaggio.put(i, 0);
		}
		NomeElementi.put(0, "OCCHI");
		NomeElementi.put(1, "NASO");
		NomeElementi.put(2, "BOCCA");
		NomeElementi.put(3, "CAPELLI");
		Base = new Image("res/Giocatori/Base.png", false, Image.FILTER_NEAREST);
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.setColor(Color.white);
		g.fillRect(200, 200, 500, 500);
		g.setColor(Color.red);
		g.drawString((String) NomeElementi.get(puntatore), 100, 100);
		g.drawString("W / S CAMBIA TIPOLOGIA   A / D CAMBIA LIVELLO", 200, 100);
		Base.draw(300, 300, 2f);
		for (int i = 0; i < ImmagineElementi.length; i++)
			ImmagineElementi[i].draw(300, 300, 2f);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO Auto-generated method stub
		for (int i = 0; i < ImmagineElementi.length; i++)
		{
			ImmagineElementi[i] = new Image("res/Giocatori/" + i + "/" + ElementiPersonaggio.get(i) + ".png", false,
					Image.FILTER_NEAREST);
		}
		
		Input input = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_W))
		{
			puntatore--;
			if (puntatore < 0)
				puntatore = 3;
		}
		else if (input.isKeyPressed(Input.KEY_S))
		{
			puntatore++;
			if (puntatore > 3)
				puntatore = 0;
		}
		
		if (input.isKeyPressed(Input.KEY_A))
		{
			ElementiPersonaggio.put(puntatore, (int) ElementiPersonaggio.get(puntatore) - 1);
			if ((int) ElementiPersonaggio.get(puntatore) < 0)
				ElementiPersonaggio.put(puntatore, 3);
		}
		else if (input.isKeyPressed(Input.KEY_D))
		{
			ElementiPersonaggio.put(puntatore, (int) ElementiPersonaggio.get(puntatore) + 1);
			if ((int) ElementiPersonaggio.get(puntatore) > 3)
				ElementiPersonaggio.put(puntatore, 0);
		}
		
		if (input.isKeyPressed(Input.KEY_ENTER))
		{

			gDaAggiungere = new Giocatore("Player" + nAdd, ElementiPersonaggio, Personaggi.giocatori.size() + 1);
			
			Personaggi.giocatori.add(gDaAggiungere);
			nAdd++;
			// System.out.println(Personaggi.giocatori.get(0).elementi.get(0));
			if (nAdd < Config.nSquadre)
			{
				init(gc, sbg);
			}
			else
			{
				// Personaggi.giocatori.addAll();
				Gioca.caricato = false;
				sbg.enterState(Main.gioca);
			}
		}
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return Main.creaPersonaggio;
	}
	
}
