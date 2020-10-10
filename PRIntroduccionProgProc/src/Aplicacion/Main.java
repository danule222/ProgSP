package Aplicacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {

		int opcion;
		BufferedReader br = new BufferedReader(new
		        InputStreamReader(System.in));
		Scanner scanner = new Scanner(System.in);
		String nombreCarpeta;
		String nombreRuta;
		String interfaz;
		
		do {
			
			opcion = scanner.nextInt();
			
			switch (opcion){
			case 1:
				System.out.println("-- CREAR CARPETA --");
				System.out.print("Ruta de la carpeta: ");
				nombreRuta = br.readLine();
				System.out.print("Nombre de la carpeta: ");
				nombreCarpeta = br.readLine();
				nombreRuta = nombreRuta.replace("/", File.separator);
				Controller.crearCarpeta(nombreRuta, nombreCarpeta);
				break;
			case 2:
				System.out.println("-- CREAR FICHERO --");
				System.out.print("Ruta del fichero: ");
				nombreRuta = br.readLine();
				System.out.print("Nombre del fichero: ");
				nombreCarpeta = br.readLine();
				nombreRuta = nombreRuta.replace("/", File.separator);
				Controller.crearFichero(nombreRuta, nombreCarpeta);
				break;
			case 3:
				System.out.println("-- LISTA DE INTERFACES --");
				Controller.listarInterfaces();
				break;
			case 4:
				System.out.println("-- IP DE INTERFAZ --");
				System.out.print("Nombre de la interfaz: ");
				interfaz = br.readLine();
				Controller.IPInterfaz(interfaz);
				break;
			case 9:
				System.out.println("adeu amadeu");
				break;
			}
			
		} while (opcion != 9);

		scanner.close();
		
	}

}
