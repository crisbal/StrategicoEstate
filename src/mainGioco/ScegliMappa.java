package mainGioco;

import entities.Personaggi;
import gui.Testo;

import java.awt.Font;
import java.io.File;
import java.awt.Color;

import materiali.QuadratoMappa;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.GradientEffect;
import org.newdawn.slick.font.effects.OutlineEffect;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import utils.CaricaMappa;

public class ScegliMappa extends BasicGameState {
	
	File[]						files;
	private QuadratoMappa[][]	mappa;
	private int					puntatore;
	Testo						testo, sottoTesto;
	Image						freccia, sfondo;
	Rectangle					bordo;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		File folder = new File("mappe/");
		files = folder.listFiles();
		mappa = CaricaMappa.caricaQuadrati(files[0].getName());
		testo = new Testo("Verdana", Font.BOLD, 25, Color.red);
		sottoTesto = new Testo("Verdana", Font.BOLD, 20, Color.black);
		freccia = new Image("res/GUI/Selettore.png");
		sfondo = new Image("res/GUI/MenuVuoto.png");
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		
		sfondo.draw(0, 0, Config.Scala);
		testo.disegna("Seleziona la mappa su cui giocherai ", (int) (100 * Config.Scala), (int) (5 * Config.Scala));
		for (int i = 0; i < files.length; i++)
		{
			sottoTesto.disegna(files[i].getName(), (int) (100 * Config.Scala), (int) (70 * Config.Scala + i * 50 * Config.Scala));
		}
		
		for (int i = 0; i < Config.RIGHE; i++)
		{
			for (int j = 0; j < Config.COLONNE; j++)
				mappa[i][j].DisegnaScalato((int) ((Config.LARGHEZZA / Config.Scala) / 3), 70, 0.5f);
		}
		freccia.draw((50 * Config.Scala), (70 * Config.Scala + puntatore * 50 * Config.Scala + freccia.getHeight() / 2),
				Config.Scala);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO Auto-generated method stub
		Input input = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_W) || input.isKeyPressed(Input.KEY_UP))
		{
			puntatore--;
			if (puntatore < 0)
				puntatore = files.length - 1;
			
			mappa = CaricaMappa.caricaQuadrati(files[puntatore].getName());
		}
		else if (input.isKeyPressed(Input.KEY_S) || input.isKeyPressed(Input.KEY_DOWN))
		{
			puntatore++;
			if (puntatore > files.length - 1)
				puntatore = 0;
			
			mappa = CaricaMappa.caricaQuadrati(files[puntatore].getName());
		}
		
		if (input.isKeyPressed(Input.KEY_ENTER) || input.isKeyPressed(Input.KEY_SPACE))
		{
			Config.mappa = files[puntatore].getName();
			Personaggi.giocatori.clear();
			sbg.enterState(Main.creaPersonaggio);
			// sbg.enterState(Main.gioca,new FadeOutTransition(Color.black),new
			// FadeInTransition(Color.black));
		}
		if (input.isKeyPressed(Input.KEY_ESCAPE))
		{
			sbg.enterState(Main.menu);
		}
		
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return Main.scegliMappa;
	}
	
}
