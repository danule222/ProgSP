package controllers;

import java.io.IOException;

import main.Main;

public class Salir {

	/**
	 * Terminar conexión con el servidor
	 * y cerrar la aplicación.
	 * @throws IOException
	 */
	public static void salir() throws IOException {
		Main.flujoSalida.writeUTF("Salir");
	}
	
}
