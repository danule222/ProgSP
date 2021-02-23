package controllers;

import java.util.ArrayList;

import dao.FacturaDAO;
import dao.ProductoDAO;

/**
 * Controlador de las operaciones ralacionadas
 * con los cobros.
 * @author Daniel Ramírez Morilla
 */
public class Cobrar {
	
	private static ProductoDAO pDAO = new ProductoDAO();
	private static FacturaDAO fDAO = new FacturaDAO();

	/**
	 * Cobrar a un cliente.
	 * @param listaCompras Lista que contiene tanto
	 * los IDs de los productos como la cantidad que hay
	 * de cada uno.
	 * @param DNI_Empleado DNI del empleado que efectúa
	 * el cobro.
	 * @throws Exception
	 */
	public static void cobro(ArrayList<String> listaCompras, String DNI_Empleado)
			throws Exception {
		int ID_Factura = fDAO.crearFactura(DNI_Empleado);
		for (String l : listaCompras) {
			String[] producto = l.split(";");
			int ID_Tienda = Integer.parseInt(producto[1]);
			int ID_Producto = Integer.parseInt(producto[2]);
			int cantidad = Integer.parseInt(producto[3]);
			pDAO.venderProducto(ID_Tienda, ID_Producto, cantidad);
			fDAO.annadirElementoAFactura(ID_Factura, ID_Producto, cantidad);
		}
	}

	
	
}
