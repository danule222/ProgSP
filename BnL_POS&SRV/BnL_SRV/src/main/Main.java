package main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import controllers.Caja;
import controllers.Cobrar;
import controllers.Config;
import controllers.Login;
import dao.JDBC;
import models.Empleado;

/**
 * Clase principal del servidor.
 * @author Daniel Ramírez Morilla
 */
public class Main {
	
	/**
	 * Inicializa todos los componentes
	 * necesarios para la ejecución del
	 * programa.
	 */
	private static void inicializar() {
		try {
			Config.leerConfiguracion();
			JDBC.establecerConexion();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		inicializar();
		
		// Prueba Login
		try {
			JDBC.setAutocommit(false);
			Empleado e = Login.login("Login;1234567890");
			System.out.println(e.getApellidos());
			// Prueba cobrar
			ArrayList<String> listaCompras = new ArrayList<>();
					// Tienda;Producto;Cantidad
			listaCompras.add("Cobro;0;0;3");
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
