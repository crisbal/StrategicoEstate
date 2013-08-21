package entities;

import java.util.Random;

import mainGioco.Config;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Nuvola {
	
	public Image	immagine;
	public float	x, y, speed, scala;
	
	public Nuvola() throws SlickException {
		
		Random rnd = new Random();
		immagine = new Image("res/GUI/nuvola.png");
		boolean orien = rnd.nextBoolean();
		/*
		 * if(!orien) { x = 0 - rnd.nextInt(200); y =
		 * rnd.nextInt(100)*Config.Scala; do{ speed = rnd.nextFloat() + 0.1f;
		 * }while(speed >0.5 || speed <0.2);
		 * 
		 * 
		 * } else
		 */
		{
			x = Config.LARGHEZZA + rnd.nextInt((int) (200 * Config.Scala));
			y = rnd.nextInt((int) (100 * Config.Scala));
			do
			{
				speed = -(rnd.nextFloat() + 0.1f);
			} while (speed < -0.5 || speed > -0.2);
			
		}
		
		do
		{
			scala = (rnd.nextFloat() + 0.1f) * Config.Scala;
		} while (scala < 0.3);
		
	}
	
}
