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
	
	private static String emailEmergencia;
	private static String mailer;
	private static String contrasennaMailer;
	private static String servidorBDD;
	private static String BDD;
	private static String usuarioBDD;
	private static String contrasennaBDD;
	private static int puerto;

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

	            emailEmergencia = eElement.getElementsByTagName
	            		("EmailEmergencia").item(0).getTextContent();
	            mailer = eElement.getElementsByTagName
	            		("Mailer").item(0).getTextContent();
	            contrasennaMailer = eElement.getElementsByTagName
	            		("ContrasennaMailer").item(0).getTextContent();
	            servidorBDD = eElement.getElementsByTagName
	            		("ServidorBDD").item(0).getTextContent();
	            BDD = eElement.getElementsByTagName
	            		("BDD").item(0).getTextContent();
	            usuarioBDD = eElement.getElementsByTagName
	            		("UsuarioBDD").item(0).getTextContent();
	            contrasennaBDD = eElement.getElementsByTagName
	            		("ContrasennaBDD").item(0).getTextContent();
	            puerto = Integer.parseInt(eElement.getElementsByTagName
	            		("Puerto").item(0).getTextContent());
	        }
	    }
	}
	
	public static String getStringConexion() {
		return "jdbc:mysql://" + servidorBDD + "/" + BDD
	            + "?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false"
	            + "&serverTimezone=Europe/Madrid";
	}

	/**
	 * Obtiene el valor en la etiqueta &lt;EmailEmergencia&gt;
	 * @return Dirección del email de emergencia.
	 */
	static String getEmailEmergencia() {
		return emailEmergencia;
	}

	/**
	 * Obtiene el valor en la etiqueta &lt;Mailer&gt;
	 * @return Dirección del email de la cuenta encargada
	 * de mandar los correos electrónicos.
	 */
	static String getMailer() {
		return mailer;
	}

	/**
	 * Obtiene el valor en la etiqueta &lt;ContrasennaMailer&gt;
	 * @return Contraseña de acceso a la cuenta del mailer.
	 */
	static String getContrasennaMailer() {
		return contrasennaMailer;
	}
	
	/**
	 * Obtiene el valor en la etiqueta &lt;ServidorBDD&gt;
	 * @return Dirección del servidor de la base de datos.
	 */
	public static String getServidorBDD() {
		return servidorBDD;
	}

	/**
	 * Obtiene el valor en la etiqueta &lt;BDD&gt;
	 * @return Nombre del esquema de la base de datos.
	 */
	public static String getBDD() {
		return BDD;
	}

	/**
	 * Obtiene el valor en la etiqueta &lt;UsuarioBDD&gt;
	 * @return Usuario de la base de datos.
	 */
	public static String getUsuarioBDD() {
		return usuarioBDD;
	}

	/**
	 * Obtiene el valor en la etiqueta &lt;ContrasennaBDD&gt;
	 * @return Contraseña del usuario de la base de datos.
	 */
	public static String getContrasennaBDD() {
		return contrasennaBDD;
	}
	
	/**
	 * Obtiene el valor en la etiqueta &lt;Puerto&gt;
	 * @return Puerto usado por el servidor.
	 */
	public static int getPuerto() {
		return puerto;
	}
	
}
