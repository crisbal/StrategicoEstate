package mainGioco;

import org.newdawn.slick.Color;

public class Config { // classe di configurazione, modificare questa per
	// modificare le proprieta'
	// della finestra, della mappa e della risoluzione dell'immagine
	
	public static int			RIGHE;
	public static int			COLONNE;
	public static final float	Scala				= 1f;
	public static final int		ALTEZZA				= (int) (720 * Scala);	// schermo
	public static final int		LARGHEZZA			= (int) (1280 * Scala); // schermo
	public static final int		DIMENSIONE_IMMAGINE	= 64;
	
}