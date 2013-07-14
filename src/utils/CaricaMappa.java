package utils;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.newdawn.slick.SlickException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import mainGioco.Config;
import materiali.Materiale;
import materiali.QuadratoMappa;

public class CaricaMappa {
	
	public static String[][]			mappa			= null;
	public static QuadratoMappa[][]	quadratoMappa	= null;

	public static String[][] creaMatriceMappa() {
		
		try
		{
			File fXmlFile = new File("mappa.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			doc.getDocumentElement().normalize();
			
			Config.RIGHE = Integer.parseInt(doc.getElementsByTagName("altezza").item(0).getTextContent());
			Config.COLONNE = Integer.parseInt(doc.getElementsByTagName("larghezza").item(0).getTextContent());
			
			mappa = new String[Config.RIGHE][Config.COLONNE];
			
			NodeList quadrati = doc.getElementsByTagName("tile");
			
			for (int i = 0; i < quadrati.getLength(); i++)
			{
				Node nNode = quadrati.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					 
					Element tile = (Element) nNode;
					
					mappa[Integer.parseInt(tile.getAttribute("y"))][Integer.parseInt(tile.getAttribute("x"))] = tile.getTextContent();
					
				}
			}
			
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return mappa;
	}
	
	public static QuadratoMappa[][] caricaQuadrati() throws SlickException {
		String[][] mappa = creaMatriceMappa();
		
		quadratoMappa = new QuadratoMappa[Config.RIGHE][Config.COLONNE];
		for (int i = 0; i < Config.RIGHE; i++)
		{
			for (int j = 0; j < Config.COLONNE; j++)
			{
				quadratoMappa[i][j] = new QuadratoMappa(Materiale.Path + mappa[i][j] + ".png",j,i);
			}
		}
		return quadratoMappa;
		
	}
}
