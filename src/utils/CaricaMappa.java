package utils;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import entities.Personaggi;
import entities.PersonaggioGenerico;
import entities.Squadra;
import entities.Tipo;

import mainGioco.Config;
import materiali.Materiale;
import materiali.QuadratoMappa;

public class CaricaMappa {
	
	public static String[][]		mappa			= null;
	public static QuadratoMappa[][]	quadratoMappa	= null;
	
	private static String[][] creaMatriceMappa(String nomeMappa) {
		
		try
		{
			File fXmlFile = new File("mappe/" + nomeMappa);
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
				if (nNode.getNodeType() == Node.ELEMENT_NODE)
				{
					
					Element tile = (Element) nNode;
					
					mappa[Integer.parseInt(tile.getAttribute("y"))][Integer.parseInt(tile.getAttribute("x"))] = tile
							.getTextContent();
					
				}
			}
			
			Personaggi.pulisciLista();
			Squadra.squadra.put(1, new Color(1f, 0f, 0f));
			Squadra.squadra.put(3, new Color(0f, 1f, 0f));
			Squadra.squadra.put(2, new Color(1f, 0.906f, 0.004f));
			
			Tipo.tipo.put("Soldato", 0);
			Tipo.tipo.put("Carro", 1);
			
			NodeList personaggi = doc.getElementsByTagName("personaggio");
			for (int i = 0; i < personaggi.getLength(); i++)
			{
				Node nNode = personaggi.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE)
				{
					
					Element personaggio = (Element) nNode;
					Personaggi.personaggio.add(new PersonaggioGenerico(Integer.parseInt(personaggio.getAttribute("y")), Integer
							.parseInt(personaggio.getAttribute("x")), personaggio.getTextContent(), Integer
							.parseInt(personaggio.getAttribute("squadra")), i));
				}
			}
			
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return mappa;
	}
	
	public static QuadratoMappa[][] caricaQuadrati(String NomeMappa) throws SlickException {
		String[][] mappa = creaMatriceMappa(NomeMappa);
		
		quadratoMappa = new QuadratoMappa[Config.RIGHE][Config.COLONNE];
		for (int i = 0; i < Config.RIGHE; i++)
		{
			for (int j = 0; j < Config.COLONNE; j++)
			{
				quadratoMappa[i][j] = new QuadratoMappa(mappa[i][j], j, i);
			}
		}
		return quadratoMappa;
		
	}
}
