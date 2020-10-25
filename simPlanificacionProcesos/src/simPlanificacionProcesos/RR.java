package simPlanificacionProcesos;

import java.util.ArrayList;

/**
 * Simulador de Round Robin.
 * @author Daniel Ramírez Morilla
 */
public class RR {

	static ArrayList<Integer> cola = new ArrayList<Integer>();
	
	static int lineaTemporal = 0;
	static int ciclos = 0;
	static int tiempoEjecucion = 0;
	static int ultimoElementoCola = 0;
	static int ultimaPosicion = 0;
	
	/**
	 * Ejecuta una simulación del algoritmo RR (Round Robin).
	 * @param listaProcesos ArrayList con los procesos que usará el simulador.
	 * @param quantum El Quantum que utilizará el simulador.
	 * @throws InterruptedException
	 */
	public static void run(ArrayList<Proceso> listaProcesos, int quantum) throws InterruptedException {
		try {
			
			System.out.println("- RR Q=" + quantum + " -");
			int h = 0;
			cola.add(0);
			
			for (Proceso proceso : listaProcesos) {
				ciclos += proceso.getTiempoEjecucion();
			}
			
			do {
				for (int j = 0; j < quantum; j++) {
					/*
					 * IMPRIME POR PANTALLA LA LETRA DEL PROCESO Y EL TIEMPO RESTANTE.
					 */
					System.out.print("Proceso " + listaProcesos.get(cola.get(0)).getLetraProceso() +
							" - Tiempo restante: " + (listaProcesos.get(cola.get(0)).getTiempoEjecucion() - 1));
					tiempoEjecucion = listaProcesos.get(cola.get(0)).getTiempoEjecucion();
					/*
					 * REDUCE EN 1 EL TIEMPO DE EJECUCIÓN DEL PROCESO.
					 */
					listaProcesos.get(cola.get(0)).setTiempoEjecucion(tiempoEjecucion - 1);
					lineaTemporal++;
					listaProcesos.get(cola.get(0)).setUltimaPosicion(lineaTemporal);
					/**
					 * SI EL TIEMPO DE EJECUCIÓN ES 0, ESTE IMPRIMIRÁ QUE HA TERMINADO.
					 */
					if (listaProcesos.get(cola.get(0)).getTiempoEjecucion() == 0) {
						System.out.print(" - Finalizado");
						listaProcesos.get(cola.get(0)).setTerminado(true);
						System.out.println();
						Thread.sleep(500);
						break;
					}
					System.out.println();
					Thread.sleep(500);
				}
				
				/**
				 * COLA - ORGANIZA LOS PROCESOS.
				 */
				ultimoElementoCola = cola.get(0);
				ultimaPosicion = listaProcesos.get(cola.get(0)).getUltimaPosicion();
				cola.clear();
				h = 0;
				for (Proceso proceso : listaProcesos) {
					
					if (!proceso.getTerminado() && 
							proceso != listaProcesos.get(ultimoElementoCola) &&
							proceso.getTiempoLlegada() <= lineaTemporal) {
						
							if (proceso.getUltimaPosicion() < ultimaPosicion) {
								cola.add(0, h);
								ultimaPosicion = proceso.getUltimaPosicion();
							}
					}
					h++;
				}
				if (cola.isEmpty()) cola.add(ultimoElementoCola);
				
			} while (lineaTemporal < ciclos);
			
			IndicePenalizacion.calcularIP(listaProcesos, "RR");
			
		} catch (IndexOutOfBoundsException e) {
			System.out.println("\nHay un problema en el listado de procesos.");
		}
		
	}
	
}