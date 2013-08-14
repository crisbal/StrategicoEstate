package mainGioco;

import java.awt.Font;
import java.util.ArrayList;

import entities.Personaggi;
import entities.PersonaggioGenerico;
import entities.QuadratoMovimento;
import entities.Squadra;
import entities.Tipo;
import gui.Bottone;
import gui.FinestraInfo;
import gui.Testo;
import materiali.Materiale;
import materiali.QuadratoMappa;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import utils.CaricaMappa;
import utils.GestoreMouse;

public class Gioca extends BasicGameState {
	
	public static QuadratoMappa[][]			mappa;
	private ArrayList<QuadratoMovimento>	movimenti	= new ArrayList<QuadratoMovimento>();
	private int[]							ArrayClick	= new int[2];
	private boolean							inBattaglia	= false, caricato = false;
	private Bottone							banner;
	private int								turno, turnoTotale;
	private Testo							testoTurno;
	FinestraInfo							info		= null;
	private PersonaggioGenerico				pPrec;
	Bottone									potenz;
	
	public Gioca(int state) { // costruttore inutile per ora, ma necessario
	
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		/*
		 * codice per inizializzare (eseguito all'avvio della classe, quando si
		 * entra nello stato
		 */
		
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// la g indica la grafica, quando si vuole
		// disegnare si passa sempre da lei.
		
		/*
		 * disegno sullo schermo le cose
		 */
		if (caricato)
		{
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
				if (Personaggi.personaggio.get(i).inVita)
					Personaggi.personaggio.get(i).Disegna();
			}
			for (int i = 0; i < Config.RIGHE; i++)
			{
				for (int j = 0; j < Config.COLONNE; j++)
				{
					if (mappa[i][j].tipo.equals("acqua"))
						mappa[i][j].Disegna();
				}
			}
			
			g.setColor(Color.black);
			g.fillRect(0, Config.RIGHE * Config.DIMENSIONE_IMMAGINE * Config.Scala, Config.LARGHEZZA, Config.ALTEZZA);
			
			// Personaggi.giocatori[turno-1].Disegna(0*Config.Scala,
			// 600*Config.Scala, gc);
			
			if (info != null)
			{
				info.Disegna(g, gc);
				potenz.Disegna((int) (Personaggi.attaccante.x>Config.COLONNE/2 ? 0 + (info.larghezza - potenz.larghezza)/2:Config.LARGHEZZA - info.larghezza + (info.larghezza - potenz.larghezza)/2),75);
			}
			
			testoTurno.disegna("Turno " + Integer.toString(turnoTotale), Testo.CENTROORIZ,
					gc.getHeight() - testoTurno.ttf.getHeight("Turno"));
		}
	}
	
	@Override
	public int getID() {
		
		return Main.gioca;
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		
		if (!caricato)
		{
			potenz = new Bottone("Potenziamenti");
			potenz.Elimina();
			caricato = !caricato;
			testoTurno = new Testo("Verdana", Font.BOLD, 20,(java.awt.Color) Squadra.squadraAWT.get(1));
			turno = turnoTotale = 1;
			if (Config.mappa != "")
				mappa = CaricaMappa.caricaQuadrati(Config.mappa);
		}
		ArrayClick = GestoreMouse.ZonaClick();
		// delta indica il tempo in ms passato dall'ultimo
		// update dello schermo. utile per standardizzare
		// animazioni/movimenti
		
		/*
		 * qui va inserito tutto quello che si vuole venga eseguito ad ogni
		 * update
		 */
		
		if (input.isKeyPressed(input.KEY_SPACE))
		{
			turno++;
			turnoTotale++;
			if (turno > 2)
				turno = 1;
			testoTurno = new Testo("Verdana", Font.BOLD, 20,(java.awt.Color) Squadra.squadraAWT.get(turno));
			
			for (int i = 0; i < Personaggi.personaggio.size(); i++)
			{
				Personaggi.personaggio.get(i).selezionato = false;
			}
			Personaggi.attaccante = null;
			info = null;
			potenz.Elimina();
		}
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
		{
			// ottendo la casella // su
			// cui ha // cliccato
			if (potenz.Cliccato())
			{
				System.out.println("gnomo");
			}
			else
			{
				for (int i = 0; i < Personaggi.personaggio.size(); i++)
				{
					
					if (Personaggi.personaggio.get(i).inVita)
						if (Personaggi.personaggio.get(i).squadra == turno)
							if (ArrayClick[0] == Personaggi.personaggio.get(i).x
									&& ArrayClick[1] == Personaggi.personaggio.get(i).y)
							{
								
								if (Personaggi.personaggio.get(i).selezionato == false)
								{
									
									Personaggi.personaggio.get(i).selezionato = true;
									Personaggi.attaccante = Personaggi.personaggio.get(i);
									for (int j = 0; j < Personaggi.personaggio.size(); j++)
									{
										if (j != i)
										{
											Personaggi.personaggio.get(j).selezionato = false;
										}
									}
									break;
								}
								else
								{
									Personaggi.personaggio.get(i).selezionato = false;
									Personaggi.attaccante = null;
									info = null;
									potenz.Elimina();
								}
							}
							else
							{
								Personaggi.personaggio.get(i).selezionato = false;
								Personaggi.attaccante = null;
								info = null;
								potenz.Elimina();
							}
				}
				
			}
		}
		
		if (input.isMousePressed(Input.MOUSE_RIGHT_BUTTON))
			for (int i = 0; i < Personaggi.personaggio.size(); i++)
			{
				if (Personaggi.personaggio.get(i).inVita)
					if (Personaggi.personaggio.get(i).selezionato)
					{
						ArrayClick = GestoreMouse.ZonaClick();
						
						if (Math.abs(Personaggi.personaggio.get(i).x - ArrayClick[0])
								+ Math.abs(Personaggi.personaggio.get(i).y - ArrayClick[1]) <= Personaggi.personaggio.get(i).raggio)
						{
							Personaggi.attaccante = Personaggi.personaggio.get(i);
							Personaggi.difensore = Personaggi.clickSuPersonaggioNemico(Personaggi.personaggio.get(i),
									ArrayClick[1], ArrayClick[0]);
							if (Personaggi.difensore != null)
							{
								
								Battaglia.caricato = false;
								Battaglia.risolto = false;
								Battaglia.timer = 0;
								Personaggi.personaggio.get(i).selezionato = false;
								sbg.enterState(Main.battaglia);
								
								break;
								
							}
							else if (Materiale.Controllo(ArrayClick))
							{
								boolean problema = false;
								for (int j = 0; j < Personaggi.personaggio.size(); j++)
								{
									if (Personaggi.personaggio.get(j).x == ArrayClick[0]
											&& Personaggi.personaggio.get(j).y == ArrayClick[1])
										problema = true;
								}
								if (!problema)
								{
									Personaggi.personaggio.get(i).Sposta(ArrayClick[1], ArrayClick[0]);
									Personaggi.attaccante = null;
								}
								// break;
							}
						}
						
					}
			}
		if (Personaggi.attaccante != null)
		{
			if (Personaggi.attaccante.selezionato)
				if (Personaggi.attaccante != pPrec)
				{
					potenz.Ripristina();
					info = new FinestraInfo(Personaggi.attaccante);
					pPrec = Personaggi.attaccante;
				}
		}
		else
		{
			info = null;
			pPrec = null;
		}
	}
	
}