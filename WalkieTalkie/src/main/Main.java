package main;

import java.io.IOException;
import java.util.Scanner;

import cliente.Cliente;
import servidor.Servidor;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		
		do {
			System.out.println("1 - unirse 2 - crear");
			try {
				opcion = sc.nextInt();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			switch(opcion) {
			// UNIRSE A SESIÓN
			case 1:
				try {
					Cliente.main(args);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			// CREAR SESIÓN
			case 2:
				try {
					Servidor.main(args);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				break;
			case 3:
				break;
			default:
				System.out.println("Por favor, seleccione "
						+ "una opción válida");
			}
			
		} while (opcion != 3);
		
		sc.close();

	}

}
