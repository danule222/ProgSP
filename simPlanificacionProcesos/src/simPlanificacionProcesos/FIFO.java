package simPlanificacionProcesos;

import java.util.ArrayList;

/**
 * Simulador de FIFO.
 * @author Daniel Ramírez Morilla
 */
public class FIFO {

	/**
	 * Ejecuta una simulación del algoritmo FIFO (First In First Out).
	 * @param listaProcesos ArrayList con los procesos que usará el simulador.
	 * @throws InterruptedException
	 */
	public static void run(ArrayList<Proceso> listaProcesos) throws InterruptedException {
		System.out.println("- FIFO -");
		
		int lineaTemporal = 0;
		
		for (int i = 0; i < listaProcesos.size(); i++) {
			
			for (int j = 0; j < listaProcesos.get(i).getTiempoEjecucion(); j++) {
				System.out.print("Proceso " + listaProcesos.get(i).getLetraProceso() +
						" - Tiempo restante: ");
				
				if ((listaProcesos.get(i).getTiempoEjecucion() - j - 1) == 0)
					System.out.print(listaProcesos.get(i).getTiempoEjecucion() - j - 1 +
							" - Finalizado");
				
				else System.out.print(listaProcesos.get(i).getTiempoEjecucion() - j - 1);
				System.out.println();
				lineaTemporal++;
				listaProcesos.get(i).setUltimaPosicion(lineaTemporal);
				
				Thread.sleep(500);
			}
			
		}
		IndicePenalizacion.calcularIP(listaProcesos, "FIFO");
	}
	
}
