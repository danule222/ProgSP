package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import controllers.Config;
import controllers.Logger;
import dao.JDBC;
import threads.Servidor;

/**
 * Clase principal del servidor.
 * @author Daniel Ramírez Morilla
 */
public class Main {
	
	private static ServerSocket servidor;
    static Socket clienteConectado;
	
	/**
	 * Inicializa todos los componentes
	 * necesarios para la ejecución del
	 * programa.
	 */
	private static void inicializar() {
		Logger.log("[i] Iniciando servidor.");
		try {
			Config.leerConfiguracion();
			JDBC.establecerConexion();
			JDBC.setAutocommit(false);
			Logger.log("[i] Conexión a la BDD realizada con éxito.");
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
		
		try {
			servidor = new ServerSocket(Config.getPuerto());
			Logger.log("[i] Puerto " + Config.getPuerto() + " abierto "
					 + "con éxito.");
		} catch (IOException e1) {
			Logger.log("[F] No se pudo abrir el Socket del servidor.");
		}
		clienteConectado = null;
		
		while(true) {
			try {
		        clienteConectado = servidor.accept();
		        Logger.log("[i] Se ha conectado un cliente.");
		        clienteConectado.setSoTimeout(3600000);
		        new Servidor(clienteConectado).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        
		}
		
	}

}
