package simPlanificacionProcesos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Controlador.
 * @author drami
 */
public class Controller {

	static Scanner scanner = new Scanner(System.in);
	static int opcion;
	static ArrayList<Proceso> listaProcesos = new ArrayList<Proceso>();
	
	/**
	 * Imprime el menú principal de la aplicación y pide al usuario
	 * que eliga una de las opciones disponibles.
	 * @return Si la opción elegida por el usuario es un integral,
	 * se devolverá el valor introducido por este. Si este no es el caso,
	 * el método sustituirá el valor introducido por un 7.
	 */
	public static int imprimirMenu() {
		System.out.println("- MENÚ PRINCIPAL -");
		System.out.println("Elije un simulador:");
		System.out.println(" 1 · FIFO");
		System.out.println(" 2 · SJF");
		System.out.println(" 3 · SRT");
		System.out.println(" 4 · RR");
		System.out.println(" -");
		System.out.println(" 5 · Recargar archivo procesos.txt");
		System.out.println(" 6 · Salir");
		
		System.out.print("Opcion: ");
		try {
			opcion = scanner.nextInt();
		} catch (Exception e) {
			scanner.nextLine();
			opcion = 7;
		}
		System.out.println();
		return opcion;
	}
	
	/**
	 * Devuelve la última opción introducida por el usuario.
	 * @return Integral con la última opción introducida por el usuario.
	 */
	public static int getOpcion() {
		return opcion;
	}
	
	/**
	 * Lee los procesos desde un archivo de texto <b>procesos.txt</b> el cual
	 * contiene los siguientes datos:
	 * <table>
	 * 	<tr><th>Letra del proceso</th><th>Tiempo de llegada</th><th>Tiempo de ejecución</th></tr>
	 * 	<tr align="center"><td>A</td><td>0</td><td>3</td></tr>
	 * </table>
	 * En el siguiente formato: <b>A;0;3</b>
	 * @return ArrayList de la clase Proceso con los datos de los procesos leídos del
	 * archivo <b>procesos.txt</b>. Los procesos en la ArrayList están ordenados en
	 * orden de llegada.
	 */
	public static ArrayList<Proceso> leerProcesos() {
		listaProcesos.clear();
		try {
		      File fichero = new File("procesos.txt");
		      Scanner myReader = new Scanner(fichero);
		      
		      while (myReader.hasNextLine()) {
		        String linea = myReader.nextLine();
		        
		        if (!linea.contains("//")) {
		        	String columna[];
		        	columna = linea.split(";");
		        	listaProcesos.add(new Proceso(columna[0].charAt(0),
		        			Integer.parseInt(columna[1]),
		        			Integer.parseInt(columna[2])));
		        }
		        
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("No se pudo leer el archivo.");
		      e.printStackTrace();
		    } catch (NumberFormatException e) {
				System.out.println("Por favor, compruebe que los datos son\n"
						+ "correctos en procesos.txt.\n");
			}
		// ORDENA LOS PROCESOS POR ORDEN DE LLEGADA
		listaProcesos.sort((o1, o2) -> Integer.compare(o1.getTiempoLlegada(),
				o2.getTiempoLlegada()));
		return listaProcesos;
	}
	
}
