package main;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Main extends StateBasedGame {

	public static final String NomeGioco = "StrategicoGenerico";
	public static final int menu = 0;
	public static final int gioca = 1;
	public static final int opzioni = 2;
	public static final int splash = 3;
	
	public Main(String NomeGioco) {
		super(NomeGioco);
		//this.addState(new Menu(menu));
		//this.addState(new Opzioni(opzioni));
		//this.addState(new Splash(splash));
		
		this.addState(new Play(gioca));

		// TODO Auto-generated constructor stub
	}

	public void initStatesList(GameContainer gc) throws SlickException {
		//this.getState(menu).init(gc, this);
		this.getState(gioca).init(gc, this);
		this.enterState(gioca);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AppGameContainer appgc;
		try {
			appgc=new AppGameContainer(new Main(NomeGioco));
			appgc.setDisplayMode(800, 600, false);
			appgc.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
