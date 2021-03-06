package mainGioco;

import java.awt.Color;
import java.awt.Font;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entities.Nuvola;
import gui.Testo;

public class Menu extends BasicGameState {
	private int			offset		= 100;
	private Image		menu, selettore;
	private Nuvola[]	nuvole		= new Nuvola[4];
	private int			selezionato	= 0;
	private boolean		indietro;
	private Animation	allies;
	private int			spostamento	= 0;
	public static  Music Sottofondo;
	private Testo opzioniMenuRosso,opzioniMenuVerde,opzioniMenuBlu;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		menu = new Image("res/GUI/MenuVuoto.png");
		selettore = new Image("res/GUI/Selettore.png");
		SpriteSheet sheetAllies = new SpriteSheet("res/GUI/allies.png", 32, 32);
		allies = new Animation();
		Sottofondo = new Music("res/Audio/music.wav");
		
		for (int i = 0; i < 6; i++)
			allies.addFrame(sheetAllies.getSprite(i, 0).getFlippedCopy(true, false), 150);
		
		for (int i = 0; i < 4; i++)
		{
			nuvole[i] = new Nuvola();
		}
		
		opzioniMenuRosso=new Testo("Verdana", Font.BOLD, 56, Color.red);
		opzioniMenuVerde=new Testo("Verdana", Font.BOLD, 56, Color.orange);
		opzioniMenuBlu=new Testo("Verdana", Font.BOLD, 56, Color.blue);
		
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
		if(!Sottofondo.playing())
			Sottofondo.play(1, 0.5f);
		
	}
	@Override
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		menu.draw(0, 0, Config.Scala);
		for (int i = 0; i < 4; i++)
		{
			nuvole[i].immagine.draw(nuvole[i].x * Config.Scala, nuvole[i].y * Config.Scala, nuvole[i].scala * Config.Scala);
		}
		
		if (selezionato == 0)
		{
			selettore.draw((550 * Config.Scala - offset), (200 * Config.Scala - selettore.getHeight() / 2));
		}
		else if (selezionato == 1)
		{
			selettore.draw((550 * Config.Scala - offset), (285 * Config.Scala - selettore.getHeight() / 2));
		}
		else if (selezionato == 2)
		{
			selettore.draw((550 * Config.Scala - offset), (375 * Config.Scala - selettore.getHeight() / 2));
		}
		opzioniMenuRosso.disegna("GIOCA", Testo.CENTROORIZ,(int) (200 * Config.Scala - opzioniMenuRosso.getTotalHeight("GIOCA")/2));
		opzioniMenuVerde.disegna("TUTORIAL", Testo.CENTROORIZ,(int) (285 * Config.Scala - opzioniMenuVerde.getTotalHeight("OPZIONI")/2));
		opzioniMenuBlu.disegna("ESCI", Testo.CENTROORIZ,(int) (375 * Config.Scala - opzioniMenuBlu.getTotalHeight("ESCI")/2));
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 8; j++)
				allies.draw((100 + i * 30 + spostamento) * Config.Scala, (450 + j * 30) * Config.Scala, allies.getWidth()
						* Config.Scala, allies.getHeight() * Config.Scala);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO Auto-generated method stub
		Input input = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_UP) || input.isKeyPressed(Input.KEY_W))
		{
			selezionato--;
		}
		else if (input.isKeyPressed(Input.KEY_DOWN) || input.isKeyPressed(Input.KEY_S))
		{
			selezionato++;
		}
		
		if (input.isKeyPressed(Input.KEY_ENTER) || input.isKeyPressed(Input.KEY_SPACE))
			switch (selezionato)
			{
				case 0:
					
					sbg.enterState(Main.scegliMappa);
					break;
				case 1:
					sbg.enterState(Main.tut);
					break;
				case 2:
					gc.exit();
					break;
			}
		
		
		if (selezionato < 0)
			selezionato = 2;
		if (selezionato > 2)
			selezionato = 0;
		
		if (offset > (150) * Config.Scala || indietro)
		{
			offset -= 0.2f * delta;
			indietro = true;
		}
		if (offset <= (100) * Config.Scala || !indietro)
		{
			offset += 0.2f * delta;
			indietro = false;
		}
		
		for (int i = 0; i < 4; i++)
		{
			nuvole[i].x += nuvole[i].speed * delta;
			if (nuvole[i].x < (-400 * Config.Scala))
				nuvole[i] = new Nuvola();
		}
		
		spostamento += 0.07f * delta;
		if (spostamento > Config.LARGHEZZA * Config.Scala + allies.getWidth() * 20 * Config.Scala)
			spostamento = (int) (-allies.getWidth() * 20 - 100);
	
		if(input.isKeyDown(input.KEY_ESCAPE));

	}
	
	public int getID() {
		// TODO Auto-generated method stub
		return Main.menu;
	}
	
}
