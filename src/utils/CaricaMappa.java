package utils;

import org.newdawn.slick.SlickException;

import mainGioco.Config;
import materiali.Materiale;
import materiali.QuadratoMappa;

public class CaricaMappa {

	public static int[][] mappa = null;
	public static QuadratoMappa[][] quadratoMappa = null;

	public static int[][] livello1() {

		mappa = new int[][] {
				{ 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, },
				{ 2, 0, 0, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, },
				{ 2, 0, 0, 0, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, },
				{ 2, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 3, },
				{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 3, },
				{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 3, },
				{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 3, },
				{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, },
				{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, },
				{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, } };

		quadratoMappa = new QuadratoMappa[Config.RIGHE][Config.COLONNE];
		return mappa;
	}

	public static QuadratoMappa[][] caricaQuadrati() throws SlickException {
		int[][] mappa = livello1();
		for (int i = 0; i < Config.RIGHE; i++) {
			for (int j = 0; j < Config.COLONNE; j++) {
				switch (mappa[i][j]) {
				case 0:
					quadratoMappa[i][j] = new QuadratoMappa(Materiale.ERBA,
							j, i);
					break;

				case 1:
					quadratoMappa[i][j] = new QuadratoMappa(Materiale.ACQUA,
							j, i);
					break;
				case 2:
					quadratoMappa[i][j] = new QuadratoMappa(
							Materiale.STRADA, j, i);
					break;
				case 3:
					quadratoMappa[i][j] = new QuadratoMappa(
							Materiale.MONTAGNA, j, i);
					break;
				}
			}
		}
		return quadratoMappa;

	}
}
