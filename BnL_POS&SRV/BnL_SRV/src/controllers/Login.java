package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.EmpleadoDAO;
import dao.ProductoDAO;
import models.Empleado;
import models.Producto;

/**
 * Controlador del inicio de sesión.
 * @author Daniel Ramírez Morilla
 */
public class Login {
	
	private static EmpleadoDAO eDAO = new EmpleadoDAO();
	private static ProductoDAO pDAO = new ProductoDAO();
	
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
	
	/**
	 * Obtiene la lista de todos los productos.
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<Producto> listaProductos()
			throws SQLException {
		return pDAO.getListaProductos();
	}
	
	public static void cerrarSesion(String DNI_Empleado)
			throws SQLException {
		eDAO.setUltimaSesion(DNI_Empleado);
	}

}
