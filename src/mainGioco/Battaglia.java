package mainGioco;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entities.Personaggi;

public class Battaglia extends BasicGameState {
	
	public Battaglia(int state) {
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		Personaggi.attaccante.Disegna();
		Personaggi.difensore.Disegna();
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		Input input = gc.getInput();
		if (input.isKeyDown(Input.KEY_A))
			sbg.enterState(Main.gioca);
		
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return Main.battaglia;
	}
}
