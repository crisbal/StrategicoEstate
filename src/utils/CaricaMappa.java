package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.SlickException;

import materiali.Materiali;
import materiali.QuadratoGenerico;

public class CaricaMappa {

	public static int[][] livello1() {

		int[][] mappa = new int[][] {
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, },
				{ 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 2, 0, 0, 0, },
				{ 0, 0, 0, 1, 3, 1, 3, 3, 3, 1, 0, 0, 2, 0, 0, 0, },
				{ 0, 0, 0, 1, 3, 1, 3, 1, 1, 1, 0, 0, 2, 0, 2, 0, },
				{ 0, 0, 0, 1, 3, 3, 3, 3, 3, 1, 0, 0, 0, 0, 2, 0, },
				{ 0, 0, 0, 1, 1, 1, 3, 1, 3, 1, 0, 0, 0, 0, 2, 0, },
				{ 0, 0, 0, 1, 3, 3, 3, 1, 3, 1, 0, 0, 2, 0, 0, 0, },
				{ 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 2, 0, 0, 0, } };

		return mappa;
	}

	public static ArrayList<QuadratoGenerico> caricaQuadrati()
			throws SlickException {
		ArrayList<QuadratoGenerico> quadrati = new ArrayList<QuadratoGenerico>();
		int[][] mappa = livello1();
		for (int i = 0; i < mappa.length; i++) {
			for (int j = 0; j < mappa[i].length; j++) {
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
