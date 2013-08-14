package mainGioco;

import gui.Testo;

import java.awt.Font;
import java.io.File;
import java.awt.Color;
import materiali.QuadratoMappa;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.GradientEffect;
import org.newdawn.slick.font.effects.OutlineEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import utils.CaricaMappa;

public class ScegliMappa extends BasicGameState {
	
	File[]							files;
	private QuadratoMappa[][]	mappa;
	private int	puntatore;
	Testo testo,sottoTesto;
	UnicodeFont test;
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		File folder = new File("mappe/");
		files = folder.listFiles();
		mappa = CaricaMappa.caricaQuadrati(files[0].getName());
		testo = new Testo("Verdana", Font.BOLD, 20,Color.red);
		sottoTesto = new Testo("Verdana", Font.BOLD, 16,Color.blue);
		test = new UnicodeFont(new Font("Verdana", Font.BOLD, 40));
		test.getEffects().add(new ColorEffect(java.awt.Color.white));
		test.getEffects().add(new GradientEffect(Color.red, Color.white, (float) 0.5));
		test.addAsciiGlyphs();
		test.loadGlyphs();
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.setFont(testo.ttf);
		test.drawString((int) (100*Config.Scala),(int) (5*Config.Scala),"Scegli la mappa\n\n su cui vuoi giocare: ");
		for (int i = 0; i < files.length; i++)
		{
			test.drawString(100 * Config.Scala,200 * Config.Scala + i * 25 * Config.Scala, files[i].getName());
		}
		
		for (int i = 0; i < Config.RIGHE; i++)
		{
			for (int j = 0; j < Config.COLONNE; j++)
				mappa[i][j].DisegnaScalato(Config.LARGHEZZA/3,32,0.5f);
		}
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO Auto-generated method stub
		Input input = gc.getInput();
		
		if (input.isKeyPressed(Input.KEY_W))
		{
			puntatore--;
			if (puntatore < 0)
				puntatore = files.length-1;
			
			mappa = CaricaMappa.caricaQuadrati(files[puntatore].getName());
		}
		else if (input.isKeyPressed(Input.KEY_S))
		{
			puntatore++;
			if (puntatore > files.length-1)
				puntatore = 0;
			
			mappa = CaricaMappa.caricaQuadrati(files[puntatore].getName());
		}
		
		if (input.isKeyPressed(Input.KEY_ENTER))
		{
			Config.mappa = files[puntatore].getName();
			sbg.enterState(Main.creaPersonaggio);
			//sbg.enterState(Main.gioca,new FadeOutTransition(Color.black),new FadeInTransition(Color.black));
		}
		
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return Main.scegliMappa;
	}
	
}
