package utils;

import mainGioco.Config;

import org.lwjgl.input.Mouse;

public class GestoreMouse {

	public static int[] ZonaClick(){
		
		int[] vettorePosizione=new int[2];
		
		
		float mX=Mouse.getX();
		
		float mY=(Config.ALTEZZA-Mouse.getY()-1);
		
		
		//sono stupido, facevo tanti calcoli, ma alla fine la formula era questa vvvv 
		vettorePosizione[0] = (int) (mX / (Config.DIMENSIONE_IMMAGINE * Config.Scala));
		vettorePosizione[1] = (int) (mY / (Config.DIMENSIONE_IMMAGINE * Config.Scala));
		
		return vettorePosizione;
		
	}
}
