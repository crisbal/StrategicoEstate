package mainGioco;

import java.io.File;
import materiali.QuadratoMappa;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import utils.CaricaMappa;

public class ScegliMappa extends BasicGameState {
	
	File[]							files;
	private QuadratoMappa[][]	mappa;
	private int	puntatore;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		File folder = new File("mappe/");
		files = folder.listFiles();
		mappa = CaricaMappa.caricaQuadrati(files[0].getName());
		
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		for (int i = 0; i < files.length; i++)
		{
			g.drawString(files[i].getName(), 100 * Config.Scala, 100 * Config.Scala + i * 50 * Config.Scala);
		}
		
		for (int i = 0; i < Config.RIGHE; i++)
		{
			for (int j = 0; j < Config.COLONNE; j++)
				mappa[i][j].DisegnaScalato(gc.getWidth()/2,32,0.5f);
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
