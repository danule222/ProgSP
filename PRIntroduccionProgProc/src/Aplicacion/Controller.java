package Aplicacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Controller {
	
	static ProcessBuilder pB = new ProcessBuilder();
	static String IPv4[];
	static String mac[];
	static String paquetes[];
	static boolean conexion;
	
	/**
	 * Al pasarle una ruta del sistema de archivos del equipo, crea
	 * una carpeta con el nombre indicado en la ruta indicada.
	 * @param ruta Direcci�n donde se quiere crear la carpeta. Si no se escribe el
	 * separador de carpeta al final, el m�todo lo detectar� y lo escribir� autom�ticamente.
	 * @param nombre Nombre de la carpeta que se quiere crear.
	 */
	public static void crearCarpeta(String ruta, String nombre) {
		if (!ruta.substring(ruta.length() - 1).equals("/")) 
			ruta += File.separator;
		String comando = "mkdir " + ruta + nombre;
		pB.command("cmd.exe", "/c", comando);
		try {
			pB.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Al pasarle una ruta del sistema de archivos del equipo, crea
	 * un fichero con el nombre indicado en la ruta indicada.
	 * @param ruta Direcci�n donde se quiere crear el fichero. Si no se escribe el
	 * separador de carpeta al final, el m�todo lo detectar� y lo escribir� autom�ticamente.
	 * @param nombre Nombre del fichero que se quiere crear.
	 */
	public static void crearFichero(String ruta, String nombre) {
		if (!ruta.substring(ruta.length() - 1).equals("/")) 
			ruta += File.separator;
		String comando = "echo > " + ruta + nombre;
		pB.command("cmd.exe", "/c", comando);
		try {
			System.out.println(comando);
			pB.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Filtra el nombre de las interfaces devueltas por el sistema
	 * y las imprime en pantalla.
	 */
	public static void listarInterfaces() {
		try {
			pB.command("cmd.exe", "/c", "ipconfig");
			Process proceso = pB.start();
			StringBuilder buffer = new StringBuilder();
			BufferedReader lector = new BufferedReader(
					new InputStreamReader(proceso.getInputStream(),
							Charset.forName("CP850")));
			String linea;
			
			while ((linea = lector.readLine()) != null) {
				if (!linea.startsWith(" ") && !linea.isBlank() &&
						!linea.startsWith("Configuraci�n IP de Windows")){
					linea = linea.substring(0, linea.length() - 1);
					buffer.append(linea + "\n");
				}
			}

			if (proceso.waitFor() == 0) {
				System.out.println(buffer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Filtra las interfaces de red con el nombre introducido y muestra
	 * su direcci�n IPv4 si esta est� disponible.
	 * @param interfaz Nombre de la interfaz cuya IP queremos ver.
	 */
	public static void IPInterfaz(String interfaz) {
		try {
			pB.command("cmd.exe", "/c", "ipconfig");
			Process proceso = pB.start();
			StringBuilder buffer = new StringBuilder();
			BufferedReader lector = new BufferedReader(
					new InputStreamReader(proceso.getInputStream(),
							Charset.forName("CP850")));
			String linea;
			boolean entrar = false;
			
			while ((linea = lector.readLine()) != null) {
				if (linea.contains(interfaz)){
					buffer.append(linea + " ");
					entrar = true;
				}
				if (entrar && linea.contains("IPv4")) {
					IPv4 = linea.split(": ");
					buffer.append(IPv4[1] + "\n");
					entrar = false;
				}
			}

			if (proceso.waitFor() == 0) {
				System.out.println(buffer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Filtra las interfaces de red con el nombre introducido y muestra
	 * su direcci�n f�sica (MAC) si esta est� disponible.
	 * @param interfaz Nombre de la interfaz cuya MAC queremos ver.
	 */
	public static void MACInterfaz(String interfaz) {
		try {
			pB.command("cmd.exe", "/c", "ipconfig /all");
			Process proceso = pB.start();
			StringBuilder buffer = new StringBuilder();
			BufferedReader lector = new BufferedReader(
					new InputStreamReader(proceso.getInputStream(),
							Charset.forName("CP850")));
			String linea;
			boolean entrar = false;
			
			while ((linea = lector.readLine()) != null) {
				if (linea.contains(interfaz) && !linea.contains("Descripci�n")){
					buffer.append(linea + " ");
					entrar = true;
				}
				if (entrar && linea.contains("Direcci�n f�sica")) {
					mac = linea.split(": ");
					buffer.append(mac[1] + "\n");
					entrar = false;
				}
			}

			if (proceso.waitFor() == 0) {
				System.out.println(buffer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Comprueba que el sistema tiene conexi�n a Internet
	 * @return El valor devuelto ser� verdadero si se ha conseguido
	 * establecer conexi�n con Internet. Si no ha sido posible, el valor
	 * devuelto ser� falso.
	 */
	public static boolean conexionInternet() {
		
		conexion = false;
		String comando = "ping 8.8.8.8 -n 1";
		pB.command("cmd.exe", "/c", comando);
		try {
			Process proceso = pB.start();
			BufferedReader lector = new BufferedReader(
					new InputStreamReader(proceso.getInputStream(),
							Charset.forName("CP850")));
			String linea;
			
			while ((linea = lector.readLine()) != null) {
				/*
				 * Debido a que Windows cambi� su forma de responder a paquetes
				 * perdidos, recibiendo respuesta desde localhost y haciendo que
				 * muestro 0% de paquetes perdidos, el m�todo comentado
				 * a continuaci�n no es fiable.
				 */
				
				/*if (linea.contains("Paquetes")){
					paquetes = linea.split(",");
					if (paquetes[2].contains("0")) {
						conexion = true;
					}
				}*/
				
				if (linea.contains("Tiempos aproximados")){
					conexion = true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return conexion;
	}
	
	/**
	 * Imprime el men� principal de la aplicaci�n por pantalla.
	 */
	public static void imprimirMenu() {
		System.out.println("-- MEN� PRINCIPAL --");
		System.out.println();
		System.out.println(" 1 � Crear carpeta.");
		System.out.println(" 2 � Crear fichero.");
		System.out.println(" 3 � Mostrar interfaces de red.");
		System.out.println(" 4 � Mostrar IP de interfaz de red.");
		System.out.println(" 5 � Mostrar MAC de interfaz de red.");
		System.out.println(" 6 � Comprobar conexi�n a Internet.");
		System.out.println(" 7 � Salir.");
		System.out.println();
		System.out.print("Opci�n: ");
	}
	
}
