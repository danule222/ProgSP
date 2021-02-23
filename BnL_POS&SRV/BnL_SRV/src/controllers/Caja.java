package controllers;

import java.sql.SQLException;

import dao.FacturaDAO;

/**
 * Controlador de las operaciones relacionadas
 * con caja.
 * @author Daniel Ramírez Morilla
 */
public class Caja {
	
	private static FacturaDAO fDAO = new FacturaDAO();

	/**
	 * Obtiene las ganancias hechas por el empleado
	 * indicado en el día en el que se llama a 
	 * este método.
	 * @param DNI_Empleado DNI del empleado.
	 * @return Total de ganancias del día.
	 * @throws SQLException
	 */
	public static double getCajaDelDia(String DNI_Empleado)
			throws SQLException {
		return fDAO.cajaDelDia(DNI_Empleado);
	}
	
}
