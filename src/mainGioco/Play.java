package mainGioco;

import java.util.ArrayList;

import entities.PersonaggioGenerico;
import entities.Tipo;
import gui.Bottone;
import materiali.Materiale;
import materiali.QuadratoGenerico;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import utils.CaricaMappa;
import utils.GestoreMouse;

public class Play extends BasicGameState {

	ArrayList<PersonaggioGenerico> personaggio = new ArrayList<PersonaggioGenerico>();
	ArrayList<QuadratoGenerico> quadrati = new ArrayList<QuadratoGenerico>();

	int[] ArrayClick = new int[2];
	boolean inBattaglia = false;
	Bottone banner;
	Bottone avviso;

	public Play(int state) { // costruttore inutile per ora, ma necessario

	}

	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		/*
		 * codice per inizializzare (eseguito all'avvio della classe, quando si
		 * entra nello stato
		 */
		banner = new Bottone(
				"Clicca con il tasto sinistro per selezionare, poi dx per muovere!");

		quadrati = CaricaMappa.caricaQuadrati();
		personaggio.add(new PersonaggioGenerico(2, 5, Tipo.SOLDATO, 1));
		personaggio.add(new PersonaggioGenerico(0, 0, Tipo.CARRO, 2));
		personaggio.add(new PersonaggioGenerico(1, 0, Tipo.CARRO, 3));
		personaggio.add(new PersonaggioGenerico(2, 0, Tipo.CARRO, 2));
		personaggio.add(new PersonaggioGenerico(3, 0, Tipo.CARRO, 2));
		personaggio.add(new PersonaggioGenerico(4, 0, Tipo.CARRO, 3));
		personaggio.add(new PersonaggioGenerico(5, 0, Tipo.CARRO, 1));
		personaggio.add(new PersonaggioGenerico(6, 0, Tipo.CARRO, 1));
		personaggio.add(new PersonaggioGenerico(7, 0, Tipo.CARRO, 2));
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
		for (int i = 0; i < personaggio.size(); i++) {
			personaggio.get(i).Disegna();
		}

		banner.Disegna(250, Config.ALTEZZA - 64);
		if (avviso != null)
			avviso.Disegna(300, Config.ALTEZZA / 2);
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

		if (avviso != null)
			if (avviso.Cliccato())
				avviso.Elimina();

		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) // se preme il
															// bottone sinsitro
															// del mouse
		{
			ArrayClick = GestoreMouse.ZonaClick(); // ottendo la casella // su
													// cui ha // cliccato
			for (int i = 0; i < personaggio.size(); i++) {
				if (ArrayClick[0] == personaggio.get(i).x
						&& ArrayClick[1] == personaggio.get(i).y) {
					if (personaggio.get(i).selezionato == false) {
						personaggio.get(i).selezionato = true;
					} else
						personaggio.get(i).selezionato = false;

				} else
					personaggio.get(i).selezionato = false;
			}
		}
		if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON))
			for (int i = 0; i < personaggio.size(); i++) {
				if (personaggio.get(i).selezionato) {
					ArrayClick = GestoreMouse.ZonaClick();
					for (int j = 0; j < personaggio.size(); j++) {
						if (i != j) {
							if (personaggio.get(j).x == ArrayClick[0]
									&& personaggio.get(j).y == ArrayClick[1]
									&& personaggio.get(i).squadra != personaggio
											.get(j).squadra) {
								inBattaglia = true;
								break;
							}
						}
					}
					if (Materiale.Controllo(ArrayClick)) {

						{
							personaggio.get(i).Sposta(ArrayClick[1],
									ArrayClick[0]);
						}
					} else
						avviso = new Bottone(
								"Non puoi muoverti sull'acqua! Clicca per far scomparire");
				}

			}

	}
}
