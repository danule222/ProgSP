package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase Config encargada de leer y establecer
 * la configuración de la aplicación.
 * @author Daniel Ramírez Morilla
 */
public class Config {

	/** Nombre que tendrá el fichero de salida. */
	public static String nombreFichero;
	/** 
	 * Tiempo que esperará el programa para volver a
	 * pedir información a la página web e imprimirla.
	 */
	public static int refresco;
	
	/**
	 * Lee el archivo de configuración "config.txt" y asigna
	 * los valores leídos a las variables de la clase Config.
	 */
	public static void leerConfiguracion() {
		try {
			String nombre = "config.txt";
			File fichero = new File(nombre);
			FileReader fR = new FileReader(fichero);
			BufferedReader bR = new BufferedReader(fR);
			String linea;
			while ((linea = bR.readLine()) != null) {
				if (linea.contains("Output=")) {
					String[] columnas = linea.split("=");
					nombreFichero = columnas[1];
				} else if (linea.contains("Refresco=")) {
					String[] columnas = linea.split("=");
					refresco = (Integer.parseInt(columnas[1])) * 1000;
				}
			}
			bR.close();
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el archivo de configuración"
					+ " \"config.txt\".");
			e.printStackTrace();
			predeterminados();
		} catch (IOException e) {
			System.out.println("No se ha podido leer el archivo de configuración"
					+ " \"config.txt\".");
			e.printStackTrace();
			predeterminados();
		}

	}
	
	/**
	 * Asigna valores predeterminados a las variables de la clase.
	 */
	static void predeterminados() {
		nombreFichero = "datosBolsa.txt";
		refresco = 60000;
	}
	
}
