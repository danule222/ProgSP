package threads;

import java.util.Random;

import app.Main;
import seccioncritica.Cocinero;

public class Cliente extends Thread{

	int id;
	int comparador;
	int idCocinero;
	
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
			Main.listaCocineros.get(idCocinero-1).sumGenteEnCola();
			Main.listaCocineros.get(idCocinero-1).consumir(id);
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
	
}
