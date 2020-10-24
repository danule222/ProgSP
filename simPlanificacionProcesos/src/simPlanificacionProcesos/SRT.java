package simPlanificacionProcesos;

import java.util.ArrayList;

public class SRT {
	
	static ArrayList<Integer> cola = new ArrayList<Integer>();
	
	static int lineaTemporal = 0;
	static int ultimoTiempoLlegada = 0;
	static int ciclos = 0;
	static int tiempoEjecucion = 0;
	static int ultimoElementeCola = 0;

	public static void run(ArrayList<Proceso> listaProcesos) 
			throws InterruptedException{
		System.out.println("- SRT -");
		int h = 0;
		cola.add(0);
		for (Proceso proceso : listaProcesos) {
			ciclos += proceso.getTiempoEjecucion();
		}
		
		for (int i = 0; i < ciclos; i++) {
			
			System.out.print("Proceso " + listaProcesos.get(cola.get(0)).getLetraProceso() +
					" - Tiempo restante: " + (listaProcesos.get(cola.get(0)).getTiempoEjecucion() - 1));
			tiempoEjecucion = listaProcesos.get(cola.get(0)).getTiempoEjecucion();
			listaProcesos.get(cola.get(0)).setTiempoEjecucion(tiempoEjecucion - 1);
			
			if (listaProcesos.get(cola.get(0)).getTiempoEjecucion() == 0) {
				System.out.print(" - Finalizado");
				listaProcesos.get(cola.get(0)).setTerminado(true);
			}
			System.out.println();
			Thread.sleep(500);
			lineaTemporal++;
			
			ultimoElementeCola = cola.get(0);
			cola.clear();
			h = 0;
			for (Proceso proceso : listaProcesos) {
				if (!proceso.getTerminado()) {
					if (proceso.getTiempoLlegada() <= lineaTemporal) {
						if (proceso.getTiempoEjecucion() <= tiempoEjecucion) {
							cola.add(0, h);
						} else {
							cola.add(h);
						}
					}
				}
				h++;
			}
			if (cola.isEmpty()) {
				cola.add(ultimoElementeCola);
			}
		}
		
	}
	
}
