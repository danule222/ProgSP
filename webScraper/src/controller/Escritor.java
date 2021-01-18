package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase Escritor, encargada de escribir
 * datos en un fichero.
 * @author Daniel Ramírez Morilla
 */
public class Escritor {
	
	static String nombreFichero;
	
	/**
	 * Escribe la linea dada en el fichero con el nombre
	 * asignado anteriormente.
	 * @param linea
	 * @throws IOException
	 */
	public static void escribir(String linea) 
			throws IOException {
		FileWriter writer = new FileWriter(nombreFichero, true);
		writer.write(linea);
		writer.close();
	}
	
	/**
	 * Comprueba si el fichero con el nombre dado anteriormente
	 * existe o no.
	 * @return <ul><li><b>True: </b>El fichero existe.</li><li>
	 * <b>False: </b>El fichero no existe.</li></ul>
	 */
	public static boolean existe() {
		File fichero = new File(nombreFichero);
		return fichero.exists();
	}
	
	/**
	 * Establece el nombre del fichero donde se escribirá
	 * los valores de la tabla del IBEX35.
	 * @param nombre
	 */
	public static void setNombreFichero(String nombre) {
		nombreFichero = nombre;
	}
	
}
