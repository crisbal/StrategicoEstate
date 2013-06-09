package utils;

import java.util.ArrayList;
import org.newdawn.slick.SlickException;

import mainGioco.Config;
import materiali.Materiali;
import materiali.QuadratoGenerico;

public class CaricaMappa {

	public static int[][] livello1() {

		int[][] mappa = new int[][] {
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
				{ 0, 0, 0, 3, 3, 0, 0, 0, 0, 0, 0, 0, 3, 3, 0, 2, },
				{ 0, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
				{ 0, 0, 2, 2, 0, 3, 3, 2, 2, 2, 0, 2, 0, 0, 0, 0, },
				{ 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, },
				{ 0, 0, 0, 0, 0, 3, 2, 2, 3, 3, 0, 1, 0, 0, 0, 0, },
				{ 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, },
				{ 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, } };
		return mappa;
	}

	public static ArrayList<QuadratoGenerico> caricaQuadrati()
			throws SlickException {
		ArrayList<QuadratoGenerico> quadrati = new ArrayList<QuadratoGenerico>();
		int[][] mappa = livello1();
		for (int i = 0; i < Config.RIGHE; i++) {
			for (int j = 0; j < Config.COLONNE; j++) {
				switch (mappa[i][j]) {
				case 0:
					quadrati.add(new QuadratoGenerico(Materiali.ERBA, j, i));
					break;

				case 1:
					quadrati.add(new QuadratoGenerico(Materiali.ACQUA, j, i));
					break;
				case 2:
					quadrati.add(new QuadratoGenerico(Materiali.STRADA, j, i));
					break;
				case 3:
					quadrati.add(new QuadratoGenerico(Materiali.MONTAGNA, j, i));
					break;
				}
			}
		}
		return quadrati;

	}
}
