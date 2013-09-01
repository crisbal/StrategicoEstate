package javaGame;

import java.awt.Font;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;

public class Battaglia extends BasicGameState {

	/* https://bitbucket.org/kevglass/slick/src/9d7443ec33af/trunk/Slick/src/org/newdawn/slick/tests/AnimationTest.java?at=default
	 * codice interessante per gli sprite */
	/* new Image("res/GUI/Selezionato.png", false, Image.FILTER_NEAREST).getScaledCopy(Config.Scala); */
	/* immagine = immagine.getFlippedCopy(boolean flipHorizontal, boolean flipVertical) */
	Image sfondoSx;
	Image sfondoDx;
	Image separatore;
	Image healtbar;
	
	Font font;
	TrueTypeFont ttf;

	/* avanzamento: pixel sommati agli sprite ogni ciclo per far camminare
	 * i soldati
	 * statoBattaglia: 0 = cammino
	 * 				   1 = cammino
	 * 				   2 = cammino
	 * 				   3 = sparo */
	int avanzamento, statoBattaglia;
	/* needUpdate: calcolo se e' necessario effettuare l'update
	 * clock: tieni il tempo sin dall'inizio */
	int needUpdate, clock;
	float scale, scaleFactorForHealtbar;
	boolean scaleDirection;
	int[] randomX = new int[10];
	int[] randomY = new int[10];
	
	int width1, height1, width2, height2, frame1, frame2;
	public Animation allies;
    public Animation enemy;
    public Animation alliesFuoco;
    public Animation enemyFuoco;
	
	/* costruisco la potenza di fuoco in attacco */
	int potenzaAttacco;
	/* costruisco la resistenza in difesa */
	int potenzaDifesa;
	
