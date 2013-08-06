package mainGioco;

import java.util.Random;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entities.Personaggi;
import entities.PersonaggioGenerico;

public class Battaglia extends BasicGameState {
	
	private int					nAttaccanti, nDifensori;
	public static boolean		caricato, risolto;
	private int[]				posXAtt, posYAtt, posXDif, posYDif;
	private Image				sfondoAtt, sfondoDif;
	public static int			timer;
	private PersonaggioGenerico	attaccante, difensore;
	private int					idAtt, idDif;
	public static final String	Path	= "res/Battaglia/";
	
	public Battaglia(int state) {
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		
		sfondoAtt.draw(0,0,Config.Scala);
		sfondoDif.draw(Config.LARGHEZZA/2, 0, Config.Scala);
		
		for (int i = 0; i < Math.round(attaccante.vita / 10); i++)
		{
			attaccante.DisegnaXY(posXAtt[i], posYAtt[i]);
		}
		
		for (int i = 0; i < Math.round(difensore.vita / 10); i++)
		{
			difensore.DisegnaXY(posXDif[i], posYDif[i]);
		}
		
		g.drawString("Attaccante : " + Integer.toString(attaccante.vita), 50, 50);
		g.drawString("Difensore : " + Integer.toString(difensore.vita), 50, 100);
	}
	
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

			sfondoAtt = new Image(Path+Gioca.mappa[yAtt][xAtt].tipo+".png");
			sfondoDif = new Image(Path+Gioca.mappa[yDif][xDif].tipo+".png");
			
			System.out.println(idAtt);
			System.out.println(idDif);
			
			nAttaccanti = Math.round(attaccante.vita / 10);
			nDifensori = Math.round(difensore.vita / 10);
			
			posXAtt = new int[nAttaccanti];
			posYAtt = new int[nAttaccanti];
			posXDif = new int[nDifensori];
			posYDif = new int[nDifensori];
			
			for (int i = 0; i < nAttaccanti; i++)
			{
				posXAtt[i] = rnd.nextInt(Config.LARGHEZZA / 2);
				posYAtt[i] = rnd.nextInt(Config.ALTEZZA);
			}
			
			for (int i = 0; i < nDifensori; i++)
			{
				posXDif[i] = Config.LARGHEZZA / 2 + rnd.nextInt(Config.LARGHEZZA / 2);
				posYDif[i] = rnd.nextInt(Config.ALTEZZA);
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
			sbg.enterState(Main.gioca);
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
