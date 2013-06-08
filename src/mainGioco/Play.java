package mainGioco;

import java.util.ArrayList;

import entities.PersonaggioGenerico;
import entities.Tipo;
import gui.Bottone;
import materiali.Materiali;
import materiali.QuadratoGenerico;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import utils.CaricaMappa;

public class Play extends BasicGameState {

	Bottone bot, bot2;
	PersonaggioGenerico pers1,pers2;
	boolean caricato = false;
	ArrayList<QuadratoGenerico> quadrati = new ArrayList<QuadratoGenerico>();

	public Play(int state) { // costruttore inutile per ora, ma necessario

	}

	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		/*
		 * codice per inizializzare (eseguito all'avvio della classe, quando si
		 * entra nello stato
		 */

		quadrati=CaricaMappa.caricaQuadrati();

		bot = new Bottone("QUESTO E' UNA SPECIE DI BOTTONE!");
		bot2 = new Bottone("Cliccalo per eliminarlo!");
		pers1=new PersonaggioGenerico(0, 0, Tipo.SOLDATO);
		pers2=new PersonaggioGenerico(3, 0, Tipo.CARRO);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// la g indica la grafica, quando si vuole
		// disegnare si passa sempre da lei.

		/*
		 * disegno sullo schermo le cose
		 */
		for(int i=0;i<quadrati.size();i++)
		{
			quadrati.get(i).Disegna();
		}
		
		pers1.Disegna();
		pers2.Disegna();
		
		bot.Disegna(0, gc.getHeight() / 2, g);
		bot2.Disegna(gc.getWidth() / 2, 0, g);
	}

	@Override
	public int getID() {

		return 1;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		// delta indica il tempo in ms passato dall'ultimo
		// update dello schermo. utile per standardizzare
		// animazioni/movimenti

		/*
		 * qui va inserito tutto quello che si vuole venga eseguito ad ogni
		 * update
		 */
		bot.Update(gc);
		bot2.Update(gc);

	}

}
