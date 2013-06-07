package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CaricaMappa {

	public int[] Carica() {
		List<Integer> list = new ArrayList<Integer>();
		File file = new File("mappa.txt");
		BufferedReader reader = null;
		int[] mappa = new int[6];
		try {
			reader = new BufferedReader(new FileReader(file));
			String text = null;
			int i = 0;
			while ((text = reader.readLine()) != null) {
				mappa[i] = Integer.parseInt(text);
				i++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
			}
		}

		return mappa;
	}

}
