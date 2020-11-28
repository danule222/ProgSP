package app;

import java.util.ArrayList;

import seccioncritica.Cocinero;
import threads.Cliente;
import threads.Escritor;
import threads.Supervisor;

/**
 * <img src="{@docRoot}/../imgs/crustaceoCrujiente.png" /><br>
 * Crustáceo Crujiente, casa de la Cangreburguer, hamburguesa estrella
 * de Fondo de Bikini.<br/>
 * El él trabajan tanto cocineros, como supervisores, los cuáles
 * esperan a las comandas de sus queridos clientes.
 * @author Daniel Ramírez Morilla
 */
public class Main {

	public static ArrayList<Cocinero> listaCocineros = new ArrayList<>();
	public static ArrayList<Supervisor> listaSupervisores = new ArrayList<>();
	public static ArrayList<Cliente> colaClientes = new ArrayList<>();
	
	/** <img src="{@docRoot}/../imgs/bobEsponja.png" /></br>
	 * Número de cocineros. */
	public static final int COCINEROS = 3;
	/** <img src="{@docRoot}/../imgs/anchoa.png" /><br/>
	 * Número de clientes. */
	public static final int CLIENTES = 30;
	/** Frecuencia con la que se escribe en "estadísticas.txt"
	 * en segundos. */
	public static final int TIEMPOESCRITOR = 30;
	
	public static void main(String[] args) {
		
		for (int i = 0; i < COCINEROS; i++) {
			listaCocineros.add(new Cocinero(i));
			listaSupervisores.add(new Supervisor());
		}
		for (Supervisor s : listaSupervisores) {
			s.start();
		}
		for (int i = 1; i <= CLIENTES; i++) {
			colaClientes.add(new Cliente(i));
		}
		
		Escritor e = new Escritor();
		e.start();
		
		for (Cliente cliente : colaClientes) {
			cliente.start();
		}
		
	}

}
