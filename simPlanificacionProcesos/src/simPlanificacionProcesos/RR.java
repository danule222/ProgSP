package simPlanificacionProcesos;

import java.util.ArrayList;

public class RR {

	static ArrayList<Integer> cola = new ArrayList<Integer>();
	
	static int lineaTemporal = 0;
	static int ciclos = 0;
	static int tiempoEjecucion = 0;
	static int ultimoElementoCola = 0;
	static int tiempoRestante = 0;
	static int ultimaPosicion = 0;
	
	public static void run(ArrayList<Proceso> listaProcesos, int quantum) throws InterruptedException {
		System.out.println("- RR Q=" + quantum + " -");
		int h = 0;
		cola.add(0);
		
		for (Proceso proceso : listaProcesos) {
			ciclos += proceso.getTiempoEjecucion();
		}
		
		do {
			for (int j = 0; j < quantum; j++) {
				System.out.print("Proceso " + listaProcesos.get(cola.get(0)).getLetraProceso() +
						" - Tiempo restante: " + (listaProcesos.get(cola.get(0)).getTiempoEjecucion() - 1));
				tiempoEjecucion = listaProcesos.get(cola.get(0)).getTiempoEjecucion();
				listaProcesos.get(cola.get(0)).setTiempoEjecucion(tiempoEjecucion - 1);
				lineaTemporal++;
				listaProcesos.get(cola.get(0)).setUltimaPosicion(lineaTemporal);
				
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
		
	}
	
}