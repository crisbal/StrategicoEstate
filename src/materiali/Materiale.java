package materiali;

import utils.CaricaMappa;

public class Materiale {
	
	/*
	 * Accessibili con Materiali.nome
	 */
	
	public static final String				Path		= "res/TextureTerreno/";
	public static boolean Controllo(int[] arrayClick) {
		if (CaricaMappa.mappa[arrayClick[1]][arrayClick[0]].contains("acqua"))
			return false;
		return true;
	}
	
}
