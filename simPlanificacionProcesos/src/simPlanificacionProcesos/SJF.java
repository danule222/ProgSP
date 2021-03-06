package simPlanificacionProcesos;

import java.util.ArrayList;

/**
 * Simulador de SJF.
 * @author Daniel Ramírez Morilla
 */
public class SJF {
	
	static ArrayList<Integer> cola = new ArrayList<Integer>();
	
	static int lineaTemporal = 0;
	static int ultimoTiempoLlegada = 0;

	/**
	 * Ejecuta una simulación del algoritmo SJF (Shortest Job Next).
	 * @param listaProcesos ArrayList con los procesos que usará el simulador.
	 * @throws InterruptedException
	 */
	public static void run(ArrayList<Proceso> listaProcesos) throws InterruptedException{
		System.out.println("- SJF -");
		cola.add(0);
		
		for (int i = 0; i < listaProcesos.size(); i++) {
			
			for (int j = 0; j < listaProcesos.get(cola.get(0))
					.getTiempoEjecucion(); j++) {
				System.out.print("Proceso " + listaProcesos.get(cola.get(0))
					.getLetraProceso() + " - Tiempo restante: ");
				
				if ((listaProcesos.get(cola.get(0)).getTiempoEjecucion() - j - 1) == 0)
					System.out.print(listaProcesos.get(cola.get(0))
							.getTiempoEjecucion() - j - 1 +
							" - Finalizado");
				
				else System.out.print(listaProcesos.get(cola.get(0))
						.getTiempoEjecucion() - j - 1);
				
				System.out.println();
				lineaTemporal++;
				listaProcesos.get(cola.get(0)).setUltimaPosicion(lineaTemporal);
				
				Thread.sleep(500);
			}
			
			/**
			 * COLA - ORGANIZA LOS PROCESOS.
			 */
			cola.remove(0);
			int h = 0;
			for (Proceso proceso : listaProcesos) {
				
				if (proceso.getTiempoLlegada() <= lineaTemporal &&
						proceso.getTiempoLlegada() > ultimoTiempoLlegada) {
					
					if (proceso.getTiempoEjecucion() < 
							listaProcesos.get(h - 1).getTiempoEjecucion()) {
						cola.add(0, h);
					
					} else {
						cola.add(h);
					}
					ultimoTiempoLlegada = proceso.getTiempoLlegada();
				}
				h++;
			}
			
		}
		IndicePenalizacion.calcularIP(listaProcesos, "SJF");
	}
	
}
