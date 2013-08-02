package mainGioco;

import java.util.ArrayList;

import entities.Personaggi;
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
	
	QuadratoMappa[][]				mappa		= new QuadratoMappa[Config.COLONNE][Config.RIGHE];
	ArrayList<QuadratoMovimento>	movimenti	= new ArrayList<QuadratoMovimento>();
	int[]							ArrayClick	= new int[2];
	boolean							inBattaglia	= false,caricato = false;
	Bottone							banner;
	
	public Gioca(int state) { // costruttore inutile per ora, ma necessario
	
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		/*
		 * codice per inizializzare (eseguito all'avvio della classe, quando si
		 * entra nello stato
		 */
		banner = new Bottone("Clicca con il tasto sinistro per selezionare, poi dx per muovere!");
		
		mappa = CaricaMappa.caricaQuadrati();
		if(!caricato)
		{
			caricato = !caricato;
			Personaggi.personaggio.add(new PersonaggioGenerico(0, 0, Tipo.SOLDATO, Squadra.ROSSA,0));
			Personaggi.personaggio.add(new PersonaggioGenerico(0, 1, Tipo.CARRO, Squadra.BLU,1));
		}
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// la g indica la grafica, quando si vuole
		// disegnare si passa sempre da lei.
		
		/*
		 * disegno sullo schermo le cose
		 */
		for (int i = 0; i < Config.RIGHE; i++)
		{
			for (int j = 0; j < Config.COLONNE; j++)
				mappa[i][j].Disegna();
		}
		
		for (int i = 0; i < movimenti.size(); i++)
		{
			movimenti.get(i).Disegna();
		}
		
		for (int i = 0; i < Personaggi.personaggio.size(); i++)
		{
			Personaggi.personaggio.get(i).Disegna();
		}
		
		banner.Disegna(250, Config.ALTEZZA - 64);
	}
	
	@Override
	public int getID() {
		
		return Main.gioca;
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		ArrayClick = GestoreMouse.ZonaClick();
		// delta indica il tempo in ms passato dall'ultimo
		// update dello schermo. utile per standardizzare
		// animazioni/movimenti
		
		/*
		 * qui va inserito tutto quello che si vuole venga eseguito ad ogni
		 * update
		 */
		
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
		{
			// ottendo la casella // su
			// cui ha // cliccato
			for (int i = 0; i < Personaggi.personaggio.size(); i++)
			{
				if (ArrayClick[0] == Personaggi.personaggio.get(i).x
						&& ArrayClick[1] == Personaggi.personaggio.get(i).y)
				{
					if (Personaggi.personaggio.get(i).selezionato == false)
					{
						Personaggi.personaggio.get(i).selezionato = true;
					}
					else
						Personaggi.personaggio.get(i).selezionato = false;
				}
				else
				{
					Personaggi.personaggio.get(i).selezionato = false;
				}
				
			}
		}
		
		if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON))
			for (int i = 0; i < Personaggi.personaggio.size(); i++)
			{
				if (Personaggi.personaggio.get(i).selezionato)
				{
					ArrayClick = GestoreMouse.ZonaClick();
					
					if (Math.abs(Personaggi.personaggio.get(i).x - ArrayClick[0])
							+ Math.abs(Personaggi.personaggio.get(i).y - ArrayClick[1]) <= Personaggi.personaggio
								.get(i).raggio)
					{
						Personaggi.difensore = Personaggi.clickSuPersonaggioNemico(Personaggi.personaggio.get(i),ArrayClick[1], ArrayClick[0]);
						if (Personaggi.difensore != null)
						{
							Personaggi.attaccante = Personaggi.personaggio.get(i);
							System.out.println(Personaggi.attaccante.Classe);
							System.out.println(Personaggi.difensore.Classe);
							Battaglia.caricato = false;
							Battaglia.risolto = false;
							Battaglia.timer = 0;
							sbg.enterState(Main.battaglia);
							Personaggi.attaccante.selezionato = false;
							break;
							
						}
						else if (Materiale.Controllo(ArrayClick))
						{
							Personaggi.personaggio.get(i).Sposta(ArrayClick[1], ArrayClick[0]);
							//break;
						}
					}
				}
			}
		
		
	}
	
}