package Aplicacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Controller {
	
	static ProcessBuilder pB = new ProcessBuilder();
	static String IPv4[];
	
	/**
	 * Al pasarle una ruta del sistema de archivos del equipo, crea
	 * una carpeta con el nombre indicado en la ruta indicada.
	 * @param ruta Dirección donde se quiere crear la carpeta. Si no se escribe el
	 * separador de carpeta al final, el método lo detectará y lo escribirá automáticamente.
	 * @param nombre Nombre de la carpeta que se quiere crear.
	 */
	public static void crearCarpeta(String ruta, String nombre) {
		if (!ruta.substring(ruta.length() - 1).equals("/")) 
			ruta += File.separator;
		String comando = "mkdir " + ruta + nombre;
		pB.command("cmd.exe", "/c", comando);
		try {
			System.out.println(comando);
			pB.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Al pasarle una ruta del sistema de archivos del equipo, crea
	 * un fichero con el nombre indicado en la ruta indicada.
	 * @param ruta Dirección donde se quiere crear el fichero. Si no se escribe el
	 * separador de carpeta al final, el método lo detectará y lo escribirá automáticamente.
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
						!linea.startsWith("Configuración IP de Windows")){
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
					linea = linea.substring(0, linea.length() - 1);
					buffer.append(linea + ": ");
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
	
}
