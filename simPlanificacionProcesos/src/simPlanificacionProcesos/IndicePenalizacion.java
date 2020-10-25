package simPlanificacionProcesos;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Cálculo del índice de penalización.
 * @author drami
 */
public class IndicePenalizacion {

	/**
	 * Realiza el cálculo del índice de penalización de todos los procesos
	 * y del logaritmo (promedio).
	 * @param listaProcesos ArrayList de la clase Proceso al haber terminado
	 * la ejecución de la simulación.
	 * @param nombreLog Nombre del logaritmo.
	 */
	public static void calcularIP(ArrayList<Proceso> listaProcesos, String nombreLog) {
		
		DecimalFormat df = new DecimalFormat("#.00");
		double Ip = 0;
		double sumaIp = 0;
		int ciclos = 0;
		
		System.out.println("\n- CÁLCULO DEL ÍNDICE DE PENALIZACIÓN -");
		
		for (Proceso proceso : listaProcesos) {
			System.out.print("Ip de " + proceso.getLetraProceso() + ": ");
			System.out.print("(" + proceso.getUltimaPosicion() + "-"
					+ proceso.getTiempoLlegada() + ")/" + proceso.getTiempoEjecucionOriginal());
			Ip = (double)(proceso.getUltimaPosicion()-proceso.getTiempoLlegada())
					/proceso.getTiempoEjecucionOriginal();
			sumaIp += Ip;
			System.out.println(" = " + df.format(Ip));
			ciclos++;
		}
		
		System.out.println("Ip de " + nombreLog + ": " + df.format(sumaIp) + "/"
				+ df.format(ciclos) + " = " + df.format(sumaIp/ciclos));
		
	}
	
}
