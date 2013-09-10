package mainGioco;

import java.awt.Font;
import java.util.ArrayList;

import entities.Giocatore;
import entities.Personaggi;
import entities.Tipo;
import gui.Bottone;
import gui.Testo;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Tecnologies extends BasicGameState
{

	int margineX = 10;

	ArrayList<Bottone> bottoniTecnologie = new ArrayList<Bottone>();
	Testo testoTecnologie;
	Image sfondo;
	Image spunta;



	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		testoTecnologie = new Testo("Verdana", Font.PLAIN, 32, java.awt.Color.black);
		sfondo = new Image("res/GUI/tecnologies.png");
		spunta = new Image("res/GUI/spunta.png");
	}

	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException
	{
		bottoniTecnologie.clear();

		if (Personaggi.attaccante.Classe.equals("Soldato"))
		{
			bottoniTecnologie.add(new Bottone("Ricarica Vita (20$)"));
			bottoniTecnologie.add(new Bottone("Potenzia Attacco Base (30$)"));
			bottoniTecnologie.add(new Bottone("Potenziamento contro veicolo corazzati (50$)"));
			bottoniTecnologie.add(new Bottone("Raggio movimento aumentato di 1 (80$)"));
		}
		if (Personaggi.attaccante.Classe.equals("Carro"))
		{
			bottoniTecnologie.add(new Bottone("Ricarica Vita (35$)"));
			bottoniTecnologie.add(new Bottone("Potenzia Attacco Base (40$)"));
			bottoniTecnologie.add(new Bottone("Potenviamento contro veicolo aerei (Antiaerea) (80$)"));
			bottoniTecnologie.add(new Bottone("Raggio movimento aumentato di 1 (120$)"));
		}
		if (Personaggi.attaccante.Classe.equals("Autoblindo"))
		{
			bottoniTecnologie.add(new Bottone("Ricarica Vita (25$)"));
			bottoniTecnologie.add(new Bottone("Potenzia Attacco Base(35$)"));
			bottoniTecnologie.add(new Bottone("Potenviamento contro fanteria (45$)"));
		}
		if (Personaggi.attaccante.Classe.equals("Aereo"))
		{
			bottoniTecnologie.add(new Bottone("Ricarica Vita (40$)"));
			bottoniTecnologie.add(new Bottone("Potenzia Attacco Base (80$)"));
			bottoniTecnologie.add(new Bottone("Potenviamento contro fanteria (80$)"));
			bottoniTecnologie.add(new Bottone("Potenviamento contro veicoli antiaerei (100$)"));
		}
		if (Personaggi.attaccante.Classe.equals("Banca"))
		{
			bottoniTecnologie.add(new Bottone("Piu soldi generati ogni turno (120$)"));
		}

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{
		g.setFont(new TrueTypeFont(new Font("Verdana", Font.BOLD, 40), true));
		sfondo.draw(0, 0, Config.Scala);

		for (int i = 0; i < bottoniTecnologie.size(); i++)
		{
			bottoniTecnologie.get(i).Disegna(300 * Config.Scala, 150 * i * Config.Scala + 50 * Config.Scala);
		}

		for (int i = 0; i < Personaggi.giocatori.size(); i++)
			if (Personaggi.giocatori.get(i).squadra == Personaggi.attaccante.squadra)
			{
				Personaggi.giocatori.get(i).Disegna(0, gc.getHeight() - 48 * 2f * Config.Scala, gc);
			}
		Gioca.coin.draw(48 * 2f * Config.Scala, (gc.getHeight() - Gioca.coin.getHeight() * Config.Scala), Config.Scala);

		Gioca.testoTurno.disegna(Integer.toString(Personaggi.giocatori.get(Gioca.turno - 1).soldi), (int) ((Gioca.coin.getWidth() + 48 * 2f) * Config.Scala), (int) (gc.getHeight() - 25 * Config.Scala));
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{

		Input input = gc.getInput();
		if (input.isKeyPressed(input.KEY_ESCAPE))
			sbg.enterState(Main.gioca);
		if (input.isKeyPressed(input.KEY_ENTER))
			;
		if (input.isKeyPressed(input.KEY_SPACE))
			;

		if (Personaggi.attaccante.Classe.equals("Soldato"))
		{
			if (bottoniTecnologie.get(0).Cliccato())
			{
				for (int i = 0; i < Personaggi.giocatori.size(); i++)
					if (Personaggi.giocatori.get(i).squadra == Personaggi.attaccante.squadra)
						if (Personaggi.giocatori.get(i).soldi >= 20)
						{
							if (!Personaggi.attaccante.vitaCaricata)
							{
								Personaggi.attaccante.vita = 100;
								Personaggi.giocatori.get(i).soldi -= 20;
								Personaggi.attaccante.vitaCaricata = true;
							}

						}

			}
			if (bottoniTecnologie.get(1).Cliccato())
			{
				for (int i = 0; i < Personaggi.giocatori.size(); i++)
					if (Personaggi.giocatori.get(i).squadra == Personaggi.attaccante.squadra)
						if (Personaggi.giocatori.get(i).soldi >= 30)
						{
							if (!Personaggi.attaccante.piuAttacco)
							{
								Personaggi.attaccante.piuAttacco = true;
								Personaggi.giocatori.get(i).soldi -= 30;
							}
						}

			}
			if (bottoniTecnologie.get(2).Cliccato())
			{
				for (int i = 0; i < Personaggi.giocatori.size(); i++)
					if (Personaggi.giocatori.get(i).squadra == Personaggi.attaccante.squadra)
						if (Personaggi.giocatori.get(i).soldi >= 50)
						{
							if (!Personaggi.attaccante.vsCorazzati)
							{
								Personaggi.attaccante.vsCorazzati = true;
								Personaggi.giocatori.get(i).soldi -= 50;
							}
						}
			}

			if (bottoniTecnologie.get(3).Cliccato())
			{
				for (int i = 0; i < Personaggi.giocatori.size(); i++)
					if (Personaggi.giocatori.get(i).squadra == Personaggi.attaccante.squadra)
						if (Personaggi.giocatori.get(i).soldi >= 80)
						{
							if (!Personaggi.attaccante.piuRaggio)
							{
								Personaggi.attaccante.piuRaggio = true;
								Personaggi.attaccante.raggio += 1;
								Personaggi.giocatori.get(i).soldi -= 80;
							}
						}
			}
		}

		if (Personaggi.attaccante.Classe.equals("Carro"))
		{
			if (bottoniTecnologie.get(0).Cliccato())
			{
				for (int i = 0; i < Personaggi.giocatori.size(); i++)
					if (Personaggi.giocatori.get(i).squadra == Personaggi.attaccante.squadra)
						if (Personaggi.giocatori.get(i).soldi >= 35)
						{
							if (!Personaggi.attaccante.vitaCaricata)
							{
								Personaggi.attaccante.vitaCaricata = true;
								Personaggi.attaccante.vita = 100;
								Personaggi.giocatori.get(i).soldi -= 35;
							}
						}
			}
			if (bottoniTecnologie.get(1).Cliccato())
			{
				for (int i = 0; i < Personaggi.giocatori.size(); i++)
					if (Personaggi.giocatori.get(i).squadra == Personaggi.attaccante.squadra)
						if (!Personaggi.attaccante.piuAttacco)
						{
							Personaggi.attaccante.piuAttacco = true;
							Personaggi.giocatori.get(i).soldi -= 35;
						}

			}
			if (bottoniTecnologie.get(2).Cliccato())
			{
				for (int i = 0; i < Personaggi.giocatori.size(); i++)
					if (Personaggi.giocatori.get(i).squadra == Personaggi.attaccante.squadra)
						if (Personaggi.giocatori.get(i).soldi >= 80)
						{
							if (!Personaggi.attaccante.vsAerei)
							{
								Personaggi.attaccante.vsAerei = true;
								Personaggi.giocatori.get(i).soldi -= 80;
							}
						}
			}

			if (bottoniTecnologie.get(3).Cliccato())
			{
				for (int i = 0; i < Personaggi.giocatori.size(); i++)
					if (Personaggi.giocatori.get(i).squadra == Personaggi.attaccante.squadra)
						if (Personaggi.giocatori.get(i).soldi >= 120)
						{
							if (!Personaggi.attaccante.piuRaggio)
							{
								Personaggi.attaccante.piuRaggio = true;
								Personaggi.attaccante.raggio += 1;
								Personaggi.giocatori.get(i).soldi -= 120;
							}

						}
			}
		}

		if (Personaggi.attaccante.Classe.equals("Autoblindo"))
		{
			if (bottoniTecnologie.get(0).Cliccato())
			{
				for (int i = 0; i < Personaggi.giocatori.size(); i++)
					if (Personaggi.giocatori.get(i).squadra == Personaggi.attaccante.squadra)
						if (Personaggi.giocatori.get(i).soldi >= 25)
						{
							if (!Personaggi.attaccante.vitaCaricata)
							{
								Personaggi.attaccante.vitaCaricata = true;
								Personaggi.attaccante.vita = 100;
								Personaggi.giocatori.get(i).soldi -= 25;
							}

						}

			}
			if (bottoniTecnologie.get(1).Cliccato())
			{
				for (int i = 0; i < Personaggi.giocatori.size(); i++)
					if (Personaggi.giocatori.get(i).squadra == Personaggi.attaccante.squadra)
						if (Personaggi.giocatori.get(i).soldi >= 35)
						{
							if (!Personaggi.attaccante.piuAttacco)
							{
								Personaggi.attaccante.piuAttacco = true;
								Personaggi.giocatori.get(i).soldi -= 35;
							}

						}

			}
			if (bottoniTecnologie.get(2).Cliccato())
			{
				for (int i = 0; i < Personaggi.giocatori.size(); i++)
					if (Personaggi.giocatori.get(i).squadra == Personaggi.attaccante.squadra)
						if (Personaggi.giocatori.get(i).soldi >= 45)
						{
							if (!Personaggi.attaccante.vsFanteria)
							{
								Personaggi.attaccante.vsFanteria = true;
								Personaggi.giocatori.get(i).soldi -= 45;
							}
						}
			}

		}

		if (Personaggi.attaccante.Classe.equals("Aereo"))
		{
			if (bottoniTecnologie.get(0).Cliccato())
			{
				for (int i = 0; i < Personaggi.giocatori.size(); i++)
					if (Personaggi.giocatori.get(i).squadra == Personaggi.attaccante.squadra)
						if (Personaggi.giocatori.get(i).soldi >= 45)
						{
							if (!Personaggi.attaccante.vitaCaricata)
							{
								Personaggi.attaccante.vitaCaricata = true;
								Personaggi.attaccante.vita = 100;
								Personaggi.giocatori.get(i).soldi -= 45;
							}
						}

			}
			if (bottoniTecnologie.get(1).Cliccato())
			{
				for (int i = 0; i < Personaggi.giocatori.size(); i++)
					if (Personaggi.giocatori.get(i).squadra == Personaggi.attaccante.squadra)
						if (Personaggi.giocatori.get(i).soldi >= 80)
						{
							if (!Personaggi.attaccante.piuAttacco)
							{
								Personaggi.attaccante.piuAttacco = true;
								Personaggi.giocatori.get(i).soldi -= 80;
							}
							
						}

			}
			if (bottoniTecnologie.get(2).Cliccato())
			{
				for (int i = 0; i < Personaggi.giocatori.size(); i++)
					if (Personaggi.giocatori.get(i).squadra == Personaggi.attaccante.squadra)
						if (Personaggi.giocatori.get(i).soldi >= 80)
						{
							if (!Personaggi.attaccante.vsFanteria)
							{
								Personaggi.attaccante.vsFanteria = true;
								Personaggi.giocatori.get(i).soldi -= 80;
							}	
						}
			}

			if (bottoniTecnologie.get(3).Cliccato())
			{
				for (int i = 0; i < Personaggi.giocatori.size(); i++)
					if (Personaggi.giocatori.get(i).squadra == Personaggi.attaccante.squadra)
						if (Personaggi.giocatori.get(i).soldi >= 100)
						{
							if (!Personaggi.attaccante.vsAA)
							{
								Personaggi.attaccante.vsAA = true;
								Personaggi.giocatori.get(i).soldi -= 80;
							}
						}
			}
		}

		if (Personaggi.attaccante.Classe.equals("Banca"))
		{
			for (int i = 0; i < Personaggi.giocatori.size(); i++)
				if (Personaggi.giocatori.get(i).squadra == Personaggi.attaccante.squadra)
					if (Personaggi.giocatori.get(i).soldi >= 120)
					{
						Personaggi.attaccante.piuSoldi = true;
						Personaggi.giocatori.get(i).soldi -= 120;
					}

		}
	}

	public int getID()
	{
		// TODO Auto-generated method stub
		return Main.potenziamento;
	}
}
