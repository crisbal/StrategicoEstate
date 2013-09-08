package mainGioco;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import entities.Personaggi;
import entities.PersonaggioGenerico;
import entities.Squadra;
import entities.Tipo;

public class Battaglia extends BasicGameState
{

	private int nAttaccanti, nDifensori;
	public static boolean caricato, risolto;
	private int[] posXAtt, posYAtt, posXDif, posYDif;
	public static Image sfondoAtt, sfondoDif, baseGen, baseSpec;
	public static int timer;
	private PersonaggioGenerico attaccante, difensore;
	private int idAtt, idDif;
	private Animation attGenerico, difGenerico;
	public static final String Path = "res/Battaglia/";

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
	{

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{

		if (sfondoAtt != null & sfondoDif != null)
		{
			sfondoAtt.draw(0, 0, Config.Scala);
			sfondoDif.draw(Config.LARGHEZZA / 2, 0, Config.Scala);

			if (!attaccante.Classe.equals("Base") && !attaccante.Classe.equals("Banca"))
				for (int i = 0; i < Math.round(attaccante.vita / 10); i++)
				{
					// attSpecifico.draw(posXAtt[i] * Config.Scala, posYAtt[i] *
					// Config.Scala, attaccante.colore);
					attGenerico.draw(posXAtt[i], posYAtt[i]);
				}
			else
			{
				attaccante.DisegnaXYScala(gc.getWidth() / 4 - (attaccante.getWidth() * 5f) / 2, 200 * Config.Scala, 5f);
			}
			if (!difensore.Classe.equals("Base") && !difensore.Classe.equals("Banca"))
				for (int i = 0; i < Math.round(difensore.vita / 10); i++)
				{
					// difSpecifico.draw(posXDif[i] * Config.Scala, posYDif[i] *
					// Config.Scala, difensore.colore);
					difGenerico.draw(posXDif[i], posYDif[i]);

				}
			else
			{
				difensore.DisegnaXYScala(gc.getWidth() / 2 + gc.getWidth() / 4 - (difensore.getWidth() * 5f) / 2, 200 * Config.Scala, 5f);
			}

			g.drawString("Attaccante : " + Integer.toString(attaccante.vita), 50, 50);
			g.drawString("Difensore : " + Integer.toString(difensore.vita), 50, 100);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{

		if (!caricato)
		{
			timer = 0;
			caricato = true;
			Random rnd = new Random();
			attaccante = Personaggi.attaccante;
			difensore = Personaggi.difensore;

			idAtt = attaccante.Identificativo;
			idDif = difensore.Identificativo;

			int xAtt, xDif, yAtt, yDif;

			xAtt = attaccante.x;
			yAtt = attaccante.y;
			xDif = difensore.x;
			yDif = difensore.y;

			if (!attaccante.Classe.equals("Base") && !attaccante.Classe.equals("Banca"))
			{
				attGenerico = new Animation();
				SpriteSheet sheetAttaccanteGenerico = new SpriteSheet(Path + attaccante.squadra + "" + Tipo.tipo.get(attaccante.Classe) + "A.png", 64, 64);
				for (int i = 0; i < sheetAttaccanteGenerico.getWidth() / 64; i++)
				{
					attGenerico.addFrame(sheetAttaccanteGenerico.getSprite(i, 0).getFlippedCopy(true, false), 150);
				}
			}
			System.out.println(difensore.Classe);
			if (!difensore.Classe.equals("Base") && !difensore.Classe.equals("Banca"))
			{

				difGenerico = new Animation();
				SpriteSheet sheetDifensoreGenerico = new SpriteSheet(Path + difensore.squadra + "" + Tipo.tipo.get(difensore.Classe) + "A.png", 64, 64);

				for (int i = 0; i < sheetDifensoreGenerico.getWidth() / 64; i++)
				{
					difGenerico.addFrame(sheetDifensoreGenerico.getSprite(i, 0), 150);
				}

			}

			sfondoAtt = new Image(Path + Gioca.mappa[yAtt][xAtt].tipo + ".png");
			sfondoDif = new Image(Path + Gioca.mappa[yDif][xDif].tipo + ".png");

			nAttaccanti = Math.round(attaccante.vita / 10);
			nDifensori = Math.round(difensore.vita / 10);
			if (nAttaccanti < 1)
				nAttaccanti = 1;
			if (nDifensori < 1)
				nDifensori = 1;
			posXAtt = new int[10];
			posYAtt = new int[10];
			posXDif = new int[10];
			posYDif = new int[10];
			int k = 0, j = 0;
			Random gen = new Random();
			for (int i = 0; i < 10; i++)
			{
				posXAtt[i] = gen.nextInt((int) (gc.getWidth()/2-attGenerico.getWidth()* Config.Scala));
				posYAtt[i] = (int) (350*Config.Scala + gen.nextInt((int) (gc.getHeight()-350*Config.Scala-attGenerico.getHeight()*Config.Scala)));

				posXDif[i] = gc.getWidth()/2 + gen.nextInt((int) (gc.getWidth()/2-difGenerico.getWidth()* Config.Scala));
				posYDif[i] = (int) (350*Config.Scala + gen.nextInt((int) (gc.getHeight()-350*Config.Scala-difGenerico.getHeight()*Config.Scala)));
			}
		}
		timer += delta;

		if (timer > 2000 && !risolto)
		{
			risolto = true;
			risolviBattaglia(attaccante, difensore);
			Personaggi.personaggio.get(idAtt).vita = attaccante.vita;
			Personaggi.personaggio.get(idDif).vita = difensore.vita;
			if (attaccante.vita <= 0)
			{
				Personaggi.personaggio.get(idAtt).inVita = false;
			}
			if (difensore.vita <= 0)
			{
				Personaggi.personaggio.get(idDif).inVita = false;
			}
		}

		if (timer > 5000)
		{
			Personaggi.attaccante = null;
			Personaggi.difensore = null;
			sbg.enterState(Main.gioca, new FadeOutTransition(), new FadeInTransition());
		}

	}

	private void risolviBattaglia(PersonaggioGenerico attaccante, PersonaggioGenerico difensore)
	{
		// da bilanciare!!
		int potenzaAttacco = 0, potenzaDifesa = 0;

		if (attaccante.Classe.matches("Soldato"))
		{
			potenzaAttacco += 5;
			if (attaccante.piuAttacco)
				potenzaAttacco += 5;
		} else if (attaccante.Classe.matches("Carro"))
		{
			potenzaAttacco += 5;
			if (attaccante.piuAttacco)
				potenzaAttacco += 5;
		}

		if (difensore.Classe.matches("Soldato"))
		{
			potenzaDifesa += 5;
			if (difensore.piuAttacco)
				potenzaDifesa += 5;
		} else if (difensore.Classe.matches("Carro"))
		{
			potenzaDifesa += 5;
			if (difensore.piuAttacco)
				potenzaDifesa += 5;
		}

		/* calcolo dell'attacco, della difesa, del danno, ecc. per tipo unita */
		if (attaccante.veicolo)
			if (attaccante.vsFanteria && !difensore.veicolo)
				potenzaAttacco += 5;

		/* calcolo i vantaggi e gli svantaggi tra unita vs unita */
		if (attaccante.veicolo && !difensore.veicolo)
		{
			potenzaAttacco += 5;
			potenzaDifesa -= 5;
		} else if (!attaccante.veicolo && difensore.veicolo)
		{
			potenzaDifesa += 5;
			potenzaAttacco -= 5;
		}

		potenzaAttacco = Math.round(potenzaAttacco * (attaccante.vita / 100));
		potenzaDifesa = Math.round(potenzaDifesa * (difensore.vita / 100));

		attaccante.vita -= potenzaDifesa;
		difensore.vita -= potenzaAttacco;

	}

	@Override
	public int getID()
	{
		return Main.battaglia;
	}
}
