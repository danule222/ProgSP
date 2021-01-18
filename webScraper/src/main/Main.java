package main;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import controller.Config;
import controller.Escritor;

/**
 * Clase principal.
 * @author Daniel Ramírez Morilla
 */
public class Main {

	public static void main(String[] args) throws IOException {
		
		final String url = "https://www.bolsamadrid.es/esp/aspx/"
				+ "Mercados/Precios.aspx?indice=ESI100000000&punto=indice";
		Config.leerConfiguracion();
		final String nombreTXT = Config.nombreFichero;
		final int tiempoRefresco = Config.refresco;
		
		Escritor.setNombreFichero(nombreTXT);
		
		while (true) {
			
			// Obtener datos Web.
			Document doc = Jsoup.connect(url).get();
			Elements cabeTabla = doc.select("#ctl00_Contenido_tblÍndice th");
			Elements contTabla = doc.select("#ctl00_Contenido_tblÍndice td");
			
			// Escribir cabecera.
			if (!Escritor.existe()) {	
				for (Element cabe : cabeTabla) {
					System.out.print(cabe.text() + ";");
					Escritor.escribir(cabe.text() + ";");
				}
				System.out.println();
				Escritor.escribir("\n");
			}
			
			// Escribir contenido de tabla.
			for (Element cont : contTabla) {
				System.out.print(cont.text() + ";");
				Escritor.escribir(cont.text() + ";");
			}
			System.out.println();
			Escritor.escribir("\n");
			
			// Esperar tiempo de refresco.
			try {
				Thread.sleep(tiempoRefresco);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
