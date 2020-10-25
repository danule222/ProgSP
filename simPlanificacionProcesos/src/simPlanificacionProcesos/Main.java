package simPlanificacionProcesos;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase principal desde donde se ejecuta la aplicación.
 */
public class Main {

	/**
	 * Método principal desde donde se ejecuta la aplicación.
	 */
	public static void main(String[] args) throws InterruptedException {
		
		Scanner scanner = new Scanner(System.in);
		int quantum = 1;
		
		ArrayList<Proceso> listaProcesos = Controller.leerProcesos();
		
		do {
			switch (Controller.imprimirMenu()) {
			case 1:
				FIFO.run(listaProcesos);
				break;
			case 2:
				SJF.run(listaProcesos);
				break;
			case 3:
				SRT.run(listaProcesos);
				break;
			case 4:
				System.out.print("Introduza el Quantum a usar: ");
				quantum = scanner.nextInt();
				System.out.println();
				RR.run(listaProcesos, quantum);
				break;
			case 5:
				break;
			case 6:
				break;
			default:
					System.out.println("Por favor, introduzca una opción válida [1 - 5]");
			}
			System.out.println();
			listaProcesos = Controller.leerProcesos();
		} while (Controller.getOpcion() != 6);
		System.out.print("Bye!");
		scanner.close();
	}

}