package utils;

import mainGioco.Config;

import org.lwjgl.input.Mouse;

public class GestoreMouse {

	public static int[] ZonaClick(){
		
		int[] vettorePosizione=new int[2];
		
		
		float mX=Mouse.getX();
		float mY=(Config.ALTEZZA-Mouse.getY()-1);
		
		for(int i=0;i<Config.COLONNE;i++)
		{
			vettorePosizione[0]=(int) (Config.DIMENSIONE_IMMAGINE*(i+1) * Config.Scala);   //faccio un for che identifica la casella che si avvicina di piu' a dove ho cliccato
			if(vettorePosizione[0]>mX)
				break;
		}
		for(int i=0;i<Config.RIGHE;i++)
		{
			vettorePosizione[1]=(int) ((Config.DIMENSIONE_IMMAGINE*(i+1)) * Config.Scala);
			if(vettorePosizione[1]>mY)
				break;
		}
		vettorePosizione[0]/=Config.DIMENSIONE_IMMAGINE * Config.Scala;   //dalla coordinata della casella passo alla posizione della tabella-mappa
		vettorePosizione[1]/=Config.DIMENSIONE_IMMAGINE * Config.Scala;
		vettorePosizione[0]--;   //gli tolgo 1 per avere coordinate che partono da 0,0
		vettorePosizione[1]--;
		
		return vettorePosizione;
		
	}
}
