package controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Logger {

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
