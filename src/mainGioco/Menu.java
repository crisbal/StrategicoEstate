package mainGioco;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entities.Nuvola;

public class Menu  extends BasicGameState {
	int offset = 100;
	Image menu,selettore;
	Nuvola[] nuvole = new Nuvola[4]; 
	int selezionato = 0;
	boolean indietro;
	Animation allies;
	int	spostamento = 0;
	public Menu(int menu2) {
		// TODO Auto-generated constructor stub
	}


	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// TODO Auto-generated method stub
		menu = new Image("res/GUI/Menu.png");
		selettore = new Image("res/GUI/Selettore.png");
		SpriteSheet sheetAllies = new SpriteSheet("res/GUI/allies.png", 32, 32);
		allies = new Animation();
	    for (int i = 0;i < 6;i++)
	            allies.addFrame(sheetAllies.getSprite(i,0).getFlippedCopy(true, false), 150);
	    
		for(int i = 0;i<4;i++)
		{
			nuvole[i]=new Nuvola();
		}
		
	}


	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		// TODO Auto-generated method stub
		menu.draw();
		for(int i = 0;i<4;i++)
		{
			nuvole[i].immagine.draw(nuvole[i].x, nuvole[i].y,nuvole[i].scala);
		}
		
		if(selezionato == 0)
		{
			selettore.draw(550 - offset, 200 - selettore.getHeight()/2);
		}
		else if(selezionato == 1)
		{
			selettore.draw(550 - offset , 287 - selettore.getHeight()/2);
		}
		else if(selezionato == 2)
		{
			selettore.draw(550 - offset , 375 - selettore.getHeight()/2);
		}
		
		for(int i = 0;i<10;i++)
			for(int j = 0;j<8;j++)
				allies.draw(100 + i * 30  +spostamento,450+j*30);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO Auto-generated method stub
		Input input = gc.getInput();
		
		
		if(input.isKeyPressed(input.KEY_UP) || input.isKeyPressed(input.KEY_W))
		{
			selezionato --;
		}else if(input.isKeyPressed(input.KEY_DOWN) || input.isKeyPressed(input.KEY_S))
		{
			selezionato ++;
		}
		
		
		if(input.isKeyPressed(input.KEY_ENTER))
			switch(selezionato)
			{
				case 0:
					sbg.enterState(Main.gioca);
				case 2:
					gc.exit();
			}
		if(selezionato < 0)
			selezionato = 2;
		if(selezionato > 2)
			selezionato = 0;
		
		if(offset > 150 || indietro)
		{
			offset -= 0.2f * delta;
			indietro = true;
		}
		if(offset <= 100 || !indietro)
		{
			offset += 0.2f * delta;
			indietro = false;
		}
		
		for(int i = 0;i<4;i++)
		{
			nuvole[i].x += nuvole[i].speed * delta;
			if(nuvole[i].x < -200 || nuvole[i].x > Config.LARGHEZZA + 200)
				nuvole[i] = new Nuvola();
		}
		
		spostamento += 0.07f*delta;
		if(spostamento > 1200)
			spostamento = -500;
	}


	public int getID() {
		// TODO Auto-generated method stub
		return Main.menu;
	}
	
}
