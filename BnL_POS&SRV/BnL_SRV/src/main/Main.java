package main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import controllers.Caja;
import controllers.Cobrar;
import controllers.Config;
import controllers.Logger;
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
		Logger.log("Iniciando servidor.");
		try {
			Config.leerConfiguracion();
			JDBC.establecerConexion();
			Logger.log("[I] Conexión a la BDD realizada con éxito.");
		} catch (SAXException e) {
			Logger.log("[F] Error al leer App.config.");
		} catch (IOException e) {
			Logger.log("[F] Error al leer App.config.");
		} catch (ParserConfigurationException e) {
			Logger.log("[F] Error al leer App.config.");
		} catch (ClassNotFoundException e) {
			Logger.log("[F] Error al incializar JDBC.");
		} catch (SQLException e) {
			Logger.log("[F] Error al establecer conexión con la base de datos.");
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
				Logger.log("[A] Compra cancelada por falta de stock.");
				JDBC.rollback();
				Logger.log("[A] Rollback ejecutado con éxito.");
			}
			// Probar caja del día
			System.out.println("Caja del día: " 
					+ Caja.getCajaDelDia(e.getDNI()));
		} catch (NumberFormatException e) {
			Logger.log("[F] Error al convertir número privado "
					+ "a tipo entero.");
		} catch (SQLException e) {
			Logger.log("[A] Login incorrecto.");
			System.out.println("Fallo login");
		}
		
	}

}
