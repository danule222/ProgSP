package Aplicacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

	public static void main(String[] args){

		int opcion = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner scanner = new Scanner(System.in);
		String nombreCarpeta;
		String nombreRuta;
		String interfaz;
		
		do {
			
			Controller.imprimirMenu();
			
			try {
				opcion = scanner.nextInt();
			} catch (Exception e) {
				scanner.nextLine();
			}
			
			System.out.println();
			
			switch (opcion){
			/* CREACIÓN DE CARPETA */
			case 1:
				System.out.println("-- CREAR CARPETA --");
				System.out.println();
				System.out.print("Ruta de la carpeta: ");
				try {
					nombreRuta = br.readLine();
				} catch (IOException e) {
					System.out.println("Por favor, introduzca carácteres válidos.");
					break;
				}
				System.out.print("Nombre de la carpeta: ");
				try {
					nombreCarpeta = br.readLine();
				} catch (IOException e) {
					System.out.println("Por favor, introduzca carácteres válidos.");
					break;
				}
				nombreRuta = nombreRuta.replace("/", File.separator);
				Controller.crearCarpeta(nombreRuta, nombreCarpeta);
				break;
			/* CREACIÓN DE FICHERO */
			case 2:
				System.out.println("-- CREAR FICHERO --");
				System.out.println();
				System.out.print("Ruta del fichero: ");
				try {
					nombreRuta = br.readLine();
				} catch (IOException e) {
					System.out.println("Por favor, introduzca carácteres válidos.");
					break;
				}
				System.out.print("Nombre del fichero: ");
				try {
					nombreCarpeta = br.readLine();
				} catch (IOException e) {
					System.out.println("Por favor, introduzca carácteres válidos.");
					break;
				}
				nombreRuta = nombreRuta.replace("/", File.separator);
				Controller.crearFichero(nombreRuta, nombreCarpeta);
				break;
			/* LISTADO DE INTERFACES */
			case 3:
				System.out.println("-- LISTA DE INTERFACES --");
				System.out.println();
				Controller.listarInterfaces();
				break;
			/* IP DE INTERFAZ */
			case 4:
				System.out.println("-- IP DE INTERFAZ --");
				System.out.println();
				System.out.print("Nombre de la interfaz: ");
				try {
					interfaz = br.readLine();
				} catch (IOException e) {
					System.out.println("Por favor, introduzca carácteres válidos.");
					break;
				}
				Controller.IPInterfaz(interfaz);
				break;
			/* MAC DE INTERFAZ */
			case 5:
				System.out.println("-- MAC DE INTERFAZ --");
				System.out.println();
				System.out.print("Nombre de la interfaz: ");
				try {
					interfaz = br.readLine();
				} catch (IOException e) {
					System.out.println("Por favor, introduzca carácteres válidos.");
					break;
				}
				Controller.MACInterfaz(interfaz);
				break;
			/* COMPROBAR CONEXIÓN A INTERNET */
			case 6:
				System.out.println("-- COMPROBAR CONEXIÓN A INTERNET --");
				System.out.println();
				if (Controller.conexionInternet()) 
					System.out.println("Se ha establecido conexión con 8.8.8.8");
				else System.out.println("No se ha podido establecer conexión con 8.8.8.8");
				break;
			/* TERMINAR APLICACIÓN */
			case 7:
				System.out.println("Bye!");
				break;
			/* EN CASO DE QUE LA OPCIÓN INTRODUCIDA NO SEA VÁLIDA */	
			default:
				System.out.println("Por favor, introduzca una opción válida [1-7]");
				break;
			}
			
			System.out.println();
		} while (opcion != 7);

		scanner.close();
		
	}

}
