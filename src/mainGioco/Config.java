package mainGioco;

public class Config {
	
	public static final float	Scala				= 0.9f;
	public static final int		ALTEZZA				= (int) (720 * Scala);	// schermo
	public static final int		LARGHEZZA			= (int) (1280 * Scala); // schermo
	public static final int		DIMENSIONE_IMMAGINE	= 64;
	
	public static int			RIGHE;
	public static int			COLONNE;
	
	public static String		mappa				= "";
	public static int			nSquadre			= 2;
	public static boolean		conBasi				= false;
	
	public static int			soldi				= 500;
}