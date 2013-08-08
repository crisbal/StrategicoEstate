package entities;

import java.util.HashMap;
import java.util.Map;

public class Tipo {
	/*
	 * Accessibili con Tipo.nome
	 */
	
	
	public static Map tipo = new HashMap();
	
	public static final String	Path	= "res/Personaggi/";
	public static final String	SOLDATO	= Path + "Soldato";
	public static final String	CARRO	= Path + "Carro";
	public static final String	AEREO	= Path + "Aereo";
}
