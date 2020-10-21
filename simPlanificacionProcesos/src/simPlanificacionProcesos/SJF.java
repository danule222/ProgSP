package simPlanificacionProcesos;

import java.util.ArrayList;

public class SJF {
	
	static ArrayList<Integer> cola = new ArrayList<Integer>();
	
	static int lineaTemporal = 0;
	static int ultimoTiempoLlegada = 0;
	static int posicion = 0;
	static int ciclos;

	public static void run(ArrayList<Proceso> listaProcesos) throws InterruptedException{
		System.out.println("- SJF -");
		ciclos = listaProcesos.size();
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
				Thread.sleep(500);
				lineaTemporal++;
			}
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
	}
	
}
