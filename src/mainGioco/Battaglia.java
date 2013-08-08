package mainGioco;

import java.util.Random;

import org.newdawn.slick.Animation;
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
import entities.Tipo;

public class Battaglia extends BasicGameState {
	
	private int	nAttaccanti, nDifensori;
	public static boolean	caricato, risolto;
	private int[]			posXAtt, posYAtt, posXDif, posYDif;
	public static Image		sfondoAtt, sfondoDif;
	public static int		timer;
	private PersonaggioGenerico	attaccante, difensore;
	private int					idAtt, idDif;
	private Animation			attGenerico, attSpecifico, difGenerico, difSpecifico;
	public static final String	Path	= "res/Battaglia/";
	
	public Battaglia(int state) {
		
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		if (sfondoAtt != null & sfondoDif != null)
		{
			sfondoAtt.draw(0, 0, Config.Scala);
			sfondoDif.draw(Config.LARGHEZZA / 2, 0, Config.Scala);
			
			for (int i = 0; i < Math.round(attaccante.vita / 10); i++)
			{
				//attSpecifico.draw(posXAtt[i] * Config.Scala, posYAtt[i] * Config.Scala, attaccante.colore);
				attGenerico.draw(posXAtt[i], posYAtt[i]);
			}
			for (int i = 0; i < Math.round(difensore.vita / 10); i++)
			{
				//difSpecifico.draw(posXDif[i] * Config.Scala, posYDif[i] * Config.Scala, difensore.colore);
				difGenerico.draw(posXDif[i], posYDif[i]);
				
			}
			
			g.drawString("Attaccante : " + Integer.toString(attaccante.vita), 50, 50);
			g.drawString("Difensore : " + Integer.toString(difensore.vita), 50, 100);
		}
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
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
			
			attGenerico = new Animation();
			attSpecifico = new Animation();
			difGenerico = new Animation();
			difSpecifico = new Animation();
			SpriteSheet sheetAttaccanteGenerico = new SpriteSheet("res/sprite/" +attaccante.squadra +""+  Tipo.tipo.get(attaccante.Classe) + ".png", 32,
					32);
			//SpriteSheet sheetAttaccanteSpecifico = new SpriteSheet("res/Battaglia/" + attaccante.Classe + "SparoSpecifico.png",
					//80, 57);
			SpriteSheet sheetDifensoreGenerico = new SpriteSheet("res/sprite/" +difensore.squadra +""+  Tipo.tipo.get(difensore.Classe) + ".png", 32,
					32);
			//SpriteSheet sheetDifensoreSpecifico = new SpriteSheet("res/Battaglia/" + difensore.Classe + "SparoSpecifico.png", 80,
					//57);
			for (int i = 0; i < 2; i++)
			{
				attGenerico.addFrame(sheetAttaccanteGenerico.getSprite(i, 1).getFlippedCopy(true, false), 150);
				//attSpecifico.addFrame(sheetAttaccanteSpecifico.getSprite(i, 0).getFlippedCopy(true, false), 150);
				difGenerico.addFrame(sheetDifensoreGenerico.getSprite(i, 1), 150);
				//difSpecifico.addFrame(sheetDifensoreSpecifico.getSprite(i, 0), 150);
			}
			
			for (int i = 0; i < 2; i++)
			{
				
			}
			sfondoAtt = new Image(Path + Gioca.mappa[yAtt][xAtt].tipo + ".png");
			sfondoDif = new Image(Path + Gioca.mappa[yDif][xDif].tipo + ".png");
			
			nAttaccanti = Math.round(attaccante.vita / 10);
			nDifensori = Math.round(difensore.vita / 10);
			if (nAttaccanti < 1)
				nAttaccanti = 1;
			if (nDifensori < 1)
				nDifensori = 1;
			posXAtt = new int[nAttaccanti];
			posYAtt = new int[nAttaccanti];
			posXDif = new int[nDifensori];
			posYDif = new int[nDifensori];
			
			for (int i = 0; i < nAttaccanti; i++)
			{
				posXAtt[i] = rnd.nextInt((int) (Config.LARGHEZZA / 2 - 32 * Config.Scala));
				posYAtt[i] = (int) (Config.ALTEZZA/2 +  rnd.nextInt((int) (Config.ALTEZZA/2 - 32 * Config.Scala)));
				System.out.println(posYAtt[i]);
			}
			
			for (int i = 0; i < nDifensori; i++)
			{
				posXDif[i] = Config.LARGHEZZA / 2 + rnd.nextInt((int) (Config.LARGHEZZA / 2 - 32 * Config.Scala));
				posYDif[i] = (int) (Config.ALTEZZA/2 +  rnd.nextInt((int) (Config.ALTEZZA/2 - 32 * Config.Scala)));
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
			sbg.enterState(Main.gioca, new FadeOutTransition(), new FadeInTransition());
		}
		
	}
	
	private void risolviBattaglia(PersonaggioGenerico attaccante, PersonaggioGenerico difensore) {
		// da bilanciare!!
		
		if (attaccante.potenzaAttacco * attaccante.vita > difensore.potenzaDifesa * difensore.vita)
		{
			int danno = attaccante.potenzaAttacco * attaccante.vita / 100 - difensore.potenzaDifesa;
			if (danno > 0)
				difensore.vita -= danno;
			// else
			// attaccante.vita -= danno;
		}
		else if (attaccante.potenzaAttacco * attaccante.vita < difensore.potenzaDifesa * difensore.vita)
		{
			int danno = difensore.potenzaDifesa * difensore.vita / 100 - attaccante.potenzaAttacco;
			if (danno > 0)
				attaccante.vita -= danno;
			// else
			// difensore.vita -= danno;
		}
		else
		// attacco == difesa && vita==vita
		{
			Random rnd = new Random();
			
			int danno = rnd.nextInt(attaccante.potenzaAttacco);
			attaccante.vita -= danno;
			difensore.vita -= danno;
			
		}
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return Main.battaglia;
	}
}
