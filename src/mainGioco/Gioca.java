package mainGioco;

import java.awt.Color;
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
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import utils.CaricaMappa;
import utils.GestoreMouse;

public class Gioca extends BasicGameState
{

	public static QuadratoMappa[][] mappa;
	private ArrayList<QuadratoMovimento> movimenti = new ArrayList<QuadratoMovimento>();
	private int[] ArrayClick = new int[2];
	public static boolean caricato = false;
	private Bottone bottoneCliccaPerPosizionare;
	private int turno = 1, turnoTotale = 1;
	private Testo testoTurno, testoVittoria;
	private FinestraInfo info = null;
	private FinestraBase base = null;
	private PersonaggioGenerico pPrec;
	private Bottone potenz;
	private ArrayList<Bottone> bottoniBase = new ArrayList<Bottone>();
	private String[] infoAggiuntivePlayerScelto;
	private boolean staComprando = false;
	private int vittoria = -1;
	private boolean controllo;
	private Image coin, menu, menuChild;
	private boolean inMenu = false;
	private Bottone[] opzioniMenu;
	private Sound click, movimento, battaglia;

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		/*
		 * codice per inizializzare (eseguito all'avvio della classe, quando si
		 * entra nello stato
		 */
		coin = new Image("res/GUI/Coin.png");
		menu = new Image("res/GUI/InGameMenu.png");
		menuChild = new Image("res/GUI/InGameMenuMenu.png");
		click = new Sound("res/Audio/click.wav");
		movimento = new Sound("res/Audio/movimento.wav");
		battaglia = new Sound("res/Audio/inizioBattaglia.wav");
		opzioniMenu = new Bottone[3];
		opzioniMenu[0] = new Bottone("Riprendi Partita");
		opzioniMenu[1] = new Bottone("Menu Principale");
		opzioniMenu[2] = new Bottone("Esci dal Gioco");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
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
			if (vittoria == -1)
			{

				if (info != null && potenz != null)
				{
					info.Disegna(g, gc);
					potenz.Disegna((int) (Personaggi.attaccante.x > Config.COLONNE / 2 ? 0 + (info.larghezza - potenz.larghezza) / 2 : Config.LARGHEZZA - info.larghezza + (info.larghezza - potenz.larghezza) / 2), 75);
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
							bottone.Disegna((int) (Personaggi.attaccante.x > Config.COLONNE / 2 ? potenz.larghezza / 2 - bottone.larghezza / 2 : Config.LARGHEZZA - bottone.larghezza + (bottone.larghezza - potenz.larghezza) / 2), 75 + (i * bottone.altezza));
							i++;
						}

					}
					info = null;
				}
				testoTurno.disegna("Turno " + Integer.toString(turnoTotale), Testo.CENTROORIZ, gc.getHeight() - testoTurno.ttf.getHeight("Turno"));
				
				
				
				for(int i = 0;i<Personaggi.giocatori.size();i++)
				{
					if(Personaggi.giocatori.get(i).squadra == turno)
					{
						Personaggi.giocatori.get(i).Disegna(0, gc.getHeight()-48*2f, gc);
					}
				}
				
				coin.draw(48*2f*Config.Scala, (gc.getHeight() - coin.getHeight() * Config.Scala), Config.Scala);
				
				testoTurno.disegna(Integer.toString(Personaggi.giocatori.get(turno - 1).soldi), (int) ((coin.getWidth()+48*2f) * Config.Scala), (int) (gc.getHeight() - 25 * Config.Scala));
				if (staComprando)
				{
					bottoneCliccaPerPosizionare.Disegna(Config.LARGHEZZA / 2 - bottoneCliccaPerPosizionare.larghezza / 2, gc.getHeight() - bottoneCliccaPerPosizionare.altezza);
				}

				if (inMenu)
				{
					menu.draw(0, 0, Config.Scala);
					menuChild.draw(gc.getWidth() / 2 - menuChild.getWidth() / 2, gc.getHeight() / 2 - menuChild.getHeight() / 2);
					for (int i = 0; i < opzioniMenu.length; i++)
					{
						opzioniMenu[i].Disegna(gc.getWidth() / 2 - opzioniMenu[i].larghezza / 2, (int) (gc.getHeight() / 2 - menuChild.getHeight() / 2 * Config.Scala + opzioniMenu[i].altezza * (i + 1) + 180 * Config.Scala * i));
					}

				}
			} else
			{
				menu.draw(0, 0, Config.Scala);
				testoVittoria.disegna("VITTORIA", Testo.CENTROORIZ, Testo.CENTROVERT);
			}
		}
	}

	@Override
	public int getID()
	{

		return Main.gioca;
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{
		Input input = gc.getInput();
		ArrayClick = GestoreMouse.ZonaClick();

		boolean cliccato = false;

		// carica le cose che deve caricare
		if (!caricato)
		{
			System.out.println("Sto caricando!");
			caricato = !caricato;

			caricaBottoni();

			turno = turnoTotale = 1;

			mappa = CaricaMappa.caricaQuadrati(Config.mappa);
		}

		if (input.isKeyPressed(Input.KEY_ESCAPE))
		{
			if (!controllo)
				controllo = !controllo;
			else
			{
				inMenu = !inMenu;
			}

		}
		// condizioni di vittoria
		if (!inMenu)
		{

			// non ha vinto
			if (vittoria == -1)
			{
				if (caricato)
				{

					if (!Config.conBasi)
					{
						for (int i = 0; i < Personaggi.giocatori.size(); i++)
						{
							if (nUnitaSquadra(Personaggi.giocatori.get(i).squadra) == 0)
							{

								Personaggi.giocatori.remove(i);
							}
							if (Personaggi.giocatori.size() == 1)
							{
								System.out.println("vincitore trovato!" + Personaggi.giocatori.get(0).squadra);
								vittoria = Personaggi.giocatori.get(0).squadra;
								testoVittoria = new Testo("Verdana", Font.BOLD, 56, (Color) Squadra.squadraAWT.get(vittoria));
							}
						}
					} else
					{
						for (int i = 0; i < Personaggi.giocatori.size(); i++)
						{
							if (!cercaBase(Personaggi.giocatori.get(i).squadra))
							{
								Personaggi.giocatori.remove(i);
							}
						}

						if (Personaggi.giocatori.size() == 1)
						{
							System.out.println("vincitore trovato!" + Personaggi.giocatori.get(0).squadra);
							vittoria = Personaggi.giocatori.get(0).squadra;
							testoVittoria = new Testo("Verdana", Font.BOLD, 56, (Color) Squadra.squadraAWT.get(vittoria));
						}
					}
				}
				// skip turno
				if (input.isKeyPressed(Input.KEY_SPACE))
				{
					if (!staComprando)
					{
						if (!controllo) // controllo serve per evitare che //
										// accumuli la coda dei tasti premuti,//
										// se uno preme spazio nella selezione//
										// della// mappa rischia che gli salta
										// il turno. // cosi' si sistema tutto
							controllo = !controllo;
						else
						{
							turno++;
							turnoTotale++;

							if (turno > Personaggi.giocatori.size())
								turno = 1;

							testoTurno = new Testo("Verdana", Font.BOLD, 20, (java.awt.Color) Squadra.squadraAWT.get(turno));
							
							smuoviPersonaggi();
							deselezionaPersonaggi();
							riscuotiBanca();
							pulisciGUI();
						}
					}
				}
				controllo = true;

				if (input.isKeyPressed(Input.KEY_2))
				{
					Personaggi.giocatori.remove(0);
					vittoria = Personaggi.giocatori.get(0).squadra;
					testoVittoria = new Testo("Verdana", Font.BOLD, 56, (Color) Squadra.squadraAWT.get(vittoria));
				}

				if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
				{
					if (staComprando) // se clicca quando sta comprando metto il
										// giocatore che ha comprato nella
										// casella
										// cliccata
					{
						click.play();
						staComprando = false;
						if (Personaggi.giocatori.get(turno - 1).soldi >= Integer.parseInt(infoAggiuntivePlayerScelto[1])) // se
																															// ha
																															// i
																															// soldi
						{
							Personaggi.personaggio.add(new PersonaggioGenerico(ArrayClick[1], ArrayClick[0], infoAggiuntivePlayerScelto[0], turno, Personaggi.personaggio.size()));

							Personaggi.giocatori.get(turno - 1).soldi -= Integer.parseInt(infoAggiuntivePlayerScelto[1]);
						}
						pulisciGUI();
						Personaggi.attaccante = null;
						pPrec = null;
						deselezionaPersonaggi();
					} else
					// il click e' un azione di selezione/deselezione o di click
					// su
					// un bottone
					{
						for (Bottone bottone : bottoniBase)
						{
							if (bottone.Cliccato()) // se ha cliccato un bottone
													// di
													// acquisto
							{
								click.play();
								infoAggiuntivePlayerScelto = bottone.infoAggiuntive;
								staComprando = true;
								cliccato = true;
							}
						}
						if (!cliccato)
						{

							if (potenz.Cliccato()) // potrebbe aver cliccato il
													// tasto di potenziamento
							{
								controllo = false;
								sbg.enterState(Main.potenziamento);
							}

							else
							// ha forse clicacto un personaggio
							{
								for (int i = 0; i < Personaggi.personaggio.size(); i++)
								{

									if (Personaggi.personaggio.get(i).inVita) // se
																				// e'
																				// in
																				// vita
										if (Personaggi.personaggio.get(i).squadra == turno) // se
																							// e'
																							// della
																							// sua
																							// squadra
											if (ArrayClick[0] == Personaggi.personaggio.get(i).x && ArrayClick[1] == Personaggi.personaggio.get(i).y) // se
																																						// la
																																						// x
																																						// e
																																						// la
																																						// y
																																						// corrispondono
											{

												if (Personaggi.personaggio.get(i).selezionato == false) // se
																										// non
																										// e'
																										// selezionato
												{
													click.play();
													Personaggi.personaggio.get(i).selezionato = true; // lo
																										// seleziono
													pulisciGUI();
													Personaggi.attaccante = Personaggi.personaggio.get(i); // lo
																											// metto
																											// come
																											// attaccante
													for (int j = 0; j < Personaggi.personaggio.size(); j++) // deseleziono
																											// gli
																											// altri
													{
														if (j != i)
														{
															Personaggi.personaggio.get(j).selezionato = false;
														}
													}
													break;
												} else
												// e' selezionato lo devo
												// deselezionare
												{
													click.play();
													Personaggi.personaggio.get(i).selezionato = false;
													Personaggi.attaccante = null;
													pPrec = null;
													pulisciGUI();
												}
											} else
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
					// se preme il tasto destro

					for (int i = 0; i < Personaggi.personaggio.size(); i++)
					{
						if (Personaggi.personaggio.get(i).inVita)
							if (Personaggi.personaggio.get(i).selezionato) // prendo
																			// l'unico
																			// selezionato
							{

								if (Math.abs(Personaggi.personaggio.get(i).x - ArrayClick[0]) + Math.abs(Personaggi.personaggio.get(i).y - ArrayClick[1]) <= Personaggi.personaggio.get(i).raggio && !Personaggi.personaggio.get(i).haMosso) // guardo
								// se
								// e'
								// in
								// range
								{
									Personaggi.personaggio.get(i).haMosso = true;
									Personaggi.attaccante = Personaggi.personaggio.get(i);
									Personaggi.difensore = Personaggi.clickSuPersonaggioNemico(Personaggi.personaggio.get(i), ArrayClick[1], ArrayClick[0]);
									if (Personaggi.difensore != null)
									{

										Battaglia.caricato = false;
										Battaglia.risolto = false;
										Battaglia.timer = 0;
										Personaggi.personaggio.get(i).selezionato = false;
										pPrec = null;
										pulisciGUI();
										battaglia.play();
										sbg.enterState(Main.battaglia);

										break;

									} else if (Materiale.Controllo(ArrayClick)) // else
																				// e'
																				// uno
																				// spostamento,
																				// allra
																				// controllo
																				// che
																				// puo'
																				// andare
																				// su
																				// quella
																				// casella
									{
										boolean problema = false;
										for (int j = 0; j < Personaggi.personaggio.size(); j++) // controllo
																								// che
																								// la
																								// casella
																								// non
																								// e'
																								// occupata
										{
											if (Personaggi.personaggio.get(j).x == ArrayClick[0] && Personaggi.personaggio.get(j).y == ArrayClick[1])
												problema = true;
										}
										if (!problema) // se non e' occupata
										{
											movimento.play();
											Personaggi.personaggio.get(i).Sposta(ArrayClick[1], ArrayClick[0]); // lo
																												// sposto
											pulisciGUI();
											Personaggi.attaccante = null;
											pPrec = null;
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
							} else
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
				} else
				{
					pulisciGUI();
				}
			} else
			// c'è uno stato di vittoria
			{
				System.out.println("ha vinto qualcuno!");
				if (input.isKeyPressed(Input.KEY_ESCAPE) || input.isKeyPressed(Input.KEY_SPACE) || input.isKeyPressed(Input.KEY_ENTER))
				{
					Personaggi.giocatori.clear();
					Personaggi.personaggio.clear();
					CreaPersonaggio.nAdd = 0;
					vittoria = -1;
					turno = 1;
					turnoTotale = 1;
					caricato = false;
					inMenu = false;
					Personaggi.attaccante = null;
					Personaggi.difensore = null;
					pulisciGUI();
					sbg.enterState(Main.menu, new FadeOutTransition(), new FadeInTransition());
				}

			}
		} else
		// e' nel menu
		{
			if (input.isKeyPressed(Input.KEY_SPACE))
				;
			if (input.isKeyPressed(Input.KEY_ENTER))
				;

			if (opzioniMenu[0].Cliccato())
			{
				inMenu = !inMenu;
			} else if (opzioniMenu[1].Cliccato())
			{
				inMenu = !inMenu;
				sbg.enterState(Main.menu);
			} else if (opzioniMenu[2].Cliccato())
			{
				gc.exit();
			}
		}
	}

	private void smuoviPersonaggi()
	{
		for(int i = 0;i<Personaggi.personaggio.size();i++)
			Personaggi.personaggio.get(i).haMosso = false;
	}

	private void riscuotiBanca()
	{
		for (int i = 0; i < Personaggi.personaggio.size(); i++)
		{
			if (Personaggi.giocatori.get(turno - 1).squadra == Personaggi.personaggio.get(i).squadra)
			{
				if (Personaggi.personaggio.get(i).Classe.equals("Banca") && Personaggi.personaggio.get(i).inVita)
				{
					Personaggi.giocatori.get(turno - 1).soldi += 20;
				}
			}
		}

	}

	private void pulisciGUI()
	{
		info = null;
		potenz.Elimina();
		base = null;
		for (Bottone bottone : bottoniBase)
		{
			bottone.Elimina();
		}

	}

	private void deselezionaPersonaggi()
	{

		for (int i = 0; i < Personaggi.personaggio.size(); i++)
		{
			Personaggi.personaggio.get(i).selezionato = false;
		}

		Personaggi.attaccante = null;

	}

	private void caricaBottoni() throws SlickException
	{
		bottoneCliccaPerPosizionare = new Bottone("Clicca sulla casella dove \nvuoi posizionare l'unità appena comprata!");
		potenz = new Bottone("Potenziamenti");
		potenz.Elimina();

		bottoniBase.clear();
		bottoniBase.add(new Bottone("Soldato (50)", "Soldato", "50"));
		bottoniBase.get(0).Elimina();
		bottoniBase.add(new Bottone("Carro (100)", "Carro", "100"));
		bottoniBase.get(1).Elimina();
		bottoniBase.add(new Bottone("Aereo (150)", "Aereo", "150"));
		bottoniBase.get(2).Elimina();
		bottoniBase.add(new Bottone("Autoblindo (75)", "Autoblindo", "75"));
		bottoniBase.get(3).Elimina();
		bottoniBase.add(new Bottone("Banca (100)", "Banca", "100"));
		bottoniBase.get(4).Elimina();
		testoTurno = new Testo("Verdana", Font.BOLD, 20, (java.awt.Color) Squadra.squadraAWT.get(1));

	}

	public int nUnitaSquadra(int squadra)
	{
		int c = 0;
		for (int i = 0; i < Personaggi.personaggio.size(); i++)
		{
			if (Personaggi.personaggio.get(i).squadra == squadra && Personaggi.personaggio.get(i).inVita)
				c++;
		}
		return c;
	}

	public boolean cercaBase(int squadra)
	{

		for (int i = 0; i < Personaggi.personaggio.size(); i++)
		{
			if (Personaggi.personaggio.get(i).squadra == squadra && Personaggi.personaggio.get(i).Classe.equals("Base") && Personaggi.personaggio.get(i).inVita)
				return true;
		}
		return false;
	}
}