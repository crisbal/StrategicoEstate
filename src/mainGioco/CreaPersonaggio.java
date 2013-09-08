package mainGioco;

import java.awt.Font;
import java.util.HashMap;
import java.util.Hashtable;
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
import gui.Testo;

public class CreaPersonaggio extends BasicGameState
{

	int[] Elementi = new int[4];
	Map<Integer, String> NomeElementi = new Hashtable<Integer, String>();

	Image Base;
	Image Sfondo;
	Image[] ImmagineElementi = new Image[4];
	public int puntatore = 0;
	public static int nAdd = 0;
	Giocatore gDaAggiungere;
	Testo testoCrea;
	Testo istruzioni;
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		// TODO Auto-generated method stub
		testoCrea = new Testo("Verdana", Font.BOLD, 45, (java.awt.Color) Squadra.squadraAWT.get(Personaggi.giocatori.size() + 1));
		istruzioni = new Testo("Verdana", Font.BOLD, 20, java.awt.Color.black);
		for (int i = 0; i < 4; i++)
		{
			Elementi[i] = 0;
		}
		Sfondo = new Image("res/GUI/MenuVuoto.png");
		NomeElementi.put(0, "OCCHI");
		NomeElementi.put(1, "NASO");
		NomeElementi.put(2, "BOCCA");
		NomeElementi.put(3, "CAPELLI");
		Base = new Image("res/Giocatori/Base.png", false, Image.FILTER_NEAREST);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		// TODO Auto-generated method stub
		Sfondo.draw(0, 0, Config.Scala);
		testoCrea.disegna("GIOCATORE " +  (Personaggi.giocatori.size() + 1) + " CREA IL TUO PERSONAGGIO", Testo.CENTROORIZ, 25*Config.Scala);
		istruzioni.disegna("Usa SU/GIU per spostarti tra i vari elementi, SX/DX per cambiare il loro valore", Testo.CENTROORIZ,25*Config.Scala +  testoCrea.getTotalHeight("GIOCATORE " +  (Personaggi.giocatori.size() + 1) + " CREA IL TUO PERSONAGGIO"));
		istruzioni.disegna(NomeElementi.get(puntatore), Testo.CENTROORIZ, gc.getHeight()/2-Base.getHeight()*2f);
		istruzioni.disegna("Premi invio quando sei contento dell'aspetto del tuo giocatore...", Testo.CENTROORIZ, gc.getHeight()-100*Config.Scala);
		Base.draw(gc.getWidth()/2-Base.getWidth()/2*Config.Scala*3f, gc.getHeight()/2-(Base.getHeight()/2)*Config.Scala*3f, 3f*Config.Scala);
		for (int i = 0; i < ImmagineElementi.length; i++)
			ImmagineElementi[i].draw(gc.getWidth()/2-Base.getWidth()/2*Config.Scala*3f, gc.getHeight()/2-(Base.getHeight()/2)*Config.Scala*3f, 3f*Config.Scala);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		// TODO Auto-generated method stub
		for (int i = 0; i < ImmagineElementi.length; i++)
		{
			ImmagineElementi[i] = new Image("res/Giocatori/" + i + "/" + Elementi[i] + ".png", false, Image.FILTER_NEAREST);
		}

		Input input = gc.getInput();

		if (input.isKeyPressed(Input.KEY_W))
		{
			puntatore--;
			if (puntatore < 0)
				puntatore = 3;
		} else if (input.isKeyPressed(Input.KEY_S))
		{
			puntatore++;
			if (puntatore > 3)
				puntatore = 0;
		}

		if (input.isKeyPressed(Input.KEY_A))
		{
			Elementi[puntatore]--;

			if (Elementi[puntatore] < 0)
				Elementi[puntatore] = 4;
		} else if (input.isKeyPressed(Input.KEY_D))
		{
			Elementi[puntatore]++;

			if (Elementi[puntatore] > 4)
				Elementi[puntatore] = 0;
		}

		if (input.isKeyPressed(Input.KEY_ENTER))
		{

			gDaAggiungere = new Giocatore("Player" + nAdd, Elementi, Personaggi.giocatori.size() + 1);

			Personaggi.giocatori.add(gDaAggiungere);

			for (int i = 0; i < 4; i++)
			{
				Elementi[i] = 0;
			}

			nAdd++;
			puntatore = 0;
			
			if (nAdd < Config.nSquadre)
			{
				init(gc, sbg);
			} else
			{
				Gioca.caricato = false;
				if(Menu.Sottofondo.playing())
					Menu.Sottofondo.fade(3000, 0, true);
				sbg.enterState(Main.gioca);
			}
		}
	}

	@Override
	public int getID()
	{
		// TODO Auto-generated method stub
		return Main.creaPersonaggio;
	}

}
