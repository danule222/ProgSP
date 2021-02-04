package main;

import java.io.IOException;

import cliente.Cliente;
import controller.Controller;
import servidor.Servidor;

/**
 * Clase principal - Menú principal.
 * @author Daniel Ramírez Morilla
 */
public class Main {

	public static void main(String[] args) {
		
		int opcion = 0;
		
		do {
			// MENÚ PRINCIPAL
			System.out.println("- WALKIE TALKIE -\n");
			System.out.println("   1 · Unirse a una sesión.");
			System.out.println("   2 · Crear una sesión.");
			System.out.println("   3 · Ayuda.");
			System.out.println("   4 · Salir.\n");
			System.out.print("Opción: ");
			// SELECCIÓN DE OPCIÓN
			try {
				opcion = Controller.sc.nextInt();
				System.out.print("\n");
			} catch (Exception e) {
				Controller.sc.nextLine();
				opcion = 5;
			}
			
			switch(opcion) {
			// UNIRSE A SESIÓN
			case 1:
				try {
					Cliente.main();
				} catch (IOException e) {
					try {
						Controller.cerrarCliente();
						System.out.println("\nSe ha perdido conexión "
								+ "con el anfitrión.\n");
					} catch (IOException e1) {
						System.out.println("Hubo un error fatal.");
						opcion = 4;
					} catch (NullPointerException e2) {
						System.out.println("\nNo se pudo establecer conexión\n"
								+ "con la dirección de red indicada.\n");
					}
				}
				break;
			// CREAR SESIÓN
			case 2:
				try {
					Servidor.main();
				} catch (IOException e1) {
					System.out.println("\nSe ha perdido conexión "
							+ "con el cliente.\n");
					try {
						Controller.cerrarServidor();
					} catch (IOException e) {
						System.out.println("Hubo un error fatal.");
						opcion = 4;
					}
				}
				break;
			// AYUDA
			case 3:
				System.out.println("- AYUDA -\n");
				System.out.println("1. Unise a una sesión: Introduzca la\n"
						+ "dirección IP de la máquina que haya creado la\n"
						+ "sesión para unirse.");
				System.out.println("2. Crear una sesión: Usted será el\n"
						+ "huésped de la reunión. Deberá dar su dirección\n"
						+ "IP para que un cliente se pueda unir a usted.");
				System.out.println("3. Ayuda: Obtiene esta ayuda.");
				System.out.println("4. Salir: Bastante autodescriptivo.");
				System.out.println("\n- IMPORTANTE -\n");
				System.out.println("Para salir de una conversación debe\n"
						+ "enviar o recibir la frase 'Cambio y corto.'\n");
				break;
			// SALIR
			case 4:
				break;
			default:
				System.out.println("\nPor favor, seleccione "
						+ "una opción válida [1 - 4]\n");
			}
			
		} while (opcion != 4);
		
		// CIERRE DE LAS CLASES SCANNER
		Controller.sc.close();
		Controller.sc2.close();

	}

}
