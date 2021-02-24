package controllers;

import java.io.IOException;

import main.Main;

/**
 * Controlador de las operaciones relacionadas
 * con la caja.
 * @author Daniel Ramírez Morilla
 */
public class Caja {

	/**
	 * Obtener caja del día.
	 * @throws IOException
	 */
	public static void cajaDelDia() throws IOException {
		Main.flujoSalida.writeUTF("Caja");
		System.out.println("Caja de hoy: "
						 + Main.flujoEntrada.readDouble());
	}
	
}
