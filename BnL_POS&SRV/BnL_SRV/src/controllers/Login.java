package controllers;

import java.sql.SQLException;

import dao.EmpleadoDAO;
import models.Empleado;

public class Login {
	
	private static EmpleadoDAO eDAO = new EmpleadoDAO();
	
	public static Empleado login(String mensaje) 
			throws NumberFormatException, SQLException {
		String numero[] = mensaje.split(";");
		Empleado e = eDAO.getEmpleadoByNumeroPrivado
				(Integer.parseInt(numero[1]));
		
		return e;
	}

}
