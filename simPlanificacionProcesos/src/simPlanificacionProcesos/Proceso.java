package simPlanificacionProcesos;

public class Proceso {

	private char letraProceso;
	private int tiempoLlegada;
	private int tiempoEjecucion;
	private boolean terminado;
	private int ultimaPosicion;

	public Proceso(char letraProceso, int tiempoLlegada, int tiempoEjecucion) {
		this.letraProceso = letraProceso;
		this.tiempoLlegada = tiempoLlegada;
		this.tiempoEjecucion = tiempoEjecucion;
		terminado = false;
		ultimaPosicion = this.tiempoLlegada;
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
	
	public boolean getTerminado() {
		return terminado;
	}
	
	public void setTerminado(boolean terminado) {
		this.terminado = terminado;
	}
	
	public int getUltimaPosicion() {
		return ultimaPosicion;
	}

	public void setUltimaPosicion(int ultimaPosicion) {
		this.ultimaPosicion = ultimaPosicion;
	}
	
}