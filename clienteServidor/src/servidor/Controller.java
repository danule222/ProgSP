package servidor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Controller {
    
	/**
	 * Comprueba que la hora actual del sistema
	 * se encuentra entre la hora de inicio y hora
	 * final dadas.
	 * @param start Hora inicial.
	 * @param end Hora final.
	 * @return <ul><li><b>True: </b>La hora actual se encuentra
	 * entre las horas dadas.</li><li><b>False: </b>La hora actual
	 * no se encuentra entre las horas dadas.</li><ul>
	 */
    static boolean isHoraIntermedia(String start, String end) {
    	LocalTime inicio = LocalTime.parse(start);
    	LocalTime fin = LocalTime.parse(end);
        return (LocalTime.now().isAfter(inicio)) && 
        		(LocalTime.now().isBefore(fin));
    }
    
    /** @return String con la hora actual (HH:mm). */
    static String getHoraActual() {
    	return LocalTime.now().format(DateTimeFormatter
    			.ofPattern("HH:mm")).toString();
    }
    
    /** @return String con la fecha actual (dd-MM-yyyy). */
    static String getFechaActual() {
    	return LocalDate.now().format(DateTimeFormatter
    			.ofPattern("dd-MM-yyyy")).toString();
    }
	
    /**
     * Devuelve un saludo dependiendo de la hora actual
     * del sistema.
     * @return <table>
     * <tr><td>06:00 a 11:59</td><td>Buenos días</td></tr>
     * <tr><td>12:00 a 20:59</td><td>Buenas tardes</td></tr>
     * <tr><td>21:00 a 05:59</td><td>Buenas noches</td></tr>
     * </table>
     */
	static String buenas() {
		if (isHoraIntermedia("06:00", "11:59"))
			return "Buenos días";
		else if (isHoraIntermedia("12:00", "20:59"))
			return "Buenas tardes";
		else return "Buenas noches";
	}
	
}
