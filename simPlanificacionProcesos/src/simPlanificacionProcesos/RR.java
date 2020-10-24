package simPlanificacionProcesos;

import java.util.ArrayList;

public class RR {

	static ArrayList<Integer> cola = new ArrayList<Integer>();
	
	static int lineaTemporal = 0;
	static int ciclos = 0;
	static int tiempoEjecucion = 0;
	static int ultimoElementoCola = 0;
	static int tiempoRestante = 0;
	static int ultimoTiempoEnCola = 0;
	
	public static void run(ArrayList<Proceso> listaProcesos, int quantum) {
		System.out.println("- RR Q=" + quantum + " -");
		int h = 0;
		cola.add(0);
		
		for (Proceso proceso : listaProcesos) {
			ciclos += proceso.getTiempoEjecucion();
		}
		
		do {
			for (int j = 0; j < quantum; j++) {
				System.out.print(listaProcesos.get(cola.get(0)).getLetraProceso());
				tiempoEjecucion = listaProcesos.get(cola.get(0)).getTiempoEjecucion();
				listaProcesos.get(cola.get(0)).setTiempoEjecucion(tiempoEjecucion - 1);
				lineaTemporal++;
				
				if (listaProcesos.get(cola.get(0)).getTiempoEjecucion() == 0) {
					System.out.print(" - Finalizado");
					listaProcesos.get(cola.get(0)).setTerminado(true);
					System.out.println();
					break;
				}
				System.out.println();
				//Thread.sleep(500);
			}
			
			ultimoElementoCola = cola.get(0);
			cola.clear();
			h = 0;
			
			for (Proceso proceso : listaProcesos) {
				if (!proceso.getTerminado() && 
						proceso != listaProcesos.get(ultimoElementoCola) &&
						proceso.getTiempoLlegada() <= lineaTemporal) {
					cola.add(h);
					break;
				}
				h++;
			}
				
		} while (lineaTemporal < ciclos);
		
	}
	
}