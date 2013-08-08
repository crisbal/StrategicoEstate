package entities;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

public class Personaggi {
	
	public static ArrayList<PersonaggioGenerico>	personaggio	= new ArrayList<PersonaggioGenerico>();
	public static PersonaggioGenerico				attaccante, difensore;
	public static ArrayList<Giocatore> giocatori = new ArrayList<Giocatore>();
	
	public static void pulisciLista() throws SlickException {
		personaggio.clear();
	}
	
	public static PersonaggioGenerico clickSuPersonaggioNemico(PersonaggioGenerico personaggioAttuale, int yClick, int xClick) {
		for (int i = 0; i < personaggio.size(); i++)
		{
			if (personaggio.get(i).x == xClick && personaggio.get(i).y == yClick
					&& personaggioAttuale.squadra != personaggio.get(i).squadra)
			{
				return personaggio.get(i);
			}
			
		}
		
		return null;
		
	}
}
