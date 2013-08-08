package mainGioco;

import java.io.File;
import java.nio.file.Files;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class ScegliMappa extends BasicGameState {

	File[] files;
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		File folder = new File("mappe/");
		files = folder.listFiles();
		
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		for(int i = 0;i<files.length;i++)
		{
			g.drawString(files[i].getName(), 100*Config.Scala, 100*Config.Scala+i*10*Config.Scala);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return Main.scegliMappa;
	}
	
}
