package mainGioco;

import java.awt.Font;
import java.util.ArrayList;

import entities.Personaggi;
import entities.PersonaggioGenerico;
import entities.QuadratoMovimento;
import entities.Squadra;
import gui.Bottone;
import gui.FinestraBase;
import gui.FinestraInfo;
import gui.Testo;
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
	
	public static QuadratoMappa[][]			mappa;
	private ArrayList<QuadratoMovimento>	movimenti		= new ArrayList<QuadratoMovimento>();
	private int[]							ArrayClick		= new int[2];
	public static boolean					caricato		= false;
	private Bottone							bottoneCliccaPerPosizionare;
	private int								turno			= 1, turnoTotale = 1;
	private Testo							testoTurno;
	private FinestraInfo					info			= null;
	private FinestraBase					base			= null;
	private PersonaggioGenerico				pPrec;
	private Bottone							potenz;
	private ArrayList<Bottone>				bottoniBase		= new ArrayList<Bottone>();
	private String[]						infoAggiuntivePlayerScelto;
	private boolean							staComprando	= false;
	private int								vittoria		= -1;
	private boolean							controllo;
	
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
			
			// g.setColor(Color.black);
			// g.fillRect(0, Config.RIGHE * Config.DIMENSIONE_IMMAGINE *
			// Config.Scala, Config.LARGHEZZA, Config.ALTEZZA);
			
			// Personaggi.giocatori[turno-1].Disegna(0*Config.Scala,
			// 600*Config.Scala, gc);
			
			if (info != null && potenz != null)
			{
				info.Disegna(g, gc);
				potenz.Disegna((int) (Personaggi.attaccante.x > Config.COLONNE / 2 ? 0 + (info.larghezza - potenz.larghezza) / 2
						: Config.LARGHEZZA - info.larghezza + (info.larghezza - potenz.larghezza) / 2), 75);
				base = null;
				
			}
			if (base != null)
			{
				base.Disegna(g, gc);
				int i = 0;
				for (Bottone bottone : bottoniBase)
				{
					if (Personaggi.giocatori.get(turno - 1).soldi >= Integer.parseInt(bottone.infoAggiuntive[1]))
					{
						bottone.Disegna(
								(int) (Personaggi.attaccante.x > Config.COLONNE / 2 ? 0 + (bottone.larghezza - potenz.larghezza) / 2
										: Config.LARGHEZZA - bottone.larghezza + (bottone.larghezza - potenz.larghezza) / 2),
								75 + (i * bottone.altezza));
						i++;
					}
					
				}
				info = null;
			}
			testoTurno.disegna("Turno " + Integer.toString(turnoTotale), Testo.CENTROORIZ,
					gc.getHeight() - testoTurno.ttf.getHeight("Turno"));
			
			testoTurno.disegna(Integer.toString(Personaggi.giocatori.get(turno - 1).soldi), 0, 700);
			if (staComprando)
			{
				bottoneCliccaPerPosizionare.Disegna(Config.LARGHEZZA / 2 - bottoneCliccaPerPosizionare.larghezza / 2,
						gc.getHeight() - bottoneCliccaPerPosizionare.altezza);
			}
		}
	}
	
	@Override
	public int getID() {
		
		return Main.gioca;
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		ArrayClick = GestoreMouse.ZonaClick();
		
		boolean cliccato = false;
		if (!caricato)
		{
			caricato = !caricato;
			
			caricaBottoni();
			
			turno = turnoTotale = 1;
			
			mappa = CaricaMappa.caricaQuadrati(Config.mappa);
		}
		
		if (!Config.conBasi)
		{
			for (int i = 0; i < Personaggi.giocatori.size(); i++)
			{
				if (nUnitaSquadra(Personaggi.giocatori.get(i).squadra) == 0)
				{
					vittoria = Personaggi.giocatori.get(i).squadra;
					break;
				}
				else
					vittoria = -1;
			}
		}
		else
		{
			// TODO GESTIRE LA DISTRUZIONE DELLE TORRI
		}
		
		if (vittoria == -1)
		{
			
			if (input.isKeyPressed(Input.KEY_SPACE))
			{
				if (!staComprando)
				{
					if (!controllo)
						controllo = !controllo;
					else
					{
						turno++;
						turnoTotale++;
						
						if (turno > Config.nSquadre)
							turno = 1;
						
						testoTurno = new Testo("Verdana", Font.BOLD, 20, (java.awt.Color) Squadra.squadraAWT.get(turno));
						
						deselezionaPersonaggi();
						
						pulisciGUI();
					}
					
				}
			}
			controllo = true;
			
			if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
			{
				// ottendo la casella // su
				// cui ha // cliccato
				
				if (staComprando)
				{
					staComprando = false;
					if (Personaggi.giocatori.get(turno - 1).soldi >= Integer.parseInt(infoAggiuntivePlayerScelto[1]))
					{
						Personaggi.personaggio.add(new PersonaggioGenerico(ArrayClick[1], ArrayClick[0],
								infoAggiuntivePlayerScelto[0], turno, Personaggi.personaggio.size()));
						
						Personaggi.giocatori.get(turno - 1).soldi -= Integer.parseInt(infoAggiuntivePlayerScelto[1]);
					}
					pulisciGUI();
					
					deselezionaPersonaggi();
				}
				else
				{
					for (Bottone bottone : bottoniBase)
					{
						if (bottone.Cliccato())
						{
							infoAggiuntivePlayerScelto = bottone.infoAggiuntive;
							staComprando = true;
							cliccato = true;
						}
					}
					if (!cliccato)
					{
						
						if (potenz.Cliccato())
						{
							//TODO CODICE DI BARILANI!!!!
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
												pulisciGUI();
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
												pPrec = null;
												pulisciGUI();
											}
										}
										else
										{
											Personaggi.personaggio.get(i).selezionato = false;
											Personaggi.attaccante = null;
											pPrec = null;
											pulisciGUI();
										}
							}
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
									pulisciGUI();
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
										pulisciGUI();
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
						if (!Personaggi.attaccante.Classe.equals("Base"))
						{
							potenz.Ripristina();
							info = new FinestraInfo(Personaggi.attaccante);
							base = null;
							for (Bottone bottone : bottoniBase)
							{
								bottone.Elimina();
							}
						}
						else
						{
							base = new FinestraBase(Personaggi.attaccante);
							for (Bottone bottone : bottoniBase)
							{
								bottone.Ripristina();
							}
							info = null;
							potenz.Elimina();
						}
						
						pPrec = Personaggi.attaccante;
					}
			}
			else
			{
				pulisciGUI();
			}
		}
		else
		{
			if (input.isKeyPressed(input.KEY_ESCAPE) || input.isKeyPressed(input.KEY_SPACE)
					|| input.isKeyPressed(input.KEY_ENTER))
				sbg.enterState(Main.menu);
		}
	}
	
	private void pulisciGUI() {
		info = null;
		potenz.Elimina();
		base = null;
		for (Bottone bottone : bottoniBase)
		{
			bottone.Elimina();
		}
		
	}
	
	private void deselezionaPersonaggi() {
		
		for (int i = 0; i < Personaggi.personaggio.size(); i++)
		{
			Personaggi.personaggio.get(i).selezionato = false;
		}
		
		Personaggi.attaccante = null;
		
	}
	
	private void caricaBottoni() throws SlickException {
		bottoneCliccaPerPosizionare = new Bottone("Clicca sulla casella dove \nvuoi posizionare l'unità appena comprata!");
		potenz = new Bottone("Potenziamenti");
		potenz.Elimina();
		
		bottoniBase.add(new Bottone("Soldato (50)", "Soldato", "50"));
		bottoniBase.get(0).Elimina();
		bottoniBase.add(new Bottone("Carro (100)", "Carro", "100"));
		bottoniBase.get(1).Elimina();
		
		testoTurno = new Testo("Verdana", Font.BOLD, 20, (java.awt.Color) Squadra.squadraAWT.get(1));
		
	}
	
	public int nUnitaSquadra(int squadra) {
		int c = 0;
		for (int i = 0; i < Personaggi.personaggio.size(); i++)
		{
			if (Personaggi.personaggio.get(i).squadra == squadra && Personaggi.personaggio.get(i).inVita)
				c++;
		}
		return c;
	}
	
	public boolean cercaBase(int squadra) {
		for (int i = 0; i < Personaggi.personaggio.size(); i++)
		{
			if (Personaggi.personaggio.get(i).squadra == squadra && Personaggi.personaggio.get(i).Classe == "Base"
					&& Personaggi.personaggio.get(i).inVita)
				return true;
		}
		return false;
	}
}