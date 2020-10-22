package simPlanificacionProcesos;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		Proceso A = new Proceso('A',0,3);
		Proceso B = new Proceso('B',2,6);
		Proceso C = new Proceso('C',4,4);
		Proceso D = new Proceso('D',6,5);
		Proceso E = new Proceso('E',8,2);
		
		ArrayList<Proceso> listaProcesos = new ArrayList<Proceso>();
		listaProcesos.add(A);
		listaProcesos.add(B);
		listaProcesos.add(C);
		listaProcesos.add(D);
		listaProcesos.add(E);
		
		listaProcesos.sort((o1, o2) -> Integer.compare(o1.getTiempoLlegada(),
				o2.getTiempoLlegada()));
		
		FIFO.run(listaProcesos);
		SJF.run(listaProcesos);
		SRT.run(listaProcesos);
		
	}

}
