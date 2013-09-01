package gui;

import java.awt.Color;
import java.awt.Font;

import mainGioco.Config;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.font.effects.GradientEffect;

public class Testo {
	
	private UnicodeFont font;
	private Font fontAppoggio;
	public TrueTypeFont ttf;
	private int	x,y,accumulatoreY;
	
	public static final int CENTROORIZ = -1;
	public static final int CENTROVERT = -2;
	
	public Testo(String tipoFont, int StileTesto,int dimensione,Color colore) throws SlickException{
		font = new UnicodeFont(new Font(tipoFont, StileTesto,(int) (dimensione*Config.Scala)));
		
		font.getEffects().add(new ColorEffect(colore));
		
		font.addAsciiGlyphs();
		font.loadGlyphs();
		
		fontAppoggio = new Font(tipoFont, StileTesto, (int) (dimensione*Config.Scala));
		ttf = new TrueTypeFont(fontAppoggio, false);
	}
	
	
	public void disegna(String testo,int x,int y)
	{
		this.x = x;
		this.y = y;
		
		if (x == CENTROORIZ)
			this.x = Config.LARGHEZZA/ 2 - getMaxWidth(testo) / 2;
		if (y == CENTROVERT)
			this.y = Config.ALTEZZA / 2 - getTotalHeight(testo) / 2;
		
		font.drawString(this.x, this.y, testo);
	}
	
	public int getMaxWidth(String content) {
		String[] splittedContent = content.split("\n");
		int maxWidth = 0;
		
		for (int i = 0; i < splittedContent.length; i++)
		{
			if (ttf.getWidth(splittedContent[i]) > maxWidth)
			{
				maxWidth = ttf.getWidth(splittedContent[i]);
			}
		}
		
		return maxWidth;
	}
	
	public int getTotalHeight(String content) {
		String[] splittedContent = content.split("\n");
		int totalH = 0;
		
		for (int i = 0; i < splittedContent.length; i++)
		{
			
			totalH += ttf.getHeight(splittedContent[i]);
		}
		
		return totalH;
	}
}
