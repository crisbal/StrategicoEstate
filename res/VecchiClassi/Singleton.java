package javaGame;

public class Singleton {

	public static int width = 1280, height = 720;
	
	public static boolean ready = false;
	public static boolean needReset = true;
	
	/* possono essere settate anche con setSingleton, siccome cambiano
	 * ad ogni battaglia */
	public static int whoAttack = Id.player1;
	public static int whoDefend = Id.player2;
	public static int campoAttacco = Id.erba;
	public static int campoDifesa = Id.erba;
	public static int unitaAttacco = Id.soldato;
	public static int unitaDifesa = Id.soldato;
	public static int tipoUnitaAttacco = Id.fanteria;
	public static int tipoUnitaDifesa = Id.fanteria;
	public static float attackerHealt = 100;
	public static float defenderHealt = 100;
	
	/* risultati battaglia */
	public static int attackerResultHealt = 0;
	public static int defenderResultHealt = 0;
	
	private Singleton(){
		
	}
	
	public static void setSingleton(int whoAttack, int campoAttacco, int campoDifesa, int unitaAttacco, int unitaDifesa, int attackerHealt, int defenderHealt){
		Singleton.needReset = true;
		
		Singleton.whoAttack = whoAttack;
		if(Singleton.whoAttack == Id.player1)
			Singleton.whoDefend = Id.player2;
		else
			Singleton.whoDefend = Id.player1;
		Singleton.campoAttacco = campoAttacco;
		Singleton.campoDifesa = campoDifesa;
		Singleton.unitaAttacco = unitaAttacco;
		Singleton.unitaDifesa = unitaDifesa;
		switch(unitaAttacco)
		{
		case Id.soldato:
			Singleton.tipoUnitaAttacco = Id.fanteria;
			break;
		case Id.carro:
			Singleton.tipoUnitaAttacco = Id.veicolo;
			break;
		}
		switch(unitaDifesa)
		{
		case Id.soldato:
			Singleton.tipoUnitaDifesa = Id.fanteria;
			break;
		case Id.carro:
			Singleton.tipoUnitaDifesa = Id.veicolo;
			break;
		}
		Singleton.attackerHealt = attackerHealt;
		Singleton.defenderHealt = defenderHealt;
	}
}
