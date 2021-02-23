package controllers;

import java.sql.SQLException;

import dao.EmpleadoDAO;
import models.Empleado;

/**
 * Controlador del inicio de sesión.
 * @author Daniel Ramírez Morilla
 */
public class Login {
	
	private static EmpleadoDAO eDAO = new EmpleadoDAO();
	
	/**
	 * Si el mensaje contiene el número privado que
	 * coincida con el de uno de los empleados, se devolverá
	 * un objeto Empleado con todos los datos de este; en
	 * caso de que no haya ninguna coincidencia, se devolverá
	 * un objeto Empleado vacío.
	 * @param mensaje Mensaje que contiene el número privado.
	 * @return Objeto Empleado.
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public static Empleado login(String mensaje) 
			throws NumberFormatException, SQLException {
		String numero[] = mensaje.split(";");
		Empleado e = eDAO.getEmpleadoByNumeroPrivado
				(Integer.parseInt(numero[1]));
		
		return e;
	}

}
