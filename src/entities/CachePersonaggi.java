package entities;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

public class CachePersonaggi {

	public static ArrayList<PersonaggioGenerico> personaggio = new ArrayList<PersonaggioGenerico>();

	public static void pulisciLista() throws SlickException {
		personaggio.clear();
	}
}
