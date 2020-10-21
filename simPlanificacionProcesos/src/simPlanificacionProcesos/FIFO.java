package simPlanificacionProcesos;

import java.util.ArrayList;

public class FIFO {

	public static void run(ArrayList<Proceso> listaProcesos) throws InterruptedException {
		System.out.println("- FIFO -");
		for (int i = 0; i < listaProcesos.size(); i++) {
			for (int j = 0; j < listaProcesos.get(i).getTiempoEjecucion(); j++) {
				System.out.print("Proceso " + listaProcesos.get(i).getLetraProceso() +
						" - Tiempo restante: ");
				if ((listaProcesos.get(i).getTiempoEjecucion() - j - 1) == 0)
					System.out.print(listaProcesos.get(i).getTiempoEjecucion() - j - 1 +
							" - Finalizado");
				else System.out.print(listaProcesos.get(i).getTiempoEjecucion() - j - 1);
				System.out.println();
				Thread.sleep(500);
			}
		}
		
	}
	
}
