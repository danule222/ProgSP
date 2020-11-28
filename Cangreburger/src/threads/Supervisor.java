package threads;

import app.Main;
import seccioncritica.Cocinero;

/**
 * <img src="{@docRoot}/../imgs/donCangrejo.png" /></br>
 * Los mandamases. Encargados de comprobar que cada cocinero está
 * trabajando continuamente.
 * @author Daniel Ramírez Morilla
 */
public class Supervisor extends Thread {

	int id;
	
	/**
	 * <img src="{@docRoot}/../imgs/donCangrejo.png" /></br>
	 * Constructor de la calse Supervisor.
	 */
	public Supervisor () {
		id = 0;
	}
	
	public void run() {
		while(true) {
			for (Cocinero cocinero : Main.listaCocineros) {
				if (cocinero.getGenteEnCola() < 1) {
					cocinero.sumGenteEnCola();
					cocinero.annadirTray();
				}
			}
		}
	}
	
}
