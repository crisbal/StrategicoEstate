package materiali;

import utils.CaricaMappa;

public class Materiale {

	/*
	 * Accessibili con
	 * Materiali.nome
	 */
	
	static String Path = "res/TextureTerreno/"; 
	public static final String ERBA = Path + "Erba.png";
	public static final String ACQUA = Path + "Acqua.png";
	public static final String STRADA = Path + "Strada.png";
	public static final String MONTAGNA = Path + "Montagna.png";
	
	
	public static boolean Controllo(int[] arrayClick) {
		if(CaricaMappa.mappa[arrayClick[1]][arrayClick[0]]==1)
			return false;
		return true;
	}
	
	
}
