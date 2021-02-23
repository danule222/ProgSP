package main;

import java.sql.SQLException;
import java.util.ArrayList;

import controllers.Caja;
import controllers.Cobrar;
import controllers.Login;
import dao.JDBC;
import models.Empleado;

/**
 * Clase principal del servidor.
 * @author Daniel Ramírez Morilla
 */
public class Main {

	public static void main(String[] args) {
		
		JDBC.establecerConexion();
		// Prueba Login
		try {
			JDBC.setAutocommit(false);
			Empleado e = Login.login("Login;1234567890");
			System.out.println(e.getApellidos());
			// Prueba cobrar
			ArrayList<String> listaCompras = new ArrayList<>();
					// Tienda;Producto;Cantidad
			listaCompras.add("Cobro;0;0;1");
			try {
				Cobrar.cobro(listaCompras, e.getDNI());
				JDBC.commit();
			} catch (Exception e1) {
				e1.printStackTrace();
				JDBC.rollback();
			}
			// Probar caja del día
			System.out.println("Caja del día: " 
					+ Caja.getCajaDelDia(e.getDNI()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo login");
		}
		
	}

}
