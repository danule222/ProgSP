package threads;

import app.Controller;
import app.Main;

/**
 * Clase encargada de ir escribiendo las estadísticas cada
 * tiempo establecido en Main.TIEMPOESCRITOR.
 * @author Daniel Ramírez Morilla
 */
public class Escritor extends Thread {

	public void run() {
		while (true) {
			try {
				Thread.sleep(Main.TIEMPOESCRITOR * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Controller.escribirEstadísticas();
		}
	}
	
}
