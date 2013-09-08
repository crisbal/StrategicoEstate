package mainGioco;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import entities.Personaggi;
import entities.Squadra;
import entities.Tipo;

public class Main extends StateBasedGame {
	
	public static final String	NomeGioco	= "StrategicoGenerico"; // il titolo
	public static final int		splash		= 0, gioca = 1, battaglia = 2, menu = 3, creaPersonaggio = 4, scegliMappa = 5, potenziamento = 6;
	
	public Main(String NomeGioco) {
		super(NomeGioco);
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		gc.setShowFPS(true);
		Personaggi.pulisciLista();
		this.addState(new Splash());
		this.addState(new Gioca());
		this.addState(new Battaglia());
		this.addState(new Menu());
		this.addState(new CreaPersonaggio());
		this.addState(new ScegliMappa());
		this.addState(new Tecnologies());
		this.enterState(splash);
		
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AppGameContainer appgc;
		try
		{
			
			Squadra.squadra.put(1, new Color(1f, 0f, 0f));
			Squadra.squadra.put(2, new Color(1f, 0.906f, 0.004f));
			Squadra.squadra.put(3, new Color(0f, 1f, 0f));
			
			Squadra.squadraAWT.put(1, java.awt.Color.red);
			Squadra.squadraAWT.put(2, java.awt.Color.yellow);
			Squadra.squadraAWT.put(3, java.awt.Color.green);

			Tipo.tipo.put("Soldato", 0);
			Tipo.tipo.put("Autoblindo", 1);
			Tipo.tipo.put("Aereo", 2);
			Tipo.tipo.put("Carro", 4);
			
			
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
