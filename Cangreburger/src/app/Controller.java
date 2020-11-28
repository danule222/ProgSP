package app;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import seccioncritica.Cocinero;
import threads.Cliente;

/**
 * Controlador.
 * @author Daniel Ramírez Morilla
 */
public class Controller {

	/**
	 * Escribe en un TXT "estadísticas.txt" todos los datos
	 * sobre el número de cocineros y clientes y las veces que
	 * ha sido servido cada cliente acompañado de una fecha.
	 */
	public static void escribirEstadísticas() {
		try {
			FileWriter escritor = new FileWriter("estadísticas.txt", true);
			
			escritor.write(estadísticasToString());
			escritor.close();
			System.out.println("LOG - estadísticas.txt actualizado.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Genera un String con los siguientes datos:<br/>
	 *  · Fecha y hora.<br/>
	 *  · Número de cocineros.<br/>
	 *  · Número de clientes.<br/>
	 *  · Hamburguesas cocinadas en total.<br/>
	 *  · Lista con las veces que ha sido atendido cada cliente.
	 *  · Media de servicios.
	 * @return String con todos los datos nombrados.
	 */
	public static String estadísticasToString() {
		String r = "";
		int suma = 0;
		r += LocalDateTime.now().toString() + "\n";
		r += "Número de cocineros: " + Main.COCINEROS + "\n";
		r += "Número de clientes: " + Main.CLIENTES + "\n";
		r += "Hamburguesas cocinadas en total: " + 
				Cocinero.hamburguesasCocinadas + "\n";
		r += "VECES ATENDIDAS\n";
		for (Cliente c : Main.colaClientes) {
			r += "	Cliente " + c.getID() + ": " + 
					c.getVecesServido();
			if (c.getVecesServido() == 1)
				r += " vez.\n";
			else r+= " veces.\n";
			suma += c.getVecesServido();
		}
		r += "MEDIA: " + suma / Main.CLIENTES + "\n";
		r += "\n";
		
		return r;
	}
	
}
