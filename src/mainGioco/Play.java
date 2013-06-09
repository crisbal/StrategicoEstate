package mainGioco;

import java.util.ArrayList;

import entities.PersonaggioGenerico;
import entities.Tipo;
import gui.Bottone;
import materiali.QuadratoGenerico;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import utils.CaricaMappa;
import utils.GestoreMouse;

public class Play extends BasicGameState {

	PersonaggioGenerico pers1, pers2;
	Bottone bot;
	ArrayList<QuadratoGenerico> quadrati = new ArrayList<QuadratoGenerico>();

	public Play(int state) { // costruttore inutile per ora, ma necessario

	}

	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		/*
		 * codice per inizializzare (eseguito all'avvio della classe, quando si
		 * entra nello stato
		 */

		quadrati = CaricaMappa.caricaQuadrati();

		pers1 = new PersonaggioGenerico(2, 5, Tipo.SOLDATO);
		pers2 = new PersonaggioGenerico(3, 8, Tipo.CARRO);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// la g indica la grafica, quando si vuole
		// disegnare si passa sempre da lei.

		/*
		 * disegno sullo schermo le cose
		 */
		for (int i = 0; i < quadrati.size(); i++) {
			quadrati.get(i).Disegna();
		}

		pers1.Disegna();
		pers2.Disegna();

		if (bot != null) {
			bot.Disegna(250, 250);
		}
	}

	@Override
	public int getID() {

		return 1;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		// delta indica il tempo in ms passato dall'ultimo
		// update dello schermo. utile per standardizzare
		// animazioni/movimenti

		/*
		 * qui va inserito tutto quello che si vuole venga eseguito ad ogni
		 * update
		 */

		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) { // se preme il
																// bottone del
																// mouse
			int[] ArrayClick = GestoreMouse.ZonaClick(); // ottendo la casella
															// su cui ha
															// cliccato
			if (ArrayClick[0] == pers1.x && ArrayClick[1] == pers1.y)
				System.out.println("Hai cliccato il giocatore 1");

			if (ArrayClick[0] == pers2.x && ArrayClick[1] == pers2.y)
				System.out.println("Hai cliccato il giocatore 2");
		}

	}

}
