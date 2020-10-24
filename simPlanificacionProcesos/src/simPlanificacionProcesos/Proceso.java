package simPlanificacionProcesos;

/**
 * Clase que define los procesos.
 * @author Daniel Ramírez Morilla
 */
public class Proceso {

	/**
	 * Letra del proceso.
	 */
	private char letraProceso;
	/**
	 * Tiempo de llegada del proceso.
	 */
	private int tiempoLlegada;
	/**
	 * Tiempo de ejecución del proceso.
	 */
	private int tiempoEjecucion;
	/**
	 * <ul>
	 * <li><b>True:</b> El proceso ha terminado.</li>
	 * <li><b>False:</b> El proceso no ha terminado.</li>
	 * </ul>
	 */
	private boolean terminado;
	/**
	 * Última posición en el que el proceso ha sido ejecutado.
	 */
	private int ultimaPosicion;

	/**
	 * Constructor de proceso.
	 * @param letraProceso Letra que usará el proceso.
	 * @param tiempoLlegada Ciclo en el que entrará el proceso.
	 * @param tiempoEjecucion Tiempo necesario para que el proceso finalice su
	 * trabajo.
	 */
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