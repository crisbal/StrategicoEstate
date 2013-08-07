package entities;

import mainGioco.Config;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class PersonaggioGenerico {
	
	public int	x, y;
	public float	xDisegno, yDisegno;
	private Image	TextureGenerica, TextureSpecifica;
	private Image	Selezionato	= new Image("res/GUI/Selezionato.png");
	private Image	Movimento	= new Image("res/TextureTerreno/BloccoMovimento.png");
	public boolean	selezionato	= false;
	public boolean	inVita		= true;
	public String	Classe;
	public Color	colore;
	
	public int		vita		= 100;
	public int		squadra;
	public int		raggio		= 2;
	public int		potenzaAttacco, potenzaDifesa;
	public int		Identificativo;
	
	public PersonaggioGenerico(int y, int x, String tipo, int squadra, int id) throws SlickException {
		
		TextureGenerica = new Image(Tipo.Path + tipo + "Generico.png");
		TextureSpecifica = new Image(Tipo.Path + tipo + "Specifico.png");
		Classe = tipo;
		this.x = x; // la casella di appartenenza
		this.y = y;
		xDisegno = this.x * TextureGenerica.getWidth() * Config.Scala; // il
																		// luogo
																		// sullo
		// schermo su cui va
		// disegnato
		yDisegno = this.y * TextureSpecifica.getHeight() * Config.Scala;
		this.squadra = squadra;

		colore = (Color) Squadra.squadra.get(this.squadra);
		
		if ((Tipo.Path + tipo).matches(Tipo.CARRO))
		{
			raggio = 1;
			potenzaAttacco = 110;
			potenzaDifesa = 45;
		}
		if ((Tipo.Path + tipo).matches(Tipo.SOLDATO))
		{
			raggio = 3;
			potenzaAttacco = 50;
			potenzaDifesa = 50;
		}
		if ((Tipo.Path + tipo).matches(Tipo.AEREO))
		{
			raggio = 8;
			potenzaAttacco = 50;
			potenzaDifesa = 100;
		}
		Identificativo = id;
	}
	
	public void Disegna() {
		
		TextureSpecifica.draw(xDisegno, yDisegno, Config.Scala, colore);
		TextureGenerica.draw(xDisegno, yDisegno, Config.Scala);
		if (selezionato)
		{
			Selezionato.draw(xDisegno, yDisegno, Config.Scala);
			MostraMovimento();
		}
	}
	
	public void DisegnaXY(float x, float y) {
		TextureSpecifica.draw(x, y, Config.Scala, colore);
		TextureGenerica.draw(x, y, Config.Scala);
		
	}
	
	public void Sposta(int y, int x) {
		this.x = x;
		this.y = y;
		xDisegno = this.x * TextureSpecifica.getWidth() * Config.Scala;
		yDisegno = this.y * TextureSpecifica.getHeight() * Config.Scala;
		selezionato = false;
	}
	
	public void MostraMovimento() {
		// TODO Auto-generated method stub
		for (int i = 0; i < raggio * 2 + 1; i++)
			for (int j = 0; j < raggio * 2 + 1; j++)
				if (Math.abs(j - raggio) + Math.abs(i - raggio) <= raggio)
					if (i != raggio || j != raggio)
						Movimento.draw((x + j - raggio) * Movimento.getWidth() * Config.Scala,
								(y + i - raggio) * Movimento.getHeight() * Config.Scala, Config.Scala, colore);
		
	}
	
	
	public float getWidth(){
		return TextureSpecifica.getWidth()*Config.Scala;
	}
	
	public float getHeight(){
		return TextureSpecifica.getHeight()*Config.Scala;
	}
}
