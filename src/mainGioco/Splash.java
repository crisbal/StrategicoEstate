package mainGioco;

import gui.Punto;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Splash extends BasicGameState {

	/* l'immagine del titolo */
	Image title;
	int xTitle, yTitle;
	/* l'immagine che copre il titolo, scorrendo */
	Image copriTitolo;
	/* le 3 immagini da stampare */
	Image imageRed;
	Image imageOrange;
	Image imageYellow;
	/* il numero di immagini per ogni grandezza (in tutto 108 siccome ci sono 3 dimensioni) */
	int fiamme = 36;
	/* le sequenze delle fiamme */
	Punto[] punto1 = new Punto[fiamme];
	Punto[] punto2 = new Punto[fiamme];
	Punto[] punto3 = new Punto[fiamme];
	/* l'ampiezza dell'onda dell'immagine */
	int ampiezza;
	/* punto inizio delle fiamme */
	int xFiamma, yFiamma;
	/* velocita' dell'ampiezza */
	float incrementoAngolo;
	/* velocita' con cui si spostano le fiamme */
	float incrementoX;
	
	public Splash(){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		title = new Image("res/logo.png");
		/* DA RIMPIAZZARE CON L'ALTEZZA E LA LARGHEZZA DELLO SCHERMO FINALE */
		xTitle = (Config.LARGHEZZA/2)-(title.getWidth()/2);
		yTitle = (Config.ALTEZZA/2)-(title.getHeight()/2);
		copriTitolo = new Image("res/Splash/copriTitolo.png");
		
		/*fiamme = 36; lo inizializzo nella dichirazione, altrimenti da' errore per la creazione
		 * dell'array fiamme (Violazione di memoria) */
		
		/* ampiezza dell'onda */
		ampiezza = 5;
		/* velocita' con cui viene eseguita l'onda */
		incrementoAngolo = 0.5f;
		/* velocita' con cui si spostano le fiamme */
		incrementoX = 0.2f;
		
		/* creo i punti piu' piccoli */
		imageRed = new Image("res/splash/red3.png");
		imageOrange = new Image("res/splash/orange3.png");
		imageYellow = new Image("res/splash/yellow3.png");
		/* DA RIMPIAZZARE CON L'ALTEZZA E LA LARGHEZZA DELLO SCHERMO FINALE */
		for(int i = 0, cont = 0, yFiamma = (Config.ALTEZZA/2)-(title.getHeight()/2); i < 6; i++, yFiamma += 17)
			for(int j = 0, xFiamma = (Config.LARGHEZZA/2)-(title.getWidth()/2);j < 6; j++, xFiamma += 5, cont++)
				punto1[cont] = new Punto(xFiamma, yFiamma, ampiezza, incrementoAngolo, cont%3, imageRed, imageOrange, imageYellow);
		
		/* creo i punti medi */
		/* DA RIMPIAZZARE CON L'ALTEZZA E LA LARGHEZZA DELLO SCHERMO FINALE */
		imageRed = new Image("res/splash/red2.png");
		imageOrange = new Image("res/splash/orange2.png");
		imageYellow = new Image("res/splash/yellow2.png");
		for(int i = 0, cont = 0, yFiamma = (Config.ALTEZZA/2)-(title.getHeight()/2); i < 6; i++, yFiamma += 17)
			for(int j = 0, xFiamma = (Config.LARGHEZZA/2)-(title.getWidth()/2) - 20;j < 6; j++, xFiamma += 5, cont++)
				punto2[cont] = new Punto(xFiamma, yFiamma, ampiezza, incrementoAngolo, cont%3, imageRed, imageOrange, imageYellow);
		
		/* creo i punti grandi */
		/* DA RIMPIAZZARE CON L'ALTEZZA E LA LARGHEZZA DELLO SCHERMO FINALE */
		imageRed = new Image("res/splash/red1.png");
		imageOrange = new Image("res/splash/orange1.png");
		imageYellow = new Image("res/splash/yellow1.png");
		for(int i = 0, cont = 0, yFiamma = (Config.ALTEZZA/2)-(title.getHeight()/2); i < 6; i++, yFiamma += 17)
			for(int j = 0, xFiamma = (Config.LARGHEZZA/2)-(title.getWidth()/2) - 40;j < 6; j++, xFiamma += 5, cont++)
				punto3[cont] = new Punto(xFiamma, yFiamma, ampiezza, incrementoAngolo, cont%3, imageRed, imageOrange, imageYellow);
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		
		g.drawImage(title, xTitle, yTitle);
		g.drawImage(copriTitolo, punto1[0].x, yTitle);
		
		for(int i = 0;i < fiamme;i++)
		{
			/* incremento  la x per far scorrere le fiamme */
			punto1[i].Disegna(g);
			punto2[i].Disegna(g);
			punto3[i].Disegna(g);
		}
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();
		
		for(int i = 0;i < fiamme;i++)
		{
			punto1[i].x += incrementoX*delta;
			punto2[i].x += incrementoX*delta;
			punto3[i].x += incrementoX*delta;
		}
		if(input.isKeyDown(Input.KEY_ESCAPE))
			sbg.enterState(Main.menu);
		/* DA RIMPIAZZARE CON L'ALTEZZA E LA LARGHEZZA DELLO SCHERMO FINALE */
		if(punto1[0].x > Config.LARGHEZZA)
			sbg.enterState(Main.menu, new FadeOutTransition(), new FadeInTransition());
	}
	
	public int getID(){
		return Main.splash;
	}
}
