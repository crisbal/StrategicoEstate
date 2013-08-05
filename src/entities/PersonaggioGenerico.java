package entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PersonaggioGenerico {
	
	public float	x, y;
	public float	xDisegno, yDisegno;
	Image			TextureGenerica, TextureSpecifica;
	Image			Selezionato	= new Image("res/GUI/Selezionato.png");
	Image			Movimento	= new Image("res/TextureTerreno/BloccoMovimento.png");
	public boolean	selezionato	= false;
	public boolean	inVita		= true;
	public String	Classe;
	Color			colore;
	
	public int		vita		= 100;
	public int		squadra;
	public int		raggio		= 2;
	public int		potenzaAttacco,potenzaDifesa;
	public int Identificativo;
	
	public PersonaggioGenerico(int y, int x, String tipo, int squadra,int id) throws SlickException {
		
		TextureGenerica = new Image(tipo + "Generico.png");
		TextureSpecifica = new Image(tipo + "Specifico.png");
		Classe = tipo;
		this.x = x; // la casella di appartenenza
		this.y = y;
		xDisegno = this.x * TextureGenerica.getWidth(); // il luogo sullo
														// schermo su cui va
														// disegnato
		yDisegno = this.y * TextureSpecifica.getHeight();
		this.squadra = squadra;
		if (squadra == Squadra.ROSSA)
			colore = new Color(1f, 0f, 0f);
		if (squadra == Squadra.VERDE)
			colore = new Color(0f, 1f, 0f);
		if (squadra == Squadra.BLU)
			colore = new Color(0.376f, 0.314f, 0.659f);
		
		if (tipo.matches(Tipo.CARRO))
		{
			raggio = 1;
			potenzaAttacco = 110;
			potenzaDifesa = 45;
		}
		if (tipo.matches(Tipo.SOLDATO))
		{
			raggio = 3;
			potenzaAttacco = 50;
			potenzaDifesa = 50;
		}
		if (tipo.matches(Tipo.AEREO))
		{
			raggio = 8;
			potenzaAttacco = 50;
			potenzaDifesa = 100;
		}
		System.out.println(Tipo.SOLDATO);
		Identificativo = id;
	}
	
	public void Disegna() {
		
		
		TextureSpecifica.draw(xDisegno, yDisegno, colore);
		TextureGenerica.draw(xDisegno, yDisegno);
		if (selezionato)
		{
			Selezionato.draw(xDisegno, yDisegno);
			MostraMovimento();
		}
	}
	
	public void DisegnaXY(float x, float y) {
		TextureSpecifica.draw(x, y, colore);
		TextureGenerica.draw(x, y);
		
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
						Movimento.draw((x + j - raggio) * Movimento.getWidth(), (y + i - raggio) * Movimento.getHeight(), colore);
		
	}
}
