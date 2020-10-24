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
	
	public static int getOpcion() {
		return opcion;
	}
	
	public static ArrayList<Proceso> leerProcesos() {
		listaProcesos.clear();
		try {
		      File fichero = new File("procesos.txt");
		      Scanner myReader = new Scanner(fichero);
		      while (myReader.hasNextLine()) {
		        String linea = myReader.nextLine();
		        String columna[];
		        columna = linea.split(";");
		        listaProcesos.add(new Proceso(columna[0].charAt(0),
		        		Integer.parseInt(columna[1]),
		        		Integer.parseInt(columna[2])));
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("No se pudo leer el archivo.");
		      e.printStackTrace();
		    } catch (NumberFormatException e) {
				System.out.println("Por favor, compruebe que los datos son\n"
						+ "correctos en procesos.txt.\n");
			}
		listaProcesos.sort((o1, o2) -> Integer.compare(o1.getTiempoLlegada(),
				o2.getTiempoLlegada()));
		return listaProcesos;
	}
	
}
