package entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PersonaggioGenerico {

	public float x, y;
	public float xDisegno, yDisegno;
	Image TextureGenerica, TextureSpecifica;
	Image Selezionato = new Image("res/GUI/Selezionato.png");
	Image Movimento = new Image("res/TextureTerreno/BloccoMovimento.png");
	public boolean selezionato = false;
	public boolean inVita = true;
	Color colore;

	public int vita = 100;
	public int squadra;
	public int raggio = 2;

	public PersonaggioGenerico(int y, int x, String tipo, int squadra)
			throws SlickException {
		TextureGenerica = new Image(tipo + "Generico.png");
		TextureSpecifica = new Image(tipo + "Specifico.png");
		this.x = x; // la casella di appartenenza
		this.y = y;
		xDisegno = this.x * TextureGenerica.getWidth(); // il luogo sullo
														// schermo su cui va
														// disegnato
		yDisegno = this.y * TextureSpecifica.getHeight();
		this.squadra = squadra;
		if (squadra == Squadra.ROSSA)
			colore = new Color(1f, 0.5f, 0.5f);
		if (squadra == Squadra.VERDE)
			colore = new Color(0f, 0.5f, 0.5f);
		if (squadra == Squadra.BLU)
			colore = new Color(1f, 1f, 1f);

		if (tipo == Tipo.CARRO)
			raggio = 1;
		if (tipo == Tipo.SOLDATO)
			raggio = 3;
	}

	public void Disegna() {
		TextureGenerica.draw(xDisegno, yDisegno);

		TextureSpecifica.draw(xDisegno, yDisegno, colore);
		if (selezionato) {
			Selezionato.draw(xDisegno, yDisegno);
			MostraMovimento();
		}
	}

	public void Sposta(int y, int x) {
		this.x = x;
		this.y = y;
		xDisegno = this.x * TextureSpecifica.getWidth();
		yDisegno = this.y * TextureSpecifica.getHeight();
		selezionato = false;
	}

	public void MostraMovimento() {
		// TODO Auto-generated method stub
		for (int i = 0; i < raggio * 2 + 1; i++)
			for (int j = 0; j < raggio * 2 + 1; j++)
				if (Math.abs(j - raggio) + Math.abs(i - raggio) <= raggio)
					if (i != raggio || j != raggio)
						Movimento.draw((x + j - raggio) * Movimento.getWidth(),
								(y + i - raggio) * Movimento.getHeight(),
								colore);

	}
}
