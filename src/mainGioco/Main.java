package mainGioco;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import entities.CachePersonaggi;

public class Main extends StateBasedGame {

	public static final String NomeGioco = "StrategicoGenerico"; // il titolo
	public static final int gioca = 1;
	public static final int battaglia = 2;
	public Main(String NomeGioco) {
		super(NomeGioco);
		
		this.addState(new Gioca(gioca));
		this.addState(new Battaglia(battaglia));
		
		// TODO Auto-generated constructor stub
	}

	public void initStatesList(GameContainer gc) throws SlickException {
		gc.setShowFPS(false);
		CachePersonaggi.pulisciLista();
		this.getState(gioca).init(gc, this);
		this.getState(battaglia).init(gc, this);
		this.enterState(gioca);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AppGameContainer appgc;
		try {
			appgc = new AppGameContainer(new Main(NomeGioco));
			appgc.setDisplayMode(Config.LARGHEZZA, Config.ALTEZZA, false);
			appgc.start(); // la apre/avvia
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
