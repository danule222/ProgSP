package controllers;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Controlador de la lectura de configuraciones
 * externas.
 * @author Daniel Ramírez Morilla
 */
public class Config {
	
	private static String direccionServidor;
	private static String puertoServidor;

	/**
	 * Lee la configuración del archivo App.config.
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 */
	public static void leerConfiguracion() 
			throws SAXException, IOException, ParserConfigurationException {
		File fXmlFile = new File("App.config");
	    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = dBuilder.parse(fXmlFile);

	    doc.getDocumentElement().normalize();

	    NodeList nList = doc.getElementsByTagName("AppConfig");

	    for (int temp = 0; temp < nList.getLength(); temp++) {

	        Node nNode = nList.item(temp);
	                
	        if (nNode.getNodeType() == Node.ELEMENT_NODE) {

	            Element eElement = (Element) nNode;

	            direccionServidor = eElement.getElementsByTagName
	            		("DireccionServidor").item(0).getTextContent();
	            puertoServidor = eElement.getElementsByTagName
	            		("PuertoServidor").item(0).getTextContent();
	        }
	    }
	}

	/**
	 * Obtiene el valor en la etiqueta &lt;DireccionServidor&gt;
	 * @return Dirección del servidor.
	 */
	public static String getDireccionServidor() {
		return direccionServidor;
	}

	/**
	 * Obtiene el valor en la etiqueta &lt;PuertoServidor&gt;
	 * @return Puerto en el que se encuentra el servidor.
	 */
	public static int getPuertoServidor() {
		return Integer.parseInt(puertoServidor);
	}
	
}
