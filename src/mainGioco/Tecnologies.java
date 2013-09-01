package mainGioco;

import java.awt.Font;

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
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Tecnologies extends BasicGameState {
	
	int margineX = 10;
	
	Bottone bottoniTecnologie;
	Testo testoTecnologie;
	Image sfondo;
	Image spunta;
	Image tecbar;
	Color azzurro;
	int ombra = 3;
	int rapporto = Config.ALTEZZA/20;
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		testoTecnologie = new Testo("Verdana", Font.PLAIN,32, java.awt.Color.black);
		sfondo = new Image("res/GUI/tecnologies.png");
		spunta = new Image("res/GUI/spunta.png");
		azzurro = new Color(0,255,255);
		//tecbar = new Image("res/GUI/tecbar.png");
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
	
		sfondo.draw(0,0);
		
		//tecbar.draw(0,rapporto*4);
		//tecbar.draw(0,rapporto*11);
		//tecbar.draw(0,rapporto*17);
		
		if(Personaggi.attaccante.Classe.equals("Soldato"))
		{
			testoTecnologie.disegna("Aumenta la potenza \ndella fanteria", margineX + ombra, ombra + rapporto*0);
			testoTecnologie.disegna("Aumenta la potenza \ndella fanteria", margineX, rapporto*0);
			
			testoTecnologie.disegna("Aumenta la difesa \ndella fanteria", margineX + ombra, ombra + rapporto*7);
			testoTecnologie.disegna("Aumenta la difesa \ndella fanteria", margineX, rapporto*7);
		}
		if(Personaggi.attaccante.Classe.equals("Carro"))
		{
			testoTecnologie.disegna("Aumenta la potenza \ndei carri", margineX + ombra, ombra + rapporto*14);
			testoTecnologie.disegna("Aumenta la potenza \ndei carri", margineX, rapporto*14);
		}
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		
		Input input = gc.getInput();
		if(input.isKeyPressed(input.KEY_ESCAPE))
			sbg.enterState(Main.gioca);
	}
	
	public int getID() {
		// TODO Auto-generated method stub
		return Main.potenziamento;
	}
}
