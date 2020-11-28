package app;

import java.util.ArrayList;

import seccioncritica.Cocinero;
import threads.Cliente;

public class Main {

	public static ArrayList<Cocinero> listaCocineros = new ArrayList<>();
	public static ArrayList<Cliente> colaClientes = new ArrayList<>();
	
	public static void main(String[] args) {
		
		final int COCINEROS = 3;
		final int CLIENTES = 10;

		
		for (int i = 1; i <= COCINEROS; i++) {
			listaCocineros.add(new Cocinero(i));
		}
		for (int i = 1; i <= CLIENTES; i++) {
			colaClientes.add(new Cliente(i));
		}
		for (Cliente cliente : colaClientes) {
			cliente.start();
		}
		
	}

}
