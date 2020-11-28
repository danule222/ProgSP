package threads;

import java.util.Random;

import app.Main;
import seccioncritica.Cocinero;

/**
 * <img src="{@docRoot}/../imgs/cliente.png" /></br>
 * Animal marino despiadado con la única misión de comer
 * y gastar hasta el último centavo en Cangreburgers.
 * @author Daniel Ramírez Morilla
 */
public class Cliente extends Thread {

	int id;
	int comparador;
	int idCocinero;
	int vecesServido;
	
	/**
	 * <img src="{@docRoot}/../imgs/cliente.png" /></br>
	 * Constructor de la clase Cliente.
	 * @param id
	 */
	public Cliente(int id) {
		this.id = id;
	}
	
	public void run() {
		while(true) {
			comparador = Main.listaCocineros.get(0).getGenteEnCola();
			for (Cocinero cocinero : Main.listaCocineros) {
				if (cocinero.getGenteEnCola() <= comparador)
					idCocinero = cocinero.getId();
			}
			Main.listaCocineros.get(idCocinero).sumGenteEnCola();
			Main.listaCocineros.get(idCocinero).consumir(id);
			vecesServido++;
			try {
				Random r = new Random();
				int low = 1000;
				int high = 3000;
				int result = r.nextInt(high-low) + low;
				Thread.sleep(result);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getID() {
		return id;
	}
	
	public int getVecesServido() {
		return vecesServido;
	}
	
}
