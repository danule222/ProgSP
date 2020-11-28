package seccioncritica;

import java.util.Random;

/**
 * <img src="{@docRoot}/../imgs/cocinero.png" /><br/>
 * Cocinero encargado de hacer las amburguesas. Si está
 * trabajando y llega un cliente, este esperará en cola hasta
 * que termine lo que esté haciendo.
 * @author Daniel Ramírez Morilla
 */
public class Cocinero {

	int id;
	boolean trabajando;
	int genteEnCola;
	static int trayHamburguesas;
	/**
	 * <img src="{@docRoot}/../imgs/cangreBurger.png" /><br/>
	 * Hamburguesas cocinadas en total.
	 */
	public static int hamburguesasCocinadas;

	/**
	 * <img src="{@docRoot}/../imgs/cocinero.png" /><br/>
	 * Constructor de la clase Cocinero.
	 * @param id ID con el que trabajará el cocinero.
	 */
	public Cocinero(int id) {
		this.id = id;
		genteEnCola = 0;
		trabajando = false;
	}
	
	/**
	 * Hacer una consumición. Si el cocinero está trabajando
	 * en el momento en el que el cliente desea hacer el pedido,
	 * este tendrá que esperar a que el cocinero termine. Si hay
	 * más clientes en cola, se posicionará en última posición.
	 * @param idCliente ID del cliente para el que el cocinero
	 * trabajará.
	 */
	public synchronized void consumir(int idCliente) {
		System.out.println("COLA C" + id + ": " + genteEnCola);
		while (trabajando) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (trayHamburguesas < 1) {
			trabajando = true;
			System.out.println("ATENDIDO - " + idCliente + 
					" siendo servido por " + id);
			try {
				Random r = new Random();
				int low = 1000;
				int high = 3000;
				int result = r.nextInt(high - low) + low;
				Thread.sleep(result);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("SERVIDO -  " + idCliente + 
					" servido por " + id);
			trabajando = false;
			hamburguesasCocinadas++;
		} else {
			trayHamburguesas--;
			System.out.println("SUB - Hamburguesas en la bandeja: " + 
					trayHamburguesas);
		}
		genteEnCola--;
		notify();
	}
	
	/**
	 * Ordena al cocinero cocinar una hamburguesa para añadirla
	 * a la bandeja común.
	 */
	public synchronized void annadirTray() {
		trabajando = true;
		System.out.println("Cocinero " + id + 
				" añadiendo hamburguesa a la bandeja.");
		try {
			Random r = new Random();
			int low = 1000;
			int high = 3000;
			int result = r.nextInt(high - low) + low;
			Thread.sleep(result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		trabajando = false;
		hamburguesasCocinadas++;
		trayHamburguesas++;
		System.out.println("ADD - Hamburguesas en la bandeja: " + 
				trayHamburguesas);
		genteEnCola--;
	}
	
	/** Suma una persona a la cola del cocinero. */
	public void sumGenteEnCola() {
		genteEnCola++;
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
	
}
