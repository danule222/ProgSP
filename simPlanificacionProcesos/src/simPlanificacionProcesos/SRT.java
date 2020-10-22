package simPlanificacionProcesos;

import java.util.ArrayList;

public class SRT {
	
	static ArrayList<Integer> cola = new ArrayList<Integer>();
	static ArrayList<Proceso> listaProcesosImportada = new ArrayList<Proceso>();
	
	static int lineaTemporal = 0;
	static int ultimoTiempoLlegada = 0;
	static int ciclos = 0;
	static int tiempoEjecucion = 0;
	static int ultimoElementeCola = 0;

	public static void run(ArrayList<Proceso> listaProcesos) 
			throws InterruptedException{
		listaProcesosImportada = listaProcesos;
		System.out.println("- SRT -");
		int h = 0;
		cola.add(0);
		for (Proceso proceso : listaProcesosImportada) {
			ciclos += proceso.getTiempoEjecucion();
		}
		
		for (int i = 0; i < ciclos; i++) {
			
			System.out.print("Proceso " + listaProcesosImportada.get(cola.get(0)).getLetraProceso() +
					" - Tiempo restante: " + (listaProcesosImportada.get(cola.get(0)).getTiempoEjecucion() - 1));
			tiempoEjecucion = listaProcesosImportada.get(cola.get(0)).getTiempoEjecucion();
			listaProcesosImportada.get(cola.get(0)).setTiempoEjecucion(tiempoEjecucion - 1);
			
			if (listaProcesosImportada.get(cola.get(0)).getTiempoEjecucion() == 0) {
				System.out.print(" - Finalizado");
				listaProcesosImportada.get(cola.get(0)).setTerminado(true);
			}
			System.out.println();
			Thread.sleep(500);
			lineaTemporal++;
			
			ultimoElementeCola = cola.get(0);
			cola.clear();
			h = 0;
			for (Proceso proceso : listaProcesosImportada) {
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
