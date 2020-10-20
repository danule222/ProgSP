package simPlanificacionProcesos;

public class Proceso {

	private char letraProceso;
	private int tiempoLlegada;
	private int tiempoEjecucion;
	
	public Proceso(char letraProceso, int tiempoLlegada, int tiempoEjecucion) {
		this.letraProceso = letraProceso;
		this.tiempoLlegada = tiempoLlegada;
		this.tiempoEjecucion = tiempoEjecucion;
	}
	
	public char getLetraProceso() {
		return letraProceso;
	}

	public void setLetraProceso(char letraProceso) {
		this.letraProceso = letraProceso;
	}

	public int getTiempoLlegada() {
		return tiempoLlegada;
	}

	public void setTiempoLlegada(int tiempoLlegada) {
		this.tiempoLlegada = tiempoLlegada;
	}

	public int getTiempoEjecucion() {
		return tiempoEjecucion;
	}

	public void setTiempoEjecucion(int tiempoEjecucion) {
		this.tiempoEjecucion = tiempoEjecucion;
	}
	
}