	public Battaglia(int state){
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		
		font = new Font("OCRAStd", Font.BOLD, 32);
		ttf = new TrueTypeFont( (java.awt.Font) font, true);
		separatore = new Image("res/paesaggi/separatore.png");
		healtbar = new Image("res/hud/healtbar.png");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawImage(sfondoSx,0,0);
		g.drawImage(sfondoDx,(Singleton.width/2),0);
		g.drawImage(separatore,(Singleton.width/2)-(separatore.getWidth()/2),0);
		
		if(clock < 2000)
			if(Singleton.whoAttack == Id.player1)
			{
				for(int i = 0;i < Math.round(Singleton.attackerHealt/10);i++)
					allies.draw(100 + avanzamento + randomX[i],(Singleton.height/2) + randomY[i]);
				for(int i = 0;i < Math.round(Singleton.defenderHealt/10);i++)
					enemy.draw((Singleton.width - 200) - avanzamento + randomY[i],(Singleton.height/2) + randomX[i]);
				healtbar.draw(- (100 - Singleton.attackerHealt), 20);
				healtbar.getFlippedCopy(true,false).draw(Singleton.width - healtbar.getWidth() + (100 - Singleton.defenderHealt), 20);
			}
			else
			{
				for(int i = 0;i < Math.round(Singleton.defenderHealt/10);i++)
					enemy.draw(100 + avanzamento + randomX[i],(Singleton.height/2) + randomY[i]);
				for(int i = 0;i < Math.round(Singleton.attackerHealt/10);i++)
					allies.draw((Singleton.width - 200) - avanzamento + randomY[i],(Singleton.height/2) + randomX[i]);
				healtbar.draw(- (100 - Singleton.defenderHealt), 20);
				healtbar.getFlippedCopy(true,false).draw(Singleton.width - healtbar.getWidth() + (100 - Singleton.attackerHealt), 20);
			}
		else if(clock >= 2000)
		{
			if(Singleton.whoAttack == Id.player1)
			{
				for(int i = 0;i < Math.round(Singleton.attackerHealt/10);i++)
					alliesFuoco.draw(100 + avanzamento + randomX[i],(Singleton.height/2) + randomY[i]);
				for(int i = 0;i < Math.round(Singleton.defenderHealt/10);i++)
					enemyFuoco.draw((Singleton.width - 200) - avanzamento + randomY[i],(Singleton.height/2) + randomX[i]);
				healtbar.draw(- (100 - Singleton.attackerHealt), 20, scale);
				healtbar.getFlippedCopy(true,false).draw(Singleton.width - healtbar.getWidth() + (100 - Singleton.defenderHealt) + scaleFactorForHealtbar, 20, scale);
			}
			else
			{
				for(int i = 0;i < Math.round(Singleton.defenderHealt/10);i++)
					enemyFuoco.draw(100 + avanzamento + randomX[i],(Singleton.height/2) + randomY[i]);
				for(int i = 0;i < Math.round(Singleton.attackerHealt/10);i++)
					alliesFuoco.draw((Singleton.width - 200) - avanzamento + randomY[i],(Singleton.height/2) + randomX[i]);
				healtbar.draw(- (100 - Singleton.defenderHealt), 20, scale);
				healtbar.getFlippedCopy(true,false).draw(Singleton.width - healtbar.getWidth() + (100 - Singleton.attackerHealt) + scaleFactorForHealtbar, 20, scale);
			}
		}
		
		/* Percentuale vita */
		if(Singleton.whoAttack == Id.player1)
		{
			ttf.drawString(100 - Singleton.attackerHealt + healtbar.getWidth() + 3, 20 + 3, (int)Singleton.attackerHealt+"%", Color.gray);
			ttf.drawString(100 - Singleton.attackerHealt + healtbar.getWidth(), 20, (int)Singleton.attackerHealt+"%", Color.red);
			
			ttf.drawString(Singleton.width - healtbar.getWidth() - 100 + 3, 20 + 3, (int)Singleton.defenderHealt+"%", Color.gray);
			ttf.drawString(Singleton.width - healtbar.getWidth() - 100, 20, (int)Singleton.defenderHealt+"%", Color.red);
		}
		else
		{
			ttf.drawString(100 - Singleton.defenderHealt + healtbar.getWidth() + 3, 20 + 3, (int)Singleton.defenderHealt+"%", Color.gray);
			ttf.drawString(100 - Singleton.defenderHealt + healtbar.getWidth(), 20, (int)Singleton.defenderHealt+"%", Color.red);
			
			ttf.drawString(Singleton.width - healtbar.getWidth() - 100 + 3, 20 + 3, (int)Singleton.attackerHealt+"%", Color.gray);
			ttf.drawString(Singleton.width - healtbar.getWidth() - 100, 20, (int)Singleton.attackerHealt+"%", Color.red);
		}
		
		/* Potenza attacco/Resistenza difesa */
		ttf.drawString(10 + 3, 64 + 3, "Potenza attacco: "+potenzaAttacco, Color.gray);
		ttf.drawString(10, 64, "Potenza attacco: "+potenzaAttacco, Color.red);
		
		ttf.drawString(Singleton.width - 525 + 3, 64 + 3, "Resistenza difesa: "+potenzaDifesa, Color.gray);
		ttf.drawString(Singleton.width - 525, 64, "Resistenza difesa: "+potenzaDifesa, Color.red);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		
		if(Singleton.needReset == true)
		{
			/* reset per la grafica & meccaniche */
			avanzamento = 0;
			statoBattaglia = 0;
			needUpdate = 0;
			clock = 0;
			scale = 1;
			scaleFactorForHealtbar = 0;
			scaleDirection = true;
			for(int i = 0;i < 10;i++)
			{
				randomX[i] = (int)(Math.random()*100);
				randomY[i] = (int)(Math.random()*100);
			}
			for(int i = 0;i < 9;i++)
				for(int k = 1;k < 10;k++)
				{
					int temp;/*
					if(randomX[i] > randomX[k])
					{
						temp = randomX[i];
						randomX[i] = randomX[k];
						randomX[k] = temp;
					}*/
					if(randomY[i] > randomY[k])
					{
						temp = randomY[i];
						randomY[i] = randomY[k];
						randomY[k] = temp;
					}
				}
			System.out.println("inizio:");
			for(int i = 0; i< 10;i++)
			System.out.println(randomX[i]);
			System.out.println("fine");
			
			switch(Singleton.tipoUnitaAttacco)
			{
			case Id.fanteria:
				width1 = 32;
				height1 = 32;
				frame1 = 6;
				break;
			case Id.veicolo:
				width1 = 64;
				height1 = 64;
				frame1 = 4;
				break;
			}
			
			switch(Singleton.tipoUnitaDifesa)
			{
			case Id.fanteria:
				width2 = 32;
				height2 = 32;
				frame2 = 6;
				break;
			case Id.veicolo:
				width2 = 64;
				height2 = 64;
				frame2 = 4;
				break;
			}
			
			SpriteSheet sheetAttack = new SpriteSheet("res/unita/"+Singleton.whoAttack+Singleton.unitaAttacco+".png", width1, height1);
		    allies = new Animation();
		    for (int i = 0;i < frame1;i++)
		            allies.addFrame(sheetAttack.getSprite(i,0).getFlippedCopy(true, false), 150);
		    
		    SpriteSheet sheetDefend = new SpriteSheet("res/unita/"+Singleton.whoDefend+Singleton.unitaDifesa+".png", width2, height2);
		    enemy = new Animation();
		    for (int i = 0;i < frame2;i++)
		            enemy.addFrame(sheetDefend.getSprite(i,0), 150);
		    
		    SpriteSheet sheetAttackFuoco = new SpriteSheet("res/unita/"+Singleton.whoAttack+Singleton.unitaAttacco+".png", width1, height1);
		    alliesFuoco = new Animation();
		    for (int i = 0;i < 2;i++)
		            alliesFuoco.addFrame(sheetAttackFuoco.getSprite(i,1).getFlippedCopy(true, false), 150);
		    
		    SpriteSheet sheetDefendFuoco = new SpriteSheet("res/unita/"+Singleton.whoDefend+Singleton.unitaDifesa+".png", width2, height2);
		    enemyFuoco = new Animation();
		    for (int i = 0;i < 2;i++)
		            enemyFuoco.addFrame(sheetDefendFuoco.getSprite(i,1), 150);
			
			sfondoSx = new Image("res/paesaggi/"+Singleton.campoAttacco+".png");
			sfondoDx = new Image("res/paesaggi/"+Singleton.campoDifesa+".png");
	
			/* reset per il calcolo del danno della battaglia */
			potenzaAttacco = 10;
			potenzaDifesa = 0;
			
			/* calcolo dell'attacco, della difesa, del danno, ecc. per unita */
			switch(Singleton.unitaAttacco)
			{
			case Id.soldato:
				potenzaAttacco += 5;
				if(Tecnologies.soldatoPiuAttacco[Singleton.whoAttack])
					potenzaAttacco += 5;
				break;
			case Id.carro:
				potenzaAttacco += 5;
				if(Tecnologies.carroPiuAttacco[Singleton.whoAttack])
					potenzaAttacco += 5;
				break;
			}
			
			switch(Singleton.unitaDifesa)
			{
			case Id.soldato:
				potenzaDifesa += 5;
				if(Tecnologies.soldatoPiuDifesa[Singleton.whoDefend])
					potenzaDifesa += 5;
				break;
			case Id.carro:
				potenzaDifesa += 5;
				if(Tecnologies.carroPiuDifesa[Singleton.whoDefend])
					potenzaDifesa += 5;
				break;
			}
			
			/* calcolo dell'attacco, della difesa, del danno, ecc. per tipo unita */
			switch(Singleton.tipoUnitaAttacco)
			{
			case Id.veicolo:
				if(Tecnologies.veicoloVsFanteria[Singleton.whoAttack] && Singleton.tipoUnitaDifesa == Id.fanteria)
					potenzaAttacco += 5;
				break;
			}
			
			/* calcolo i vantaggi e gli svantaggi tra unita vs unita */
			if(Singleton.unitaAttacco == Id.veicolo && Singleton.unitaDifesa == Id.fanteria)
			{
				potenzaAttacco += 5;
				potenzaDifesa -= 5;
			}
			else if(Singleton.unitaAttacco == Id.fanteria && Singleton.unitaDifesa == Id.veicolo)
				potenzaDifesa += 5;
			
			potenzaAttacco = Math.round(potenzaAttacco * (Singleton.attackerHealt / 100));
			potenzaDifesa = Math.round(potenzaDifesa * (Singleton.defenderHealt / 100));
			
			Singleton.attackerResultHealt = (int) (Singleton.attackerHealt - potenzaDifesa);
			Singleton.defenderResultHealt = (int) (Singleton.defenderHealt - potenzaAttacco);
			
			Singleton.ready = true;
			Singleton.needReset = false;
		}
		
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_ESCAPE))
			sbg.enterState(Game.menu);
		
		clock += delta;
		needUpdate += delta;
		if(needUpdate > 15)
		{
			if(clock < 2000)
				avanzamento += 2;
			else
			{
				if(clock < 4000)
				{
					if(scaleDirection == true)
						scale += 0.05;
					else
						scale -= 0.05;
					if(scale >= 1.2)
						scaleDirection = false;
					else if(scale <= 0.8)
						scaleDirection = true;
	
					/* 357 = larghezza healtbar (getWidth() da' problemi) */
					scaleFactorForHealtbar = 357 * (scale - 1) * -1;
				}
				else
				{
					scaleFactorForHealtbar = 0;
					scale = (float) 1.0;
				}
				
				/* Permette l'effetto "percentuale che cala" della vita */
				if(Singleton.attackerHealt > Singleton.attackerResultHealt)
					Singleton.attackerHealt -= 0.3;
				if(Singleton.defenderHealt > Singleton.defenderResultHealt)
					Singleton.defenderHealt -= 0.3;
				
				/* Evita che ci sia un punto in meno nella vita reale */
				if(Singleton.attackerHealt < Singleton.attackerResultHealt)
					Singleton.attackerHealt = Singleton.attackerResultHealt;
				if(Singleton.defenderHealt < Singleton.defenderResultHealt)
					Singleton.defenderHealt = Singleton.defenderResultHealt;
			}
			
			/* Evita che la vita vadi sotto zero */
			if(Singleton.attackerResultHealt <= 0)
				Singleton.attackerHealt = 0;
			if(Singleton.defenderResultHealt <= 0)
				Singleton.defenderHealt = 0;
			
			needUpdate = 0;
		} 
	}
	
	public int getID(){
		return 3;
	}
}