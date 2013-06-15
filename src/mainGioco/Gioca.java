package mainGioco;

import java.util.ArrayList;

import entities.CachePersonaggi;
import entities.PersonaggioGenerico;
import entities.QuadratoMovimento;
import entities.Squadra;
import entities.Tipo;
import gui.Bottone;
import materiali.Materiale;
import materiali.QuadratoMappa;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import utils.CaricaMappa;
import utils.GestoreMouse;

public class Gioca extends BasicGameState {

	QuadratoMappa[][] mappa = new QuadratoMappa[Config.COLONNE][Config.RIGHE];
	ArrayList<QuadratoMovimento> movimenti = new ArrayList<QuadratoMovimento>();
	int[] ArrayClick = new int[2];
	boolean inBattaglia = false;
	Bottone banner;
	Bottone avviso;
	CachePersonaggi personaggi;

	public Gioca(int state) { // costruttore inutile per ora, ma necessario

	}

	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		/*
		 * codice per inizializzare (eseguito all'avvio della classe, quando si
		 * entra nello stato
		 */
		banner = new Bottone(
				"Clicca con il tasto sinistro per selezionare, poi dx per muovere!");

		mappa = CaricaMappa.caricaQuadrati();
		if (CachePersonaggi.personaggio.size() == 0) {
			CachePersonaggi.personaggio.add(new PersonaggioGenerico(2, 5,
					Tipo.SOLDATO, Squadra.ROSSA));
			System.out.println("Gnomo");
		}

	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// la g indica la grafica, quando si vuole
		// disegnare si passa sempre da lei.

		/*
		 * disegno sullo schermo le cose
		 */
		for (int i = 0; i < Config.RIGHE; i++) {
			for (int j = 0; j < Config.COLONNE; j++)
				mappa[i][j].Disegna();
		}

		for (int i = 0; i < movimenti.size(); i++) {
			movimenti.get(i).Disegna();
		}

		for (int i = 0; i < CachePersonaggi.personaggio.size(); i++) {
			CachePersonaggi.personaggio.get(i).Disegna();

		}

		banner.Disegna(250, Config.ALTEZZA - 64);
		if (avviso != null)
			avviso.Disegna(300, Config.ALTEZZA / 2);
	}

	@Override
	public int getID() {

		return Main.gioca;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		ArrayClick = GestoreMouse.ZonaClick();
		System.out.println(CachePersonaggi.personaggio.size());
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

		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			// ottendo la casella // su
			// cui ha // cliccato
			for (int i = 0; i < CachePersonaggi.personaggio.size(); i++) {
				if (ArrayClick[0] == CachePersonaggi.personaggio.get(i).x
						&& ArrayClick[1] == CachePersonaggi.personaggio.get(i).y) {
					if (CachePersonaggi.personaggio.get(i).selezionato == false) {
						CachePersonaggi.personaggio.get(i).selezionato = true;
					} else
						CachePersonaggi.personaggio.get(i).selezionato = false;

				} else {
					CachePersonaggi.personaggio.get(i).selezionato = false;
				}

			}
		}

		if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON))
			for (int i = 0; i < CachePersonaggi.personaggio.size(); i++) 
			{
				if (CachePersonaggi.personaggio.get(i).selezionato) {
					ArrayClick = GestoreMouse.ZonaClick();
					if (Materiale.Controllo(ArrayClick)) {
						if (Math.abs(CachePersonaggi.personaggio.get(i).x
								- ArrayClick[0])
								+ Math.abs(CachePersonaggi.personaggio.get(i).y
										- ArrayClick[1]) <= CachePersonaggi.personaggio
									.get(i).raggio) {
							CachePersonaggi.personaggio.get(i).Sposta(
									ArrayClick[1], ArrayClick[0]);
						}
					} else
						avviso = new Bottone(
								"Non puoi muoverti sull'acqua! Clicca per far scomparire");
				}
			}
		
		if(input.isKeyDown(input.KEY_W))
			sbg.enterState(Main.battaglia);
	}

}
