package mainGioco;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class SplashScreen extends BasicGameState {
	Image	splash;
	int		timer;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		splash = new Image("res/logo.png");
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		splash.draw(gc.getWidth() / 2 - splash.getWidth() / 2, gc.getHeight() / 2 - splash.getHeight() / 2);
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		timer += delta;
		if (timer > 5000 || input.isKeyPressed(input.KEY_ESCAPE) || input.isKeyPressed(input.KEY_SPACE)
				|| input.isKeyPressed(input.KEY_ENTER))
			sbg.enterState(Main.menu, new FadeOutTransition(), new FadeInTransition());
	}
	
	@Override
	public int getID() {
		return Main.splash;
	}
	
}
