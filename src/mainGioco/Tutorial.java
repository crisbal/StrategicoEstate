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

public class Tutorial extends BasicGameState
{

	Image tut;
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException
	{
		tut = new Image("res/GUI/tutorial.png");
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException
	{
		tut.draw(0, 0, Config.Scala);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException
	{
		Input input = arg0.getInput();
		if(input.isKeyPressed(input.KEY_ESCAPE)||input.isKeyPressed(input.KEY_ENTER)||input.isKeyPressed(input.KEY_SPACE))
			arg1.enterState(Main.menu, new FadeOutTransition(), new FadeInTransition());
		
	}

	@Override
	public int getID()
	{
		return Main.tut;
	}

}
