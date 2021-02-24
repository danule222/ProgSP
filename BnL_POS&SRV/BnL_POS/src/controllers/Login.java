package controllers;

import java.io.IOException;
import java.util.ArrayList;

import main.Main;
import models.Empleado;
import models.Producto;

/**
 * Controlador de las operaciones de
 * inicio de sesión.
 * @author Daniel Ramírez Morilla
 *
 */
public class Login {

	/**
	 * Envía al servidor el mensaje Login junto
	 * al número privado que el empleado ha introducido.
	 * Si el número privado introducido corresponde con
	 * el de un trabajador, se espera recibir un objeto
	 * Empleado con los datos del trabajado; en caso de
	 * que no haya coincidencia, se recibirá un objeto
	 * Empleado vacío.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static void login() 
			throws IOException, ClassNotFoundException {
		int numeroPrivado = 0;
		
		do {
			do {
				
				try {
					System.out.print("Introducir número privado: ");
					numeroPrivado = Main.sc.nextInt();
					System.out.println();
				} catch (Exception e) {
					numeroPrivado = 0;
					Main.sc.nextLine();
					System.out.println("\nPor favor, introduzca un número\n"
								     + "privado válido.\n");
				}
				
			} while (numeroPrivado == 0);
			
			Main.flujoSalida.writeUTF("Login;" + numeroPrivado);
			Main.e = (Empleado) Main.objetoEntrada.readObject();
			if (Main.e.getNombre() == null)
				System.out.println("El número privado introducido no\n"
								 + "corresponde al de ningún empleado.\n");
			
		} while (Main.e.getNombre() == null);
		
		Object lista = Main.objetoEntrada.readObject();
		if (lista instanceof ArrayList<?>) {
			ArrayList<?> al = (ArrayList<?>) lista;
			if (al.size() > 0) {
				for (int i = 0; i < al.size(); i++) {
					Object o = al.get(i);
					if (o instanceof Producto) {
						Producto s = (Producto) o;
						Main.listaProductos.add(s);
					}
				}
			}
		}
	}
	
}
