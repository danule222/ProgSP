package controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Logger {

	/**
	 * Guarda el aviso establecido en un archivo
	 * en la carpeta "logs" cuyo nombre está formado
	 * por la fecha en la que se ejecuta este método
	 * y la extensión .txt.
	 * @param log Aviso a guardar en el registro.
	 */
	public static void log(String log) {
		FileWriter myWriter;
		File directorio = new File("logs");
		File archivo = new File("logs/" + LocalDate.now() + ".txt");
		
		if (!directorio.exists()) {
			directorio.mkdir();
		}
		if (!archivo.exists())
			try {
				archivo.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		try {
			myWriter = new FileWriter(archivo, true);
			myWriter.write(LocalDateTime.now() + " - " + log + "\n");
			myWriter.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}
