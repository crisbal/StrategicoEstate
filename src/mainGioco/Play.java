package mainGioco;

import java.util.ArrayList;

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

		CaricaMappa gestoreMappa = new CaricaMappa();
		int[] mappa = gestoreMappa.Carica();

		if (!caricato)
			for (int i = 0; i < mappa.length; i++) {
				caricato = true;
				String Tipo = "";
				switch (mappa[i]) {
				case 0:
					Tipo = Materiali.ERBA;
					break;
				case 1:
					Tipo = Materiali.ACQUA;
					break;
				case 2:
					Tipo = Materiali.MONTAGNA;
					break;
				}
				quadrati.add(new QuadratoGenerico(Tipo));
				System.out.println(i + Tipo);
			}

		bot = new Bottone("QUESTO E' UNA SPECIE DI BOTTONE!");
		bot2 = new Bottone("Cliccalo per eliminarlo!");
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		// la g indica la grafica, quando si vuole
		// disegnare si passa sempre da lei.

		/*
		 * disegno sullo schermo le cose
		 */
		int j=0;
		int x=0;
		for(int i=0;i<quadrati.size();i++)
		{
			if(i%6==0&&i!=0)
			{
				j++;
				x=0;
			}
			
			quadrati.get(i).Disegna(j*quadrati.get(i).Texture.getWidth(),x*quadrati.get(i).Texture.getHeight(), g);
			x++;
		}
		
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
