package seccioncritica;

import java.util.Random;

public class Cocinero {

	int id;
	boolean trabajando;
	int genteEnCola;

	public Cocinero(int id) {
		this.id = id;
		genteEnCola = 0;
		trabajando = false;
	}
	
	public synchronized void consumir(int idCliente) {
		System.out.println("COLA C" + id + ": " + genteEnCola);
		while (trabajando) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		trabajando = true;
		genteEnCola--;
		System.out.println("ATENDIDO - " + idCliente + " siendo servido por " + id);
		try {
			Random r = new Random();
			int low = 1000;
			int high = 3000;
			int result = r.nextInt(high-low) + low;
			Thread.sleep(result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("SERVIDO -  " + idCliente + " servido por " + id);
		trabajando = false;
		notify();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public boolean isTrabajando() {
		return trabajando;
	}
	
	public void setTrabajando(boolean trabajando) {
		this.trabajando = trabajando;
	}

	public int getGenteEnCola() {
		return genteEnCola;
	}

	public void setGenteEnCola(int genteEnCola) {
		this.genteEnCola = genteEnCola;
	}
	
	public void sumGenteEnCola() {
		genteEnCola++;
	}
	
}
