package mainGioco;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import entities.Personaggi;

public class Main extends StateBasedGame {
	
	public static final String	NomeGioco	= "StrategicoGenerico"; // il titolo
	public static final int		gioca		= 1, battaglia = 2, menu = 3, creaPersonaggio = 4;
	
	public Main(String NomeGioco) {
		super(NomeGioco);
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		gc.setShowFPS(true);
		Personaggi.pulisciLista();
		
		this.addState(new Gioca(gioca));
		this.addState(new Battaglia(battaglia));
		this.addState(new Menu(menu));
		this.addState(new CreaPersonaggio());
		this.enterState(gioca);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AppGameContainer appgc;
		try
		{
			appgc = new AppGameContainer(new Main(NomeGioco));
			appgc.setDisplayMode(Config.LARGHEZZA, Config.ALTEZZA, false);
			appgc.setVSync(true);
			appgc.start(); // la apre/avvia
		} catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
	
}
